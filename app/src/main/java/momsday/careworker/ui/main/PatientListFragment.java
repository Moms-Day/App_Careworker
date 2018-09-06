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

import momsday.careworker.adapter.PatientListAdapter;
import momsday.careworker.model.PatientListModel;
import momsday.careworker.R;
import momsday.careworker.databinding.FragmentPatientListBinding;
import momsday.careworker.util.DataBindingFragment;


public class PatientListFragment extends DataBindingFragment<FragmentPatientListBinding> {

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

        ArrayList<PatientListModel> patientList = new ArrayList<>();
        patientList.add(new PatientListModel("요청 목록(3)", PatientListModel.VIEWTYPE_HEADER));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야", PatientListModel.VIEWTYPE_REQUEST));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야", PatientListModel.VIEWTYPE_REQUEST));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야", PatientListModel.VIEWTYPE_REQUEST));
        patientList.add(new PatientListModel("노부모 목록(3)", PatientListModel.VIEWTYPE_HEADER));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야", PatientListModel.VIEWTYPE_PATIENT));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야", PatientListModel.VIEWTYPE_PATIENT));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야", PatientListModel.VIEWTYPE_PATIENT));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야", PatientListModel.VIEWTYPE_PATIENT));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야", PatientListModel.VIEWTYPE_PATIENT));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야", PatientListModel.VIEWTYPE_PATIENT));

        binding.rvPatientListParent.setAdapter(new PatientListAdapter(patientList));
        binding.rvPatientListParent.setHasFixedSize(true);
        binding.rvPatientListParent.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
