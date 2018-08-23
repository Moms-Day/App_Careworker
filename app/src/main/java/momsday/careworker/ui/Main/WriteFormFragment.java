package momsday.careworker.ui.Main;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import momsday.careworker.R;
import momsday.careworker.adapter.WriteFormPatientListAdapter;
import momsday.careworker.databinding.FragmentWriteFormBinding;
import momsday.careworker.model.WriteFormListModel;
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
        ArrayList<WriteFormListModel> model = new ArrayList<>();
        model.add(new WriteFormListModel("전체"));
        model.add(new WriteFormListModel("ㅇㅇ"));
        model.add(new WriteFormListModel("ㅁㅁ"));
        model.add(new WriteFormListModel("ㅂㅂ"));
        WriteFormPatientListAdapter adapter = new WriteFormPatientListAdapter(model);
        binding.rvWriteFormPatientList.setAdapter(adapter);
        binding.rvWriteFormPatientList.hasFixedSize();
        binding.rvWriteFormPatientList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}
