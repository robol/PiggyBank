package it.robol.android.piggybank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

/**
 * An activity representing a list of Movements. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link MovementDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link MovementListFragment} and the item details (if present) is a
 * {@link MovementDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link MovementListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class MainActivity extends ActionBarActivity implements
		MasterCallbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	
	public boolean hasTwoPaneModeEnabled() {
		return mTwoPane;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_movement_list);

		// Setup the ActionBar to navigate through 
		ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

		if (findViewById(R.id.movement_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;
		}
	}

	/**
	 * Callback method from {@link MovementListFragment.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		@SuppressWarnings("rawtypes")
		Class detailClass = MovementDetailFragment.class;
		
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(MovementDetailFragment.ARG_ITEM_ID, id);
			Fragment fragment;
			
			try {
				fragment = (Fragment) detailClass.newInstance();				
				fragment.setArguments(arguments);
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.movement_detail_container, fragment).commit();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, detailClass);
			detailIntent.putExtra(MovementDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
