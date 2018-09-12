package momsday.careworker.ui.writeForm

import android.arch.lifecycle.MutableLiveData
import momsday.careworker.model.PatientListModel
import momsday.careworker.model.ScheduleModel
import momsday.careworker.util.DisposableViewModel

class WriteFormViewModel : DisposableViewModel() {
    val list = arrayListOf<ScheduleModel>()

    val patient = MutableLiveData<PatientListModel>()

    val schedule = MutableLiveData<ArrayList<ScheduleModel>>().apply { postValue(list) }

    val selectedFragment = MutableLiveData<String>()

    fun addSchedule(model: ScheduleModel) {
        list.add(model)
        schedule.postValue(list)
    }

}