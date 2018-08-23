package momsday.careworker.ui.Login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import momsday.careworker.R;
import momsday.careworker.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.loginBtnSubmit.setOnClickListener((v) -> {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        });

    }

    void onLoginButtonClick() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
