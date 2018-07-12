package momsday.careworker.ui.Main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import momsday.careworker.Adapter.RequestListAdapter;
import momsday.careworker.Model.RequestListModel;
import momsday.careworker.R;
import momsday.careworker.databinding.FragmentPatientListBinding;

public class ChattingFragment extends Fragment {

    View view;

    public ChattingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chatting, container, false);
        return view;
    }


}
