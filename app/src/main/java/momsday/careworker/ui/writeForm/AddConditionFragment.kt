package momsday.careworker.ui.writeForm


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject

import momsday.careworker.R
import momsday.careworker.connecter.Connect
import momsday.careworker.databinding.FragmentAddConditionBinding
import momsday.careworker.model.ConditionModel
import momsday.careworker.util.DataBindingFragment
import momsday.careworker.util.getToken
import org.jetbrains.anko.sdk25.coroutines.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class AddConditionFragment : DataBindingFragment<FragmentAddConditionBinding>() {

    val writeFormViewModel by lazy {
        ViewModelProviders.of(activity!!).get(WriteFormViewModel::class.java)
    }

    var isPosted = false

    override fun getLayoutId() = R.layout.fragment_add_condition

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        Connect.getAPI().getCondition(getToken(context!!, true), writeFormViewModel.patient.value!!.id).enqueue(object : Callback<ConditionModel> {
            override fun onResponse(call: Call<ConditionModel>?, response: Response<ConditionModel>) {
                val conditionModel = response.body()!!
                isPosted = conditionModel.isConnected()
                Log.d("AddConditionFragment", "isPosted: $isPosted")
                binding.addConditionActivityReduce.isChecked = conditionModel.activity_reduction
                binding.addConditionLowTemp.isChecked = conditionModel.low_temperature
                binding.addConditionHighFever.isChecked = conditionModel.high_fever
                binding.addConditionBloodHigh.isChecked = conditionModel.blood_pressure_increase
                binding.addConditionBloodLow.isChecked = conditionModel.blood_pressure_reduction
                binding.addConditionLowSleep.isChecked = conditionModel.lack_of_sleep
                binding.addConditionLowEat.isChecked = conditionModel.lose_Appetite
                binding.addConditionHighEat.isChecked = conditionModel.binge_eating
                binding.addConditionDiarrhea.isChecked = conditionModel.diarrhea
                binding.addConditionConstipation.isChecked = conditionModel.constipation
                binding.addConditionVomiting.isChecked = conditionModel.vomiting
                binding.addConditionUrinationInconvenient.isChecked = conditionModel.urination_inconvenient
                binding.addConditionPowerReduction.isChecked = conditionModel.human_power_reduction
                binding.addConditionPovertyOfBlood.isChecked = conditionModel.poverty_of_blood
                binding.addConditionCough.isChecked = conditionModel.cough
            }

            override fun onFailure(call: Call<ConditionModel>?, t: Throwable?) {
                Toast.makeText(context!!, "FAIL?", Toast.LENGTH_SHORT).show()
            }

        })

        binding.addConditionSubmit.onClick {
            val req = JsonObject().apply {
                addProperty("pId", writeFormViewModel.patient.value!!.id)
                addProperty("activity_reduction", binding.addConditionActivityReduce.isChecked)
                addProperty("low_temperature", binding.addConditionLowTemp.isChecked)
                addProperty("high_fever", binding.addConditionHighFever.isChecked)
                addProperty("blood_pressure_increase", binding.addConditionBloodHigh.isChecked)
                addProperty("blood_pressure_reduction", binding.addConditionBloodLow.isChecked)
                addProperty("lack_of_sleep", binding.addConditionLowSleep.isChecked)
                addProperty("lose_Appetite", binding.addConditionLowEat.isChecked)
                addProperty("binge_eating", binding.addConditionHighEat.isChecked)
                addProperty("diarrhea", binding.addConditionDiarrhea.isChecked)
                addProperty("constipation", binding.addConditionConstipation.isChecked)
                addProperty("vomiting", binding.addConditionVomiting.isChecked)
                addProperty("urination_inconvenient", binding.addConditionUrinationInconvenient.isChecked)
                addProperty("human_power_reduction", binding.addConditionPowerReduction.isChecked)
                addProperty("poverty_of_blood", binding.addConditionPovertyOfBlood.isChecked)
                addProperty("cough", binding.addConditionCough.isChecked)
            }

            if (!isPosted) {
                Connect.getAPI().sendCondition(getToken(this@AddConditionFragment.context!!, true), req).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>) {
                        Toast.makeText(this@AddConditionFragment.context!!, "전송되었습니다.", Toast.LENGTH_SHORT).show()
                        activity!!.supportFragmentManager.popBackStack()
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {

                    }

                })
            } else {
                Connect.getAPI().updateCondition(getToken(this@AddConditionFragment.context!!, true), req).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>) {
                        Toast.makeText(this@AddConditionFragment.context!!, "전송되었습니다.", Toast.LENGTH_SHORT).show()
                        activity!!.supportFragmentManager.popBackStack()
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {

                    }

                })
            }
        }
        return view
    }

}// Required empty public constructor
