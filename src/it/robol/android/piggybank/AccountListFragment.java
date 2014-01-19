package it.robol.android.piggybank;

import java.lang.Class;
import android.os.Bundle;

public class AccountListFragment extends MasterFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setListAdapter(new AccountsAdapter(getActivity()));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getDetailFragmentClass() {
		return MovementDetailFragment.class;
	}
	
}
