package momsday.careworker.viewModel;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

import momsday.careworker.model.PatientListModel;
import momsday.careworker.util.DisposableViewModel;

public class PatientListViewModel extends DisposableViewModel {
    MutableLiveData<ArrayList<PatientListModel>> patientList = new MutableLiveData<>();

    public MutableLiveData<ArrayList<PatientListModel>> getPatientList() {
        return patientList;
    }

}
