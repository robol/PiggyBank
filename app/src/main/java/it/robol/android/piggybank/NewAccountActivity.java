package it.robol.android.piggybank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import it.robol.android.piggybank.data.Account;
import it.robol.android.piggybank.data.DataProvider;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this,
                        new Intent(this, MainActivity.class));
                return true;
        }

        return false;
    }

    public void onCreateNewAccountClicked (View view) {
        Log.d(LOG_TAG, "Creating new Account: ");

        DataProvider provider = DataProvider.getInstance(this);
        EditText accountNameText = (EditText) findViewById(R.id.newAccountNameEditText);
        Account newAccount = new Account(accountNameText.getText().toString());

        provider.getAccountsManager().updateAccount(newAccount);

        // Go back to the Accounts View.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
