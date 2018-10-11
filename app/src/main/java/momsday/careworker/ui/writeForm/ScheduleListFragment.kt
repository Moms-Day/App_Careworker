package momsday.careworker.ui.writeForm


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

import momsday.careworker.R
import momsday.careworker.adapter.ScheduleListAdapter
import momsday.careworker.connecter.Connect
import momsday.careworker.databinding.FragmentScheduleListBinding
import momsday.careworker.model.ScheduleListModel
import momsday.careworker.model.ScheduleModel
import momsday.careworker.util.DataBindingFragment
import momsday.careworker.util.getToken
import org.jetbrains.anko.sdk25.coroutines.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class ScheduleListFragment : DataBindingFragment<FragmentScheduleListBinding>() {

    val writeFormViewModel by lazy { ViewModelProviders.of(activity!!)[WriteFormViewModel::class.java] }

    var isPosted = false

    override fun getLayoutId() = R.layout.fragment_schedule_list

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val scheduleAdapter = ScheduleListAdapter(writeFormViewModel.schedule.value!!)
//        val scheduleAdapter = ScheduleListAdapter(ArrayList<ScheduleModel>())
        binding.scheduleRv.adapter = scheduleAdapter
        binding.scheduleRv.setHasFixedSize(true)
        binding.scheduleRv.layoutManager = LinearLayoutManager(context)

        Connect.getAPI().getSchedule(getToken(context!!, true), writeFormViewModel.patient.value!!.id).enqueue(object : Callback<ArrayList<ScheduleListModel>> {
            override fun onResponse(call: Call<ArrayList<ScheduleListModel>>?, response: Response<ArrayList<ScheduleListModel>>) {
                if (response.body()!!.isNotEmpty()) {
                    isPosted = true
                    response.body()!!.forEach {
                        writeFormViewModel.addSchedule(ScheduleModel(it.time, "", it.work))
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<ScheduleListModel>>?, t: Throwable?) {
//                Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show()
            }
        })

        writeFormViewModel.schedule.observe(this, Observer {
            binding.scheduleRv.adapter.notifyDataSetChanged()

            Log.d("와 슈발 졸라잘돼", "와 옵저버당")
            Log.d("와 슈발 졸라잘돼", "${writeFormViewModel.schedule.value}")
        })

        binding.scheduleAddBtn.onClick {
            writeFormViewModel.selectedFragment.value = "AddSchedule"
        }
        binding.scheduleSubmitBtn.onClick {
            val req = JsonObject()
            val schedules = JsonArray()
            writeFormViewModel.schedule.value!!.forEach {
                schedules.add(JsonObject().apply {
                    addProperty("start", it.startTime)
                    addProperty("end", it.endTime)
                    addProperty("work", it.info)
                })
            }
            req.addProperty("pId", writeFormViewModel.patient.value!!.id)
            req.add("schedules", schedules)
            if (!isPosted)
                Connect.getAPI().sendSchedule(getToken(this@ScheduleListFragment.context!!, true), req).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>) {
                        Log.d("ScheduleListFragment", "code: ${response.code()}")
                        writeFormViewModel.schedule.value!!.clear()
                        activity!!.supportFragmentManager.popBackStack()
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
//                        Log.d("ScheduleListFragment", "FAIL")
                    }

                })
            else
                Connect.getAPI().updateSchedule(getToken(this@ScheduleListFragment.context!!, true), req).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>) {
                        Log.d("ScheduleListFragment", "code: ${response.code()}")
                        writeFormViewModel.schedule.value!!.clear()
                        activity!!.supportFragmentManager.popBackStack()
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
//                        Log.d("ScheduleListFragment", "FAIL")
                    }

                })
        }
        return view
    }
}
