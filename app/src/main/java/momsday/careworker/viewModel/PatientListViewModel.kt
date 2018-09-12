package momsday.careworker.viewModel

import android.arch.lifecycle.MutableLiveData

import java.util.ArrayList

import momsday.careworker.model.PatientListModel
import momsday.careworker.util.DisposableViewModel

class PatientListViewModel :DisposableViewModel() {
    val list = arrayListOf<PatientListModel>()
    val patientList = MutableLiveData<ArrayList<PatientListModel>>()
    fun addList(patientListModel: PatientListModel){
        list.add(patientListModel)
        patientList.postValue(list)
    }
}
