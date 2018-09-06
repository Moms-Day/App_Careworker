package momsday.careworker.ui.main;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import momsday.careworker.R;
import momsday.careworker.adapter.WriteFormPatientListAdapter;
import momsday.careworker.databinding.FragmentWriteFormBinding;
import momsday.careworker.model.WriteFormListModel;
import momsday.careworker.util.DataBindingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriteFormFragment extends DataBindingFragment<FragmentWriteFormBinding> {



    public WriteFormFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_write_form;
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
