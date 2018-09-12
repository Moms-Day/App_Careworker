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
import momsday.careworker.connecter.Connect
import momsday.careworker.databinding.FragmentAddMealBinding
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

    override fun getLayoutId() = R.layout.fragment_add_meal

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.addMealSubmitBtn.onClick {
            val req = JsonObject().apply {
                addProperty("breakfast" , addMeal_breakfirst_et.toText())
                addProperty("lunch" , addMeal_launch_et.toText())
                addProperty("dinner" , addMeal_dinner_et.toText())
                addProperty("snack" , addMeal_desert_et.toText())
                addProperty("pId",writeFormViewModel.patient.value!!.id)
            }
            Connect.getAPI().sendMeal(getToken(this@AddMealFragment.context!!,true),req ).enqueue( object: Callback<Void>{
                override fun onResponse(call: Call<Void>?, response: Response<Void>) {
                    Toast.makeText(this@AddMealFragment.context ,"${response.code()}",Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
        }
        return view
    }

}// Required empty public constructor
