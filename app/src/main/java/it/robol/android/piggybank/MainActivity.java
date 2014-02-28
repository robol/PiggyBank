package it.robol.android.piggybank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
 */
public class MainActivity extends ActionBarActivity implements
		MasterCallbacks {

    private static final String LOG_TAG = "MainActivity";

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

        if (savedInstanceState != null && savedInstanceState.containsKey(ListBrowserFragment.STATE_SELECTED_POSITION)) {
            int position = savedInstanceState.getInt(ListBrowserFragment.STATE_SELECTED_POSITION);

            ListBrowserFragment fragment = (ListBrowserFragment)
                    getSupportFragmentManager().getFragment(Bundle.EMPTY, ListBrowserFragment.TAG);
            fragment.setCurrentPosition(ListBrowserFragment.ACCOUNTS_POSITION);

        }
	}

	/**
	 * Callback method from {@link MasterCallbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(MasterFragment source, String id) {
        Log.d(LOG_TAG, "Selected id = " + id);

        if (mTwoPane) {
            // Still to implement this
        }
        else {
            Bundle extras = new Bundle();
            extras.putString("ID", id);
            Intent newIntent = new Intent(this, source.getDetailActivityClass());
            newIntent.putExtras(extras);
            startActivity(newIntent);
        }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_account:
                // TODO: We need to handle the case where the user is in tablet view.
                startActivity(new Intent(this, NewAccountActivity.class));
                return true;
        }
        return false;
	}
		
}
