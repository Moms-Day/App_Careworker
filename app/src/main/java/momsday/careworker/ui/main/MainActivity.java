package momsday.careworker.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

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
import momsday.careworker.util.DataBindingActivity;
import momsday.careworker.viewModel.PatientListViewModel;

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
        init();
    }

    void init() {
        patientListViewModel = ViewModelProviders.of(this).get(PatientListViewModel.class);

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

        addDisposable(api.getPatients(getToken(getBaseContext(), true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                    if (!res.body().getConnectionRequests().isEmpty())
                        patientListViewModel.getPatientList().getValue().add(new PatientListModel("요청목록 ( " + res.body().getConnectionRequests().size() + " )", PatientListModel.VIEWTYPE_HEADER));
                    for (PatientResponseModel.ConnectionRequest model : res.body().getConnectionRequests()) {
                        patientListViewModel.getPatientList().getValue().add(new PatientListModel(model.getParentName(), String.valueOf(model.getAge()), model.getRequesterId(), model.getReqId(), PatientListModel.VIEWTYPE_REQUEST));
                    }

                    if (!res.body().getInChargeList().isEmpty())
                        patientListViewModel.getPatientList().getValue().add(new PatientListModel("환자목록 ( " + res.body().getInChargeList().size() + " )", PatientListModel.VIEWTYPE_HEADER));

                    for (PatientResponseModel.InChargeList model : res.body().getInChargeList()) {
                        patientListViewModel.getPatientList().getValue().add(new PatientListModel(model.getName(), String.valueOf(model.getAge()), model.getDaughter(), model.getId(), PatientListModel.VIEWTYPE_PATIENT));
                    }

                }, err -> {
                    Toast.makeText(getBaseContext(), "인터넷 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                }));

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