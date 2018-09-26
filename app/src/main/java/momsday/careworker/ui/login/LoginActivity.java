package momsday.careworker.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import momsday.careworker.R;
import momsday.careworker.connecter.Api;
import momsday.careworker.connecter.Connect;
import momsday.careworker.databinding.ActivityLoginBinding;
import momsday.careworker.ui.main.MainActivity;

import static momsday.careworker.util.TokenManagerKt.saveId;
import static momsday.careworker.util.TokenManagerKt.saveToken;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        compositeDisposable = new CompositeDisposable();
        binding.loginBtnSubmit.setOnClickListener((v) -> {
            JsonObject req = new JsonObject();
            req.addProperty("id", binding.loginEtId.getText().toString());
            req.addProperty("pw", binding.loginEtPw.getText().toString());
            Api api = Connect.getAPI();
            addDisposable(api.signIn(req).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .debounce(1, TimeUnit.SECONDS)
                    .subscribe(res -> {
                        switch (res.code()) {
                            case 200:
//                                Toast.makeText(getBaseContext(), res.body().getAccessToken(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                saveId(getBaseContext(), binding.loginEtId.getText().toString());

                                Log.d("TOKEN IS ", res.body().getAccessToken());
                                saveToken(getBaseContext(), res.body().getAccessToken(), true);
                                saveToken(getBaseContext(), res.body().getRefreshToken(), false);
                                startActivity(intent);
                                ActivityCompat.finishAffinity(this);
                        }
                    }, throwable -> {

                    }));
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
