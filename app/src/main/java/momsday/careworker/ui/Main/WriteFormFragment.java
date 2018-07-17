package momsday.careworker.ui.Main;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import momsday.careworker.R;
import momsday.careworker.databinding.FragmentWriteFormBinding;
import momsday.careworker.ui.Login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriteFormFragment extends Fragment {

    View view;
    FragmentWriteFormBinding binding;


    public WriteFormFragment() {
        // Required empty public constructor
    }


    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_write_form, container, false);
        view = binding.getRoot();
        binding.buttonWriteFormAll.setOnClickListener((v) -> {
            getContext().startActivity(new Intent(getContext(), LoginActivity.class));
        });
        binding.buttonWriteFormChoose.setOnClickListener(v -> {

        });
        return view;
    }

}
