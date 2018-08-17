package momsday.careworker.ui.SignUp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;

public class SignUpViewModel extends ViewModel {

    private MutableLiveData<String> id = new MutableLiveData<>();
    private MutableLiveData<String> pw = new MutableLiveData<>();
    private MutableLiveData<String> confirm = new MutableLiveData<>();
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> age = new MutableLiveData<>();
    private MutableLiveData<String> career = new MutableLiveData<>();
    private MutableLiveData<String> patient = new MutableLiveData<>();
    private MutableLiveData<String> phoneNum = new MutableLiveData<>();
    private MutableLiveData<String> verify = new MutableLiveData<>();
    private MutableLiveData<String> hospitalCode = new MutableLiveData<>();
    private MutableLiveData<String> introduce = new MutableLiveData<>();

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

    public MutableLiveData<String> getAge() {
        return age;
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

    //    public LiveData<String> getId() {
//        return id;
//    }
//
//    public LiveData<String> getPw() {
//        return pw;
//    }
//
//    public LiveData<String> getConfirm() {
//        return confirm;
//    }
//
//    public LiveData<String> getName() {
//        return name;
//    }
//
//    public LiveData<String> getAge() {
//        return age;
//    }
//
//    public LiveData<String> getCareer() {
//        return career;
//    }
//
//    public LiveData<String> getPatient() {
//        return patient;
//    }
//
//    public LiveData<String> getPhoneNum() {
//        return phoneNum;
//    }
//
//    public LiveData<String> getVerify() {
//        return verify;
//    }
//
//    public LiveData<String> getHospitalCode() {
//        return hospitalCode;
//    }
//
//    public LiveData<String> getIntroduce() {
//        return introduce;
//    }
}
