package momsday.careworker.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import momsday.careworker.R;
import momsday.careworker.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start);


        binding.startBtnLogin.setOnClickListener(v -> startActivityForResult(new Intent(this, LoginActivity.class), 1));

        binding.startBtnSignUp.setOnClickListener((v) -> {
//                        TODO 회원가입 액티비티로 넘어가는 기능 추가
            startActivity(new Intent(this, SignUpActivity.class));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

}
