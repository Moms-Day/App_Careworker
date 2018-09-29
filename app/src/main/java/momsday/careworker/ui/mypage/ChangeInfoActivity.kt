package momsday.careworker.ui.mypage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.common.internal.ConnectionErrorMessages
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_change_info.*
import momsday.careworker.R
import momsday.careworker.connecter.Connect
import momsday.careworker.model.MyPageInfoModel
import momsday.careworker.util.getToken
import org.jetbrains.anko.sdk25.coroutines.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_info)
        Connect.getAPI().getMyPageInfo(getToken(baseContext, true)).enqueue(object : Callback<MyPageInfoModel> {
            override fun onResponse(call: Call<MyPageInfoModel>?, response: Response<MyPageInfoModel>) {
                val model = response.body()!!
                changeInfo_bio_et.setText(model.bio)
                changeInfo_name_et.setText(model.name)
                changeInfo_career_et.setText(model.career.toString())
                changeInfo_facilityCode_et.setText(model.facilityCode)
            }

            override fun onFailure(call: Call<MyPageInfoModel>?, t: Throwable?) {

            }

        })
        changeInfo_submit_btn.onClick {
            val req = JsonObject().apply {
                addProperty("name", changeInfo_name_et.text.toString())
                addProperty("bio", changeInfo_bio_et.text.toString())
                addProperty("career", changeInfo_career_et.text.toString().toInt())
                addProperty("facility_code", changeInfo_facilityCode_et.text.toString())
            }
            Connect.getAPI().patchMyPageInfo(getToken(baseContext,true),req).enqueue(object: Callback<Void>{
                override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                    Toast.makeText(baseContext,"수정되었습니다.",Toast.LENGTH_SHORT).show()
                    finish()
                }

                override fun onFailure(call: Call<Void>?, t: Throwable?) {
                }

            }
            )
        }
    }
}
