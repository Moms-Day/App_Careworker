package momsday.careworker.viewModel

import android.arch.lifecycle.MutableLiveData

import java.util.ArrayList

import momsday.careworker.model.PatientListModel
import momsday.careworker.util.DisposableViewModel
import momsday.careworker.util.SingleLiveEvent

class PatientListViewModel : DisposableViewModel() {
    val list = arrayListOf<PatientListModel>()
    val patientList = MutableLiveData<ArrayList<PatientListModel>>()
    val patientListChangeRequestEvent = SingleLiveEvent<Any>()
    fun addList(patientListModel: PatientListModel) {
        list.add(patientListModel)
        patientList.postValue(list)
    }
    fun clearList(){
        list.clear()
        patientList.postValue(list)
    }
}
