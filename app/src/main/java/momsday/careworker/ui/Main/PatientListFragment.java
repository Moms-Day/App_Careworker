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

import momsday.careworker.Adapter.PatientListAdapter;
import momsday.careworker.Adapter.RequestListAdapter;
import momsday.careworker.Model.PatientListModel;
import momsday.careworker.Model.RequestListModel;
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
        // Inflaate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_patient_list, container, false);
        view = binding.getRoot();

        ArrayList<RequestListModel> requestList = new ArrayList<>();
        requestList.add(new RequestListModel("되냐??"));
        requestList.add(new RequestListModel("되냐고"));
        requestList.add(new RequestListModel("되냔말이야"));

        binding.rvPatientListRequest.setAdapter(new RequestListAdapter(requestList));
        binding.rvPatientListRequest.setHasFixedSize(true);
        binding.rvPatientListRequest.setLayoutManager(getUnScrollableLinearLayoutManager());

        ArrayList<PatientListModel> patientList = new ArrayList<>();
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야"));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야"));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야"));
        patientList.add(new PatientListModel("되냐?", "되냐고", "되냔말이야"));

        binding.rvPatientListParent.setAdapter(new PatientListAdapter(patientList));
        binding.rvPatientListParent.setHasFixedSize(true);
        binding.rvPatientListParent.setLayoutManager(getUnScrollableLinearLayoutManager());

        return view;
    }

    LinearLayoutManager getUnScrollableLinearLayoutManager() {
        return new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
    }
}
