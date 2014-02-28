package it.robol.android.piggybank.data;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AccountsManager extends Manager {
	
	private DatabaseHelper mHelper = null;
	
	public static final String TABLE = "accounts";

    private final static String LOG_TAG = "AccountsManager";

    private ManagerListener[] mListeners = {};
	
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
	
	public Account getAccount(long id) {
		SQLiteDatabase db = mHelper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"SELECT name,  state FROM " + TABLE + " WHERE id=" + id, null);
		
		if (cursor.moveToFirst()) {
			Account newAccount = new Account (
					id, 
					cursor.getString(0),
					cursor.getString(1)
					);
			db.close();
			return newAccount;
		}
		else {
			db.close();
			return null;
		}
	}
	
	public Account updateAccount(Account account) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.beginTransaction();
		
		Log.d("Database", "Adding Account = " + account.name);
		
		try {
			ContentValues values = account.asContentValues();
			
			// Overwrite the timestamp in the fields so we can easily sync
			// only changed objects (once Synchronization will be implemented)
			values.put("timestamp", Utils.getTimeStamp());
			
			if (account.id == -1) {
				account.id = db.insert(TABLE, null,
						values
						);
			}
			else {
				db.update(TABLE, values, 
						"id = " + account.id, null);
			}
			
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			db.close();
			
			return null;
		}
		finally {
			db.endTransaction();
		}
		
		db.close();

        notifyDataChanged();
		return account; 
	}

    /**
     * Remove an {@link Account} from the database, along with all its associated
     * transitions.
     *
     * @param id The id of the {@link Account} that shall be removed.
     */
    public void removeAccount(long id) {
        Log.d(LOG_TAG, "Deleting account with id = " + id);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM " + MovementManager.TABLE + " WHERE account_id = " + id);
            db.execSQL("DELETE FROM " + AccountsManager.TABLE + " WHERE id = " + id);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.w(LOG_TAG, "Error while deleting Account with id = " + id);
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        notifyDataChanged();
        db.close();
    }

}
