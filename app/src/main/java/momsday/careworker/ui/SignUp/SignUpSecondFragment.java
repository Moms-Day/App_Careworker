package momsday.careworker.ui.SignUp;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import momsday.careworker.R;
import momsday.careworker.util.DataBindingFragment;
import momsday.careworker.databinding.FragmentSignUpSecondBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpSecondFragment extends DataBindingFragment<FragmentSignUpSecondBinding> {

    SignUpViewModel viewModel;

    public SignUpSecondFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sign_up_second;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SignUpViewModel.class);
        binding.setVm(viewModel);

        viewModel.getName().observe(this, (s) -> {
            Toast.makeText(getContext(), viewModel.getId().getValue(), Toast.LENGTH_SHORT).show();
        });
        return view;
    }

}
