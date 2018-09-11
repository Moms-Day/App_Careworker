package momsday.careworker.ui.writeForm


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import momsday.careworker.R
import momsday.careworker.databinding.FragmentAddMealBinding
import momsday.careworker.util.DataBindingFragment


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

        return view
    }

}// Required empty public constructor
