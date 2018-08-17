package momsday.careworker.ui.SignUp;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import momsday.careworker.R;
import momsday.careworker.Util.DataBindingFragment;
import momsday.careworker.databinding.FragmentSignUpThirdBinding;

public class SignUpThirdFragment extends DataBindingFragment<FragmentSignUpThirdBinding> {

    SignUpViewModel viewModel;

    public SignUpThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sign_up_third;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getActivity()).get(SignUpViewModel.class);
        binding.setVm(viewModel);
        return view;
    }
}
