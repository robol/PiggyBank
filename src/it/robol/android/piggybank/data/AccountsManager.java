package it.robol.android.piggybank.data;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AccountsManager {
	
	private DatabaseHelper mHelper = null;
	
	public static final String TABLE = "accounts";
	
	public AccountsManager(DatabaseHelper helper) {
		mHelper = helper;
	}
	
	public int getCount() {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT COUNT(id) FROM " + TABLE, null);
		int count = 0;
		
		if (cursor.moveToFirst()) {
			count = cursor.getInt(0);
		}
		
		db.close();
		return count;
	}
	
	public ArrayList<Account> getAccounts() {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		
		Cursor cursor = db.rawQuery("SELECT id, name, state FROM " + TABLE, null);
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		if (cursor.moveToFirst()) {
			do {
				Account newAccount = new Account(
						cursor.getInt(0),
						cursor.getString(1),
						cursor.getString(2)
						);
				accounts.add(newAccount);
			} while (cursor.moveToNext());
		}
		
		db.close();
		
		return accounts;
	}
	
	public boolean updateAccount(Account account) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.beginTransaction();
		
		Log.d("Database", "Adding Account = " + account.name);
		Log.d("Database", "Database path = " + db.getPath());
		
		try {
			if (account.id == -1) {
				db.insert(TABLE, null,
						account.asContentValues()
						);
			}
			else {
				db.update(TABLE, account.asContentValues(), 
						"id = " + account.id, null);
			}
			
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			db.close();
			return false;
		}
		finally {
			db.endTransaction();
		}
		
		db.close();		
		return true; 
	}

}
