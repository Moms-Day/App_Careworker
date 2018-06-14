package momsday.careworker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import momsday.careworker.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding.loginBtnSubmit.setOnClickListener((v) -> {

        });
    }
    void onLoginButtonClick() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
