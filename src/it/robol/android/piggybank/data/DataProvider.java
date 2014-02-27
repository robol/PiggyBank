package it.robol.android.piggybank.data;

import android.accounts.AccountManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataProvider implements DatabaseHelper.ExampleDataLoader {
	
	private static DataProvider mInstance = null;

    public static final String LOG_TAG = "DataProvider";
	
	public DatabaseHelper mHelper;
	
	private AccountsManager mAccountsManager = null;
	private CategoriesManager mCategoriesManager = null;
	
	protected DataProvider(Context context) {
		mHelper = new DatabaseHelper(context, this);
		
		mAccountsManager = new AccountsManager(mHelper);
		mCategoriesManager = new CategoriesManager(mHelper);
	}
	
	public void loadExampleData(SQLiteDatabase db) {
        Log.d(LOG_TAG, "Loading sample data");

		// Create a sample Account
        db.execSQL("INSERT INTO " + AccountsManager.TABLE +
                " (name, state) VALUES ('Main account', 'active')");

        // Sample Categories
        db.execSQL("INSERT INTO " + CategoriesManager.TABLE +
                " (name) VALUES ('Food')");
	}
	
	public static DataProvider getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DataProvider(context);
		}
		
		return mInstance;
	}
	
	public AccountsManager getAccountsManager() {
		return mAccountsManager;
	}

}
