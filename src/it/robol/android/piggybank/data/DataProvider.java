package it.robol.android.piggybank.data;

import java.util.ArrayList;

import android.content.Context;

public class DataProvider {
	
	private static DataProvider mInstance = null;
	
	public DatabaseHelper mHelper;
	
	private AccountsManager mAccountsManager = null;
	
	protected DataProvider(Context context) {
		// Inhibit creation of new instances
		mHelper = new DatabaseHelper(context);
		mAccountsManager = new AccountsManager(mHelper);
		
		// mAccountsManager.updateAccount(new Account(-1, "Test Account", "active"));
	}
	
	public static DataProvider getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DataProvider(context);
		}
		
		return mInstance;
	}
	
	public ArrayList<Account> getAccounts() {
		return mAccountsManager.getAccounts();
	}
	
	public int getAccountsCount() {
		return mAccountsManager.getCount();
	}
	
	public boolean updateAccount(Account account) {
		return mAccountsManager.updateAccount(account);
	}

}
