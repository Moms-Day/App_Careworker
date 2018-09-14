package momsday.careworker.ui.writeForm


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject

import momsday.careworker.R
import momsday.careworker.connecter.Connect
import momsday.careworker.databinding.FragmentAddConditionBinding
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


    override fun getLayoutId() = R.layout.fragment_add_condition

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

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
            /*
            * low_temperature = BooleanField(default=False) # 저체온
    high_fever = BooleanField(default=False) # 고열
    blood_pressure_increase = BooleanField(default=False) # 고혈압
    blood_pressure_reduction = BooleanField(default=False) # 저혈압
    lack_of_sleep = BooleanField(default=False) # 수면부족
    lose_Appetite = BooleanField(default=False) # 식욕 감퇴
    binge_eating = BooleanField(default=False) # 폭식
    diarrhea = BooleanField(default=False) # 설사
    constipation = BooleanField(default=False) # 변비
    vomiting = BooleanField(default=False) # 구토
    urination_inconvenient = BooleanField(default=False) # 배뇨활동 불편
    human_power_reduction = BooleanField(default=False) # 인지력 감퇴
    poverty_of_blood = BooleanField(default=False) # 빈혈
    cough = BooleanField(default=False) # 기침
            * */
            Connect.getAPI().sendCondition(getToken(this@AddConditionFragment.context!!, true), req).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>?, response: Response<Void>) {
                    Toast.makeText(this@AddConditionFragment.context!!, "전송되었습니다.", Toast.LENGTH_SHORT).show()
                    activity!!.supportFragmentManager.popBackStack()
                }

                override fun onFailure(call: Call<Void>?, t: Throwable?) {

                }

            })
        }
        return view
    }

}// Required empty public constructor
