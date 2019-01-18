package momsday.careworker.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import momsday.careworker.R;
import momsday.careworker.connecter.Api;
import momsday.careworker.connecter.Connect;
import momsday.careworker.databinding.ActivityMainBinding;
import momsday.careworker.model.PatientListModel;
import momsday.careworker.model.PatientResponseModel;
import momsday.careworker.ui.mypage.MyPageActivity;
import momsday.careworker.util.DataBindingActivity;
import momsday.careworker.viewModel.PatientListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static momsday.careworker.util.TokenManagerKt.getToken;

public class MainActivity extends DataBindingActivity<ActivityMainBinding> {

    private SectionsPagerAdapter sectionsPagerAdapter;

    PatientListFragment patientListFragment;
    ChattingFragment chattingFragment;
    WriteFormFragment writeFormFragment;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    PatientListViewModel patientListViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patientListViewModel = ViewModelProviders.of(this).get(PatientListViewModel.class);
        findViewById(R.id.main_myPage_Btn).setOnClickListener(v -> {
            startActivity(new Intent(this, MyPageActivity.class));
        });
        init();
        observe();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        SharedPreferences preference = getApplicationContext().getSharedPreferences("pref",MODE_PRIVATE);
        firebaseDatabase.getReference("users").child(preference.getString("id","")).setValue(FirebaseInstanceId.getInstance().getToken());

    }

    void init() {
        patientListFragment = new PatientListFragment();
        chattingFragment = new ChattingFragment();
        writeFormFragment = new WriteFormFragment();

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        binding.mainViewPager.setAdapter(sectionsPagerAdapter);

        binding.tabLayoutMain.addTab(binding.tabLayoutMain.newTab().setText("환자 목록"));
        binding.tabLayoutMain.addTab(binding.tabLayoutMain.newTab().setText("채팅"));
        binding.tabLayoutMain.addTab(binding.tabLayoutMain.newTab().setText("폼 작성"));

        ((LinearLayout) ((LinearLayout) binding.tabLayoutMain.getChildAt(0)).getChildAt(0)).getChildAt(1).setScaleY(-1);
        ((LinearLayout) ((LinearLayout) binding.tabLayoutMain.getChildAt(0)).getChildAt(1)).getChildAt(1).setScaleY(-1);
        ((LinearLayout) ((LinearLayout) binding.tabLayoutMain.getChildAt(0)).getChildAt(2)).getChildAt(1).setScaleY(-1);
        binding.mainViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayoutMain));
        binding.tabLayoutMain.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.mainViewPager));

        Api api = Connect.getAPI();

        api.getPatients(getToken(getBaseContext(), true)).enqueue(new Callback<PatientResponseModel>() {
            @Override
            public void onResponse(Call<PatientResponseModel> call, Response<PatientResponseModel> res) {
                if (!res.body().getConnectionRequests().isEmpty())
                    patientListViewModel.addList(new PatientListModel("요청목록 ( " + res.body().getConnectionRequests().size() + " )", PatientListModel.VIEWTYPE_HEADER));
                for (PatientResponseModel.ConnectionRequest model : res.body().getConnectionRequests()) {
                    patientListViewModel.addList(new PatientListModel(model.getParentName(), String.valueOf(model.getAge()), model.getUserName(), model.getRequesterId(), model.getReqId(), PatientListModel.VIEWTYPE_REQUEST));
                }

                if (!res.body().getInChargeList().isEmpty())
                    patientListViewModel.addList(new PatientListModel("환자목록 ( " + res.body().getInChargeList().size() + " )", PatientListModel.VIEWTYPE_HEADER));
                for (PatientResponseModel.InChargeList model : res.body().getInChargeList()) {
                    patientListViewModel.addList(new PatientListModel(model.getName(), String.valueOf(model.getAge()), model.getDaughter(), model.getdId(), model.getId(), PatientListModel.VIEWTYPE_PATIENT));
                }
            }

            @Override
            public void onFailure(Call<PatientResponseModel> call, Throwable t) {

            }
        });
    }

    void observe() {
        patientListViewModel.getPatientListChangeRequestEvent().observe(this, o -> {
            Api api = Connect.getAPI();
            api.getPatients(getToken(getBaseContext(), true)).enqueue(new Callback<PatientResponseModel>() {
                @Override
                public void onResponse(Call<PatientResponseModel> call, Response<PatientResponseModel> res) {
                    patientListViewModel.clearList();
                    if (!res.body().getConnectionRequests().isEmpty())
                        patientListViewModel.addList(new PatientListModel("요청목록 ( " + res.body().getConnectionRequests().size() + " )", PatientListModel.VIEWTYPE_HEADER));
                    for (PatientResponseModel.ConnectionRequest model : res.body().getConnectionRequests()) {
                        patientListViewModel.addList(new PatientListModel(model.getParentName(), String.valueOf(model.getAge()), model.getUserName(), model.getRequesterId(), model.getReqId(), PatientListModel.VIEWTYPE_REQUEST));
                    }

                    if (!res.body().getInChargeList().isEmpty())
                        patientListViewModel.addList(new PatientListModel("환자목록 ( " + res.body().getInChargeList().size() + " )", PatientListModel.VIEWTYPE_HEADER));
                    for (PatientResponseModel.InChargeList model : res.body().getInChargeList()) {
                        patientListViewModel.addList(new PatientListModel(model.getName(), String.valueOf(model.getAge()), model.getDaughter(), model.getdId(), model.getId(), PatientListModel.VIEWTYPE_PATIENT));
                    }
                }

                @Override
                public void onFailure(Call<PatientResponseModel> call, Throwable t) {

                }
            });
        });
    }

    void addDisposable(Disposable d) {
        compositeDisposable.add(d);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return patientListFragment;
                case 1:
                    return chattingFragment;
                case 2:
                    return writeFormFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}