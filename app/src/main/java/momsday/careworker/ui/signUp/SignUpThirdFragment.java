package momsday.careworker.ui.signUp;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

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

        binding.btnSignUp3PhoneAuth.setOnClickListener(v -> {
            int certCharLength = 8;
            final char[] characterTable = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                    'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                    'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
            Random random = new Random(System.currentTimeMillis());
            int tablelength = characterTable.length;
            StringBuffer buf = new StringBuffer();

            for (int i = 0; i < certCharLength; i++) {
                buf.append(characterTable[random.nextInt(tablelength)]);
            }
            viewModel.getVerify().setValue(buf.toString());
        });

        return view;
    }
}
