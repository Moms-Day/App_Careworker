package momsday.careworker.ui.SignUp;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import momsday.careworker.Connecter.Api;
import momsday.careworker.Connecter.Connect;
import momsday.careworker.Util.DisposableViewModel;

public class SignUpViewModel extends DisposableViewModel {

    private MutableLiveData<String> id = new MutableLiveData<>();
    private MutableLiveData<String> pw = new MutableLiveData<>();
    private MutableLiveData<String> confirm = new MutableLiveData<>();
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> career = new MutableLiveData<>();
    private MutableLiveData<String> patient = new MutableLiveData<>();
    private MutableLiveData<String> phoneNum = new MutableLiveData<>();
    private MutableLiveData<String> verify = new MutableLiveData<>();
    private MutableLiveData<String> hospitalCode = new MutableLiveData<>();
    private MutableLiveData<String> introduce = new MutableLiveData<>();
    private MutableLiveData<Integer> resCode = new MutableLiveData<>();

    public MutableLiveData<String> getId() {
        return id;
    }

    public MutableLiveData<String> getPw() {
        return pw;
    }

    public MutableLiveData<String> getConfirm() {
        return confirm;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getCareer() {
        return career;
    }

    public MutableLiveData<String> getPatient() {
        return patient;
    }

    public MutableLiveData<String> getPhoneNum() {
        return phoneNum;
    }

    public MutableLiveData<String> getVerify() {
        return verify;
    }

    public MutableLiveData<String> getHospitalCode() {
        return hospitalCode;
    }

    public MutableLiveData<String> getIntroduce() {
        return introduce;
    }

    public MutableLiveData<Integer> getResCode() {
        return resCode;
    }

    public void signUp() {
        Api api = Connect.getAPI();

        JsonObject requestMap = new JsonObject();
        requestMap.addProperty("id", id.getValue());
        requestMap.addProperty("pw", pw.getValue());
        requestMap.addProperty("name", name.getValue());
        requestMap.addProperty("career", Integer.parseInt(career.getValue()));
        requestMap.addProperty("patientInCharge", Integer.parseInt(patient.getValue()));
        requestMap.addProperty("phoneNumber", phoneNum.getValue());
        requestMap.addProperty("certifyCode", verify.getValue());
        requestMap.addProperty("facilityCode", hospitalCode.getValue());
        requestMap.addProperty("bio", introduce.getValue());
        Log.d("SignUpViewModel", requestMap.toString());
        addDisposable(api.signUp(requestMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                    resCode.setValue(res.code());
                }));
    }
}
