package momsday.careworker.ui.Main;

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
import momsday.careworker.adapter.RequestListAdapter;
import momsday.careworker.model.PatientListModel;
import momsday.careworker.model.RequestListModel;
import momsday.careworker.R;
import momsday.careworker.databinding.FragmentPatientListBinding;


public class PatientListFragment extends Fragment {

    View view;
    FragmentPatientListBinding binding;

    public PatientListFragment() {
        // Required empty public constructor
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_patient_list, container, false);
        view = binding.getRoot();

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

        binding.rvPatientListParent.setAdapter(new PatientListAdapter(patientList));
        binding.rvPatientListParent.setHasFixedSize(true);
        binding.rvPatientListParent.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
