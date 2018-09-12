package momsday.careworker.ui.writeForm


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import momsday.careworker.R
import momsday.careworker.databinding.FragmentSelectFormBinding
import momsday.careworker.util.DataBindingFragment
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * A simple [Fragment] subclass.
 */
class SelectFormFragment : DataBindingFragment<FragmentSelectFormBinding>() {

    val viewModel by lazy {
        ViewModelProviders.of(activity!!)[WriteFormViewModel::class.java]
    }

    override fun getLayoutId() = R.layout.fragment_select_form

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)

        binding.selectFormHealthImg.onClick {
            viewModel.selectedFragment.value = "Health"
        }
        binding.selectFormScheduleImg.onClick {
            viewModel.selectedFragment.value = "Schedule"
        }
        binding.selectFormMealImg.onClick {
            viewModel.selectedFragment.value = "Meal"
        }
        return view
    }

}
