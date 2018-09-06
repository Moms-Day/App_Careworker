package momsday.careworker.ui.signUp;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import momsday.careworker.R;
import momsday.careworker.util.DataBindingFragment;
import momsday.careworker.databinding.FragmentSignUpFirstBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFirstFragment extends DataBindingFragment<FragmentSignUpFirstBinding> {

    public SignUpFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sign_up_first;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        SignUpViewModel signUpViewModel = ViewModelProviders.of(getActivity()).get(SignUpViewModel.class);
        binding.setVm(signUpViewModel);

        return view;
    }

    public int getSignUpId() {
        return Integer.parseInt(binding.etSignUp1Id.toString());
    }


}
