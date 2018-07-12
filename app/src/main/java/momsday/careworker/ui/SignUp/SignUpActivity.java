package momsday.careworker.ui.SignUp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import momsday.careworker.R;
import momsday.careworker.ui.Start.StartActivity;
import momsday.careworker.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding r;

    SignUpFirstFragment signUpFirstFragment;
    SignUpSecondFragment signUpSecondFragment;
    SignUpThirdFragment signUpThirdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        init();

        r.viewPagerSignUp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        r.buttonSignUpNext.setText("다음");
                        r.buttonSignUpNext.setOnClickListener(v -> {
                            Toast.makeText(getBaseContext(), signUpFirstFragment.getSignUpId(), Toast.LENGTH_SHORT).show();
                            r.viewPagerSignUp.setCurrentItem(1);
                        });
                        break;
                    case 1:
                        r.buttonSignUpNext.setText("다음");
                        r.buttonSignUpNext.setOnClickListener(v -> r.viewPagerSignUp.setCurrentItem(2));
                        break;
                    case 2:
                        r.buttonSignUpNext.setText("회원가입");
                        r.buttonSignUpNext.setOnClickListener(v -> {
                            Intent intent = new Intent(SignUpActivity.this, StartActivity.class);
                            startActivity(intent);
                        });
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                r.indicatorSignUp.selectDot(position);
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

        r.viewPagerSignUp.setAdapter(new SignUpPagerAdapter(getSupportFragmentManager()));
        r.viewPagerSignUp.setCurrentItem(0);
        r.viewPagerSignUp.setOnTouchListener((v, event) -> true);
        r.indicatorSignUp.setItemMargin(15);
        r.indicatorSignUp.setAnimDuration(300);
        r.indicatorSignUp.createDotPanel(3, R.drawable.ic_indicator_non, R.drawable.ic_indicator_on);
    }

    class SignUpPagerAdapter extends FragmentStatePagerAdapter {
        SignUpPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return signUpFirstFragment;
                case 1:
                    return signUpSecondFragment;
                case 2:
                    return signUpThirdFragment;
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
