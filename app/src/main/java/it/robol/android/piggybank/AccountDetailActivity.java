package it.robol.android.piggybank;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import it.robol.android.piggybank.data.DataProvider;

public class AccountDetailActivity extends ActionBarActivity {

    private long mAccountId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);

        mAccountId = getIntent().getExtras().getLong("ID");

        if (savedInstanceState == null) {
            // Pass the extras of the bundle.
            AccountDetailFragment accountDetailFragment = new AccountDetailFragment();
            accountDetailFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, accountDetailFragment)
                    .commit();
        }
        else {
            // TODO: This is probably wrong, because we haven't registered
            // the AccountDetailFragment with this TAG and it should probably
            // be recreated.
            getSupportFragmentManager().getFragment(savedInstanceState,
                    AccountDetailFragment.TAG).setArguments(
                    getIntent().getExtras());
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onDeleteAccountClicked (View view) {
        DataProvider provider = DataProvider.getInstance(this);
        provider.getAccountsManager().removeAccount(mAccountId);

        // Direct the user to the AccountList.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
