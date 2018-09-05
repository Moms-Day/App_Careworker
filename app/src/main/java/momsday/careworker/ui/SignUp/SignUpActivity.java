package momsday.careworker.ui.SignUp;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.Toast;

import momsday.careworker.adapter.SignUpPagerAdapter;
import momsday.careworker.R;
import momsday.careworker.util.DataBindingActivity;
import momsday.careworker.databinding.ActivitySignUpBinding;

public class SignUpActivity extends DataBindingActivity<ActivitySignUpBinding> {

    SignUpViewModel viewModel;

    SignUpFirstFragment signUpFirstFragment;
    SignUpSecondFragment signUpSecondFragment;
    SignUpThirdFragment signUpThirdFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
//        viewModel.getResCode().observe(this, (res) -> {
//            Toast.makeText(getBaseContext(), "" + res, Toast.LENGTH_SHORT).show();
//        });
        init();

        binding.viewPagerSignUp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        binding.buttonSignUpNext.setText("다음");
                        binding.buttonSignUpNext.setOnClickListener(v -> {
//                            Toast.makeText(getBaseContext(), signUpFirstFragment.getSignUpId(), Toast.LENGTH_SHORT).show();
                            binding.viewPagerSignUp.setCurrentItem(1);
                        });
                        break;
                    case 1:
                        binding.buttonSignUpNext.setText("다음");
                        binding.buttonSignUpNext.setOnClickListener(v -> binding.viewPagerSignUp.setCurrentItem(2));
                        break;
                    case 2:
                        binding.buttonSignUpNext.setText("회원가입");
                        binding.buttonSignUpNext.setOnClickListener(v -> {

                            viewModel.signUp();

//                            Intent intent = new Intent(SignUpActivity.this, StartActivity.class);
//                            startActivity(intent);
                        });
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                binding.indicatorSignUp.selectDot(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    void init() {
        signUpFirstFragment = new SignUpFirstFragment();
        signUpSecondFragment = new SignUpSecondFragment();
        signUpThirdFragment = new SignUpThirdFragment();

        SignUpPagerAdapter signUpPagerAdapter = new SignUpPagerAdapter(getSupportFragmentManager());
        binding.viewPagerSignUp.setAdapter(signUpPagerAdapter);
        binding.viewPagerSignUp.setCurrentItem(0);
        binding.indicatorSignUp.setItemMargin(15);
        binding.indicatorSignUp.setAnimDuration(300);
        binding.indicatorSignUp.createDotPanel(3, R.drawable.ic_indicator_non, R.drawable.ic_indicator_on);
    }
}
