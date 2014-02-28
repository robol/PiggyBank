package it.robol.android.piggybank;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class NewAccountActivity extends ActionBarActivity {

    public static final String LOG_TAG = "NewAccountActivity";
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_account);

        ActionBar bar = getSupportActionBar();

        // Activate the Home button to allow going back to the main screen.
        bar.setDisplayHomeAsUpEnabled(true);

        // If we are actually creating the instance add the Fragment to the
        // current {@link FragmentManager}.
        if (savedInstanceState == null) {
            Log.d(LOG_TAG, "Creating the Fragment");
		    getSupportFragmentManager().beginTransaction().add(R.id.new_account_frame,
				new NewAccountFragment(), NewAccountFragment.TAG).commit();
        }
	}

}
