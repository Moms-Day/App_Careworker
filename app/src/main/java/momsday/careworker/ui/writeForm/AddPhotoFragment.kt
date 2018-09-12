package momsday.careworker.ui.writeForm


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import momsday.careworker.R


/**
 * A simple [Fragment] subclass.
 */
class AddPhotoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_photo, container, false)
    }

}// Required empty public constructor
