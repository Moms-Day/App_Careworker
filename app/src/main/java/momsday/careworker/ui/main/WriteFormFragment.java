package momsday.careworker.ui.main;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import momsday.careworker.ui.writeForm.WriteFormActivity;
import momsday.careworker.util.DataBindingFragment;
import momsday.careworker.viewModel.PatientListViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriteFormFragment extends DataBindingFragment<FragmentWriteFormBinding> {

    PatientListViewModel patientListViewModel;

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
        super.onCreateView(inflater, container, savedInstanceState);
        patientListViewModel = ViewModelProviders.of(getActivity()).get(PatientListViewModel.class);

        binding.writeFormAllCardView.setOnClickListener((v) -> {
            Intent intent = new Intent(getContext(), WriteFormActivity.class);
            intent.putExtra("isAll", true);
            startActivity(intent);
        });

        patientListViewModel.getPatientList().observe(this, (res) -> {
            WriteFormPatientListAdapter adapter = new WriteFormPatientListAdapter(patientListViewModel.getPatientList().getValue());
            binding.rvWriteFormPatientList.setAdapter(adapter);
            binding.rvWriteFormPatientList.hasFixedSize();
            binding.rvWriteFormPatientList.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter.notifyDataSetChanged();
        });
        return view;
    }

}
