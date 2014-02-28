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
import android.view.View;

import it.robol.android.piggybank.data.DataProvider;

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

    /**
     * The ID currently displayed in the detail view when the two-pane mode is active.
     */
    private long mCurrentId = -1;

    private PlaceholderFragment mPlaceholderFragment;
	
	public boolean hasTwoPaneModeEnabled() {
		return mTwoPane;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        mPlaceholderFragment = new PlaceholderFragment();

        setContentView(R.layout.activity_main_list);

        // You may uncomment this to force two-pane mode on a phone, for debugging purpose.
        // setContentView(R.layout.activity_main_twopane);

		// Setup the ActionBar to navigate through
		ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

		if (findViewById(R.id.detail_container) != null) {
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
	public void onItemSelected(MasterFragment source, long id) {

        Bundle extras = new Bundle();
        extras.putLong("ID", id);

        mCurrentId = id;

        if (mTwoPane) {
            try {
                Fragment detailFragment = (Fragment) source.getDetailFragmentClass().newInstance();
                detailFragment.setArguments(extras);

                getSupportFragmentManager().beginTransaction().replace(R.id.detail_container,
                        detailFragment).commit();
            } catch (Exception e) {
                Log.d(LOG_TAG, "Failed to load the DetailFragment for " + source.toString());
            }
        }
        else {
            Intent intent = new Intent(this, source.getDetailActivityClass());
            intent.putExtras(extras);
            startActivity(intent);
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

    /**
     * Load the {@link PlaceholderFragment} when we are in the two-pane view and there is nothing
     * to do. In the classic smartphone view, this is a no-op.
     */
    public void loadPlaceholderFragment () {
        if (mTwoPane) {
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container,
                    mPlaceholderFragment).commit();

            mCurrentId = -1;
        }
    }

    public void onDeleteAccountClicked (View view) {
        DataProvider.getInstance(this).getAccountsManager().removeAccount(mCurrentId);
        loadPlaceholderFragment();
    }
		
}
