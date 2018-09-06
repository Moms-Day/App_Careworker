package momsday.careworker.ui.signUp;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import momsday.careworker.R;
import momsday.careworker.util.DataBindingFragment;
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
        super.onCreateView(inflater,container,savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SignUpViewModel.class);
        binding.setVm(viewModel);
        return view;
    }
}
