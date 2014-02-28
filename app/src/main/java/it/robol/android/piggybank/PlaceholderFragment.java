package it.robol.android.piggybank;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Simple {@link Fragment} that can be loaded in the Details view of the
 * two-pane interface when there is nothing important to show.
 */
public class PlaceholderFragment extends Fragment {

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup view, Bundle savedInstanceState) {
        return new FrameLayout(getActivity());
    }


}
