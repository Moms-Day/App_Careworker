package momsday.careworker.ui.writeForm


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonObject

import momsday.careworker.R
import momsday.careworker.databinding.FragmentAddScheduleBinding
import momsday.careworker.model.ScheduleModel
import momsday.careworker.util.DataBindingFragment
import momsday.careworker.viewModel.PatientListViewModel
import org.jetbrains.anko.sdk25.coroutines.onClick

class AddScheduleFragment : DataBindingFragment<FragmentAddScheduleBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_add_schedule

    val writeFormViewModel by lazy { ViewModelProviders.of(activity!!)[WriteFormViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.addScheduleSubmit.onClick {
            val startMin = binding.addScheduleStartMin.text
            val startHour = binding.addScheduleStartHour.text
            val endMin = binding.addScheduleEndMin.text
            val endHour = binding.addScheduleEndHour.text
            val work = binding.addScheduleActEt.text
            writeFormViewModel.addSchedule(ScheduleModel(startTime = "${startHour}:$startMin", endTime = "$endHour:$endMin", info = work.toString()))
            activity!!.supportFragmentManager.popBackStack()
            Log.d("와 슈발 졸라잘돼", "그러네~~")
        }

        writeFormViewModel.schedule.observe(this, Observer {
            Log.d("와 슈발 졸라잘돼", "와 여기선 되는데")
        })
        return view
    }


}
