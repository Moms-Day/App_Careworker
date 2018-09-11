package momsday.careworker.ui.patientInfo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_patient_info.*

import momsday.careworker.R
import momsday.careworker.connecter.Connect
import momsday.careworker.util.getToken
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)
        val id = intent.getStringExtra("id")
        tv_patientInfo_name.text = intent.getStringExtra("name")
        Connect.getAPI().getPatientMemo(getToken(baseContext, true), id).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
//                toast(response!!.code())
                response!!.body()?.let { et_patientInfo_memo.setText(it) }
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {

            }

        })
        btn_patientInfo_submit.onClick {
            val jsonObject = JsonObject().apply {
                addProperty("memo", et_patientInfo_memo.text.toString())
            }
            Connect.getAPI().patchPatientMemo(getToken(baseContext, true), id, jsonObject).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>?, response: Response<Void>) {
//                    toast(response.code())
                    finish()
                }

                override fun onFailure(call: Call<Void>?, t: Throwable?) {

                }

            })
        }
    }
}
