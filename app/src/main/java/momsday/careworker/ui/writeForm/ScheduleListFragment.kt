package momsday.careworker.ui.writeForm


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import momsday.careworker.R
import momsday.careworker.adapter.ScheduleListAdapter
import momsday.careworker.databinding.FragmentScheduleListBinding
import momsday.careworker.model.ScheduleModel
import momsday.careworker.util.DataBindingFragment
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * A simple [Fragment] subclass.
 */
class ScheduleListFragment : DataBindingFragment<FragmentScheduleListBinding>() {

    val writeFormViewModel by lazy { ViewModelProviders.of(activity!!)[WriteFormViewModel::class.java] }

    override fun getLayoutId() = R.layout.fragment_schedule_list

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
//        val scheduleAdapter = ScheduleListAdapter(writeFormViewModel.schedule.value!!)
        val scheduleAdapter = ScheduleListAdapter(ArrayList<ScheduleModel>())
        binding.scheduleRv.adapter = scheduleAdapter

        writeFormViewModel.schedule.observe(this, Observer {
            scheduleAdapter.notifyDataSetChanged()
        })

        binding.scheduleAddBtn.onClick {
            writeFormViewModel.selectedFragment.value = "AddSchedule"
        }
        return view
    }

}
