package momsday.careworker.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import momsday.careworker.ui.SignUp.SignUpFirstFragment;
import momsday.careworker.ui.SignUp.SignUpSecondFragment;
import momsday.careworker.ui.SignUp.SignUpThirdFragment;

public class SignUpPagerAdapter extends FragmentStatePagerAdapter {
    public SignUpPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SignUpFirstFragment();
            case 1:
                return new SignUpSecondFragment();
            case 2:
                return new SignUpThirdFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
