package it.robol.android.piggybank;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListBrowserFragment extends Fragment {
	
	private ViewPager mViewPager;	
	private TabsAdapter mTabsAdapter;
	
	public ListBrowserFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		
		return mainView;
	}
	
}
