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
import com.google.gson.JsonObject
import momsday.careworker.connecter.Connect
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

    lateinit var bitMap: Bitmap

    override fun getLayoutId() = R.layout.fragment_add_photo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.addPhotoAttachImg.onClick {
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
            Log.d("EncodedImage", encodedImage)
            Log.d("EncodedImage", Base64.decode(encodedImage, Base64.DEFAULT).contentToString())
            val req = JsonObject().apply {
                addProperty("encodedImage", encodedImage)
                addProperty("comment", binding.addPhotoDescribeEt.text.toString())
                addProperty("pId", writeFormViewModel.patient.value!!.id)
            }
            Connect.getAPI().sendPhoto(getToken(this@AddPhotoFragment.context!!, true), req).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>?, response: Response<Void>) {
                    when(response.code()){
                        201 ->{
                            Toast.makeText(this@AddPhotoFragment.context, "사진이 첨부되었습니다.",Toast.LENGTH_SHORT).show()
                            activity!!.supportFragmentManager.popBackStack()
                        }
                    }
                }

                override fun onFailure(call: Call<Void>?, t: Throwable?) {
                }

            })
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

        val exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        val exifDegree = exifOrientationToDegrees(exifOrientation)
        bitMap = BitmapFactory.decodeFile(imagePath)
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
//
//    private void sendPicture(Uri imgUri) {
//
//        String imagePath = getRealPathFromURI(imgUri); // path 경로
//        ExifInterface exif = null;
//        try {
//            exif = new ExifInterface(imagePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//        int exifDegree = exifOrientationToDegrees(exifOrientation);
//
//        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);//경로를 통해 비트맵으로 전환
//        ivImage.setImageBitmap(rotate(bitmap, exifDegree));//이미지 뷰에 비트맵 넣기
//
//    }

}// Required empty public constructor
