package momsday.careworker.ui.writeForm

import android.arch.lifecycle.MutableLiveData
import momsday.careworker.model.PatientListModel
import momsday.careworker.model.ScheduleModel
import momsday.careworker.util.DisposableViewModel

class WriteFormViewModel : DisposableViewModel() {

    val patient = MutableLiveData<PatientListModel>()

    val schedule = MutableLiveData<ArrayList<ScheduleModel>>()

    val selectedFragment = MutableLiveData<String>()

}