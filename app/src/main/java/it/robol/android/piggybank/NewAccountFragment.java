package it.robol.android.piggybank;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewAccountFragment extends Fragment {
	
	public static final String TAG = "NewAccountFragment";

    public NewAccountFragment () {}
	
	public View onCreateView (LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
        Log.d(TAG, "Inflating the requested View");
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_new_account, null);
	}

}
