package momsday.careworker.ui.writeForm


import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import momsday.careworker.R
import momsday.careworker.databinding.FragmentAddPhotoBinding
import momsday.careworker.util.DataBindingFragment
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_add_photo.*
import momsday.careworker.connecter.Connect
import momsday.careworker.model.PhotoModel
import momsday.careworker.model.ScheduleListModel
import momsday.careworker.model.ScheduleModel
import momsday.careworker.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.IOException


/**
 * A simple [Fragment] subclass.
 */
class AddPhotoFragment : DataBindingFragment<FragmentAddPhotoBinding>() {

    val writeFormViewModel by lazy {
        ViewModelProviders.of(activity!!).get(WriteFormViewModel::class.java)
    }

    var isPosted = false
    lateinit var bitMap: Bitmap

    override fun getLayoutId() = R.layout.fragment_add_photo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        Connect.getAPI().getPhoto(getToken(context!!, true), writeFormViewModel.patient.value!!.id).enqueue(object : Callback<PhotoModel> {
            override fun onResponse(call: Call<PhotoModel>?, response: Response<PhotoModel>?) {
                val model = response!!.body()!!
                Log.d("AddPhotoFragment", "model.comment: ${model.comment}")
                Log.d("AddPhotoFragment", "model.photo_path: ${model.photo_path}")
                if (model.comment != null) {
                    isPosted = true

                    binding.addPhotoDescribeEt.setText(model.comment)
                }
                if (model.photo_path != null) {
                    isPosted = true
                    Glide.with(this@AddPhotoFragment)
                            .load("http://${model.photo_path}")
                            .into(addPhoto_attach_view)
                    addPhoto_attach_img.visibility = View.INVISIBLE
                }
                Log.d("AddPhotoFragment", "isPosted: $isPosted")
            }

            override fun onFailure(call: Call<PhotoModel>?, t: Throwable?) {
                Log.d("AddPhotoFragment", "FAILED")
            }

        })

        binding.addPhotoAttachView.onClick {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            startActivityForResult(intent, 1112)
        }

        binding.addPhotoSubmitBtn.onClick {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitMap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            Log.d("ByteArray", byteArray!!.contentToString())
            val encodedImage: String = Base64.encodeToString(byteArray, Base64.DEFAULT)

            val req = JsonObject().apply {
                addProperty("encodedImage", encodedImage)
                addProperty("comment", binding.addPhotoDescribeEt.text.toString())
                addProperty("pId", writeFormViewModel.patient.value!!.id)
            }
            if (!isPosted) {
                Connect.getAPI().sendPhoto(getToken(this@AddPhotoFragment.context!!, true), req).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>) {
                        when (response.code()) {
                            201 -> {
                                Toast.makeText(this@AddPhotoFragment.context, "사진이 첨부되었습니다.", Toast.LENGTH_SHORT).show()
                                activity!!.supportFragmentManager.popBackStack()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                    }

                })
            } else {
                Connect.getAPI().updatePhoto(getToken(this@AddPhotoFragment.context!!, true), req).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>) {
                        when (response.code()) {
                            201 -> {
                                Toast.makeText(this@AddPhotoFragment.context, "사진이 첨부되었습니다.", Toast.LENGTH_SHORT).show()
                                activity!!.supportFragmentManager.popBackStack()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                    }

                })
            }
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                1112 -> {
                    data?.data?.let { sendPicture(it) }
                }
            }
        }
    }

    fun sendPicture(imgUri: Uri) {
        val imagePath = getRealPathFromURI(imgUri)
        val exif: ExifInterface = ExifInterface(imagePath)

        val exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_ROTATE_90)
        val exifDegree = exifOrientationToDegrees(exifOrientation)
        bitMap = BitmapFactory.decodeFile(imagePath)
        Glide.with(this)
                .load(bitMap)
                .into(addPhoto_attach_view)
        addPhoto_attach_img.visibility = View.INVISIBLE
        Log.d("Bitmap is", bitMap.toString())
    }

    fun exifOrientationToDegrees(exifOrientation: Int): Int =
            when (exifOrientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> 90
                ExifInterface.ORIENTATION_ROTATE_180 -> 180
                ExifInterface.ORIENTATION_ROTATE_270 -> 270
                else -> 0
            }


    private fun getRealPathFromURI(contentUri: Uri): String {
        var column_index = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = activity!!.contentResolver.query(contentUri, proj, null, null, null)
        if (cursor.moveToFirst()) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }

        return cursor.getString(column_index)
    }

}
