package momsday.careworker;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import momsday.careworker.connecter.Api;
import momsday.careworker.connecter.Connect;
import momsday.careworker.model.JWTModel;
import momsday.careworker.ui.main.MainActivity;
import momsday.careworker.ui.start.StartActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static momsday.careworker.util.TokenManagerKt.getToken;
import static momsday.careworker.util.TokenManagerKt.removeToken;
import static momsday.careworker.util.TokenManagerKt.saveToken;

public class SplashActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (!getToken(getBaseContext(), true).isEmpty()) {
//            Connect.getAPI().
            Api api = Connect.getAPI();
            Log.d("Refresh_Token", getToken(getBaseContext(), false) + "  ");
            api.refresh(getToken(getBaseContext(), false)).enqueue(new Callback<JWTModel>() {
                @Override
                public void onResponse(Call<JWTModel> call, Response<JWTModel> res) {
                    removeToken(getBaseContext(), true);
                    saveToken(getBaseContext(), res.body().getAccessToken(), true);
//                        Toast.makeText(getBaseContext(), "얘도안되네", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    finish();
                }

                @Override
                public void onFailure(Call<JWTModel> call, Throwable t) {
//                    Toast.makeText(getBaseContext(), "뭔문제임", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), StartActivity.class));
                    finish();
                }
            });
        } else {
            startActivity(new Intent(this, StartActivity.class));
            finish();
        }
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
