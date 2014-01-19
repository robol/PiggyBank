package it.robol.android.piggybank.data;

import android.content.Context;

public class DataProvider {
	
	private static DataProvider mInstance = null;
	
	public DatabaseHelper mHelper;
	
	private AccountsManager mAccountsManager = null;
	private CategoriesManager mCategoriesManager = null;
	
	protected DataProvider(Context context) {
		mHelper = new DatabaseHelper(context);
		
		mAccountsManager = new AccountsManager(mHelper);
		mCategoriesManager = new CategoriesManager(mHelper);
		
		if (mHelper.justCreated()) {
			loadExampleData();
		}
	}
	
	private void loadExampleData() {
		// Create a sample Account
		mAccountsManager.updateAccount(
				new Account("Main account"));
		
		// Sample Categories
		mCategoriesManager.updateCategory(
				new Category("Food"));
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
