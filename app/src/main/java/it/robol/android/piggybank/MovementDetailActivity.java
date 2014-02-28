package it.robol.android.piggybank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

/**
 * An activity representing a single Movement detail screen. This activity is
 * only used on handset devices. On tablet-size devices, item details are
 * presented side-by-side with a list of items in a {@link MovementListActivity}
 * .
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link MovementDetailFragment}.
 */
public class MovementDetailActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movement_detail);

		// Show the Up button in the action bar.		
		ActionBar bar = getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		
		if (savedInstanceState == null) {
			Bundle arguments = new Bundle();
			arguments.putString(MovementDetailFragment.ARG_ITEM_ID, getIntent()
					.getStringExtra(MovementDetailFragment.ARG_ITEM_ID));
			MovementDetailFragment fragment = new MovementDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.movement_detail_container, fragment).commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpTo(this, new Intent(this,
					MainActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
