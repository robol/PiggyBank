package it.robol.android.piggybank;

import java.lang.Class;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class AccountListFragment extends MasterFragment {
	
	public final String LOG_TAG = "AccountListFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setListAdapter(new AccountsAdapter(getActivity()));
	}

	@Override
	public Class<?> getDetailFragmentClass() {
		return MovementDetailFragment.class;
	}
	
	public void onListItemClick (ListView listView, View view, int position, long id) {
		Log.d(LOG_TAG, "Clicked on Account = " + id);
	}
	
}
