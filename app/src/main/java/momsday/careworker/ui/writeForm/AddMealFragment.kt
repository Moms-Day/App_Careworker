package momsday.careworker.ui.writeForm


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_add_meal.*

import momsday.careworker.R
import momsday.careworker.R.id.addMeal_breakfirst_et
import momsday.careworker.connecter.Connect
import momsday.careworker.databinding.FragmentAddMealBinding
import momsday.careworker.model.MealModel
import momsday.careworker.model.ScheduleListModel
import momsday.careworker.util.DataBindingFragment
import momsday.careworker.util.getToken
import momsday.careworker.util.toText
import org.jetbrains.anko.sdk25.coroutines.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class AddMealFragment : DataBindingFragment<FragmentAddMealBinding>() {

    val writeFormViewModel by lazy {
        ViewModelProviders.of(activity!!).get(WriteFormViewModel::class.java)
    }
    var isPosted = false
    override fun getLayoutId() = R.layout.fragment_add_meal

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        Connect.getAPI().getMeal(getToken(context!!, true), writeFormViewModel.patient.value!!.id).enqueue(object : Callback<MealModel> {
            override fun onResponse(call: Call<MealModel>?, response: Response<MealModel>?) {
                val body = response!!.body()!!
                if (body.breakfast.isNotEmpty() || body.dinner.isNotEmpty() || body.lunch.isNotEmpty() || body.snack.isNotEmpty()) {
                    isPosted = true
                    body.breakfast.forEach { addMeal_breakfirst_et.text.append(it + "\n") }
                    body.lunch.forEach { addMeal_launch_et.text.append(it + "\n") }
                    body.dinner.forEach { addMeal_dinner_et.text.append(it + "\n") }
                    addMeal_desert_et.setText(body.snack)
                } else {
                    Toast.makeText(context, "입력하세요 ", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MealModel>?, t: Throwable?) {
                Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
            }

        })
        binding.addMealSubmitBtn.onClick {
            val req = JsonObject().apply {
                addProperty("breakfast", addMeal_breakfirst_et.toText())
                addProperty("lunch", addMeal_launch_et.toText())
                addProperty("dinner", addMeal_dinner_et.toText())
                addProperty("snack", addMeal_desert_et.toText())
                addProperty("pId", writeFormViewModel.patient.value!!.id)
            }
            if (!isPosted)
                Connect.getAPI().sendMeal(getToken(this@AddMealFragment.context!!, true), req).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>) {
                        Toast.makeText(this@AddMealFragment.context, "성공했습니다", Toast.LENGTH_SHORT).show()
                        addMeal_breakfirst_et.text.clear()
                        addMeal_launch_et.text.clear()
                        addMeal_dinner_et.text.clear()
                        addMeal_desert_et.text.clear()
                        activity!!.supportFragmentManager.popBackStack()
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                    }

                })
            else
                Connect.getAPI().updateMeal(getToken(this@AddMealFragment.context!!, true), req).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>) {
                        Toast.makeText(this@AddMealFragment.context, "성공했습니다", Toast.LENGTH_SHORT).show()
                        addMeal_breakfirst_et.text.clear()
                        addMeal_launch_et.text.clear()
                        addMeal_dinner_et.text.clear()
                        addMeal_desert_et.text.clear()
                        activity!!.supportFragmentManager.popBackStack()
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                    }

                })

        }
        return view
    }

    override fun onPause() {
        super.onPause()
        addMeal_breakfirst_et.text.clear()
        addMeal_launch_et.text.clear()
        addMeal_dinner_et.text.clear()
        addMeal_desert_et.text.clear()
    }
}// Required empty public constructor
