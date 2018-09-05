package momsday.careworker.ui.Login;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import momsday.careworker.R;
import momsday.careworker.connecter.Api;
import momsday.careworker.connecter.Connect;
import momsday.careworker.databinding.ActivityLoginBinding;
import momsday.careworker.model.JWTModel;
import momsday.careworker.ui.Main.MainActivity;
import retrofit2.Response;

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
                                Toast.makeText(getBaseContext(), res.body().getAccessToken(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
