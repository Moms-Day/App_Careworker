package momsday.careworker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.start_btn_login)
    Button loginBtn;

    @BindView(R.id.start_btn_signUp)
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        loginBtn.setOnClickListener(v -> {
//            TODO 로그인 액티비티로 넘어가는 기능 추가
//            startActivity(new Intent(this,));
        });

        signUpBtn.setOnClickListener((v) ->{
//            TODO 회원가입 액티비티로 넘어가는 기능 추가
        });
    }
}
