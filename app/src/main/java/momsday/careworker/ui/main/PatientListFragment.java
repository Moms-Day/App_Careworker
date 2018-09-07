package momsday.careworker.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import momsday.careworker.adapter.PatientListAdapter;
import momsday.careworker.model.PatientListModel;
import momsday.careworker.R;
import momsday.careworker.databinding.FragmentPatientListBinding;
import momsday.careworker.util.DataBindingFragment;
import momsday.careworker.viewModel.PatientListViewModel;


public class PatientListFragment extends DataBindingFragment<FragmentPatientListBinding> {

    PatientListViewModel patientListViewModel;

    public PatientListFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_patient_list;
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        patientListViewModel = ViewModelProviders.of(getActivity()).get(PatientListViewModel.class);


        patientListViewModel.getPatientList().observe(this, (res) -> {
            PatientListAdapter adapter = new PatientListAdapter(patientListViewModel.getPatientList().getValue());
            binding.rvPatientListParent.setAdapter(adapter);
            binding.rvPatientListParent.setHasFixedSize(true);
            binding.rvPatientListParent.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter.notifyDataSetChanged();
        });

        return view;
    }
}
