package momsday.careworker.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import momsday.careworker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriteFormFragment extends Fragment {


    public WriteFormFragment() {
        // Required empty public constructor
    }


    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_write_form, container, false);
    }

}
