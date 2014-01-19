package it.robol.android.piggybank;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

public class AccountListFragment extends ListFragment {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setListAdapter(new AccountsAdapter(getActivity()));
	}
	
}
