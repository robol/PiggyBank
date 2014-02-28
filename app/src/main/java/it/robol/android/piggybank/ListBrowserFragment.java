package it.robol.android.piggybank;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListBrowserFragment extends Fragment {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_SELECTED_POSITION = "selected_position";
		
	private final static int ACCOUNTS_POSITION = 0;
	private final static int MOVEMENTS_POSITION = 1;
	
	private ViewPager mViewPager;	
	private TabsAdapter mTabsAdapter;
	
	private int selected_position = 0;
	
	public ListBrowserFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState != null && 
				savedInstanceState.containsKey(STATE_SELECTED_POSITION)) {
			 selected_position = savedInstanceState.getInt(STATE_SELECTED_POSITION);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mainView = inflater.inflate(R.layout.fragment_list_browser, null);
		
		ActionBarActivity activity = (ActionBarActivity) getActivity();
		ActionBar bar = activity.getSupportActionBar();
		
		mViewPager = (ViewPager) mainView.findViewById(R.id.view_pager);
		mTabsAdapter = new TabsAdapter(activity, mViewPager);
		
		mTabsAdapter.addTab(bar.newTab().setText("Accounts"), 
				AccountListFragment.class, null);
		
		mTabsAdapter.addTab(bar.newTab().setText("Movements"), 
				MovementListFragment.class, null);
		
		if (selected_position > 0) {
			mViewPager.setCurrentItem(selected_position);
		}
		
		return mainView;
	}
	
	public void setActivateOnItemClick(boolean activated) {
		for (int i = 0; i < mTabsAdapter.getCount(); i++) {
			((MasterFragment) mTabsAdapter.getItem(i)).setActivateOnItemClick(activated);
		}
	}
}
