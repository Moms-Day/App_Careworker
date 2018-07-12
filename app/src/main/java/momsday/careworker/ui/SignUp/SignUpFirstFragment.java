package momsday.careworker.ui.SignUp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import momsday.careworker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFirstFragment extends Fragment {

    View view;

    public SignUpFirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up_first, container, false);
        return view;
    }

    public String getSignUpId() {
        return ((EditText) view.findViewById(R.id.et_signUp1_id)).getText().toString();
    }

    public String getSignUpPw() {
        return ((EditText) view.findViewById(R.id.et_signUp1_pw)).getText().toString();
    }

}
