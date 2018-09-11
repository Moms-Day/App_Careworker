package momsday.careworker.ui.main


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

import momsday.careworker.R
import momsday.careworker.adapter.WriteFormPatientListAdapter
import momsday.careworker.databinding.FragmentWriteFormBinding
import momsday.careworker.model.PatientListModel
import momsday.careworker.model.WriteFormListModel
import momsday.careworker.ui.writeForm.WriteFormActivity
import momsday.careworker.util.DataBindingFragment
import momsday.careworker.viewModel.PatientListViewModel

/**
 * A simple [Fragment] subclass.
 */
class WriteFormFragment : DataBindingFragment<FragmentWriteFormBinding>() {

    val patientListViewModel: PatientListViewModel by lazy{
        ViewModelProviders.of(activity!!).get(PatientListViewModel::class.java)
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_write_form
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.writeFormAllCardView.setOnClickListener { v ->
            val intent = Intent(context, WriteFormActivity::class.java)
            intent.putExtra("isAll", true)
            startActivity(intent)
        }

        patientListViewModel.patientList.observe(this, Observer{ res ->
            val adapter = WriteFormPatientListAdapter(patientListViewModel.patientList.value!!.filter { it.viewType == PatientListModel.VIEWTYPE_PATIENT } as ArrayList<PatientListModel>)

            binding.rvWriteFormPatientList.adapter = adapter
            binding.rvWriteFormPatientList.hasFixedSize()
            binding.rvWriteFormPatientList.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        })
        return view
    }

}// Required empty public constructor
