package momsday.careworker.ui.mypage

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_my_page.*
import momsday.careworker.R
import momsday.careworker.connecter.Connect
import momsday.careworker.model.MyPageInfoModel
import momsday.careworker.model.MyPageModel
import momsday.careworker.util.getToken
import org.jetbrains.anko.sdk25.coroutines.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageActivity : AppCompatActivity() {

    lateinit var myPageModel: MyPageInfoModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        Connect.getAPI().getMyPage(getToken(baseContext, true)).enqueue(object : Callback<MyPageModel> {
            override fun onResponse(call: Call<MyPageModel>?, response: Response<MyPageModel>) {
                myPage_name_tv.text = response.body()!!.name

            }

            override fun onFailure(call: Call<MyPageModel>?, t: Throwable?) {

            }

        })
        myPage_changeInfo_btn.onClick {
            startActivity(Intent(this@MyPageActivity, ChangeInfoActivity::class.java ))
        }
    }
}
