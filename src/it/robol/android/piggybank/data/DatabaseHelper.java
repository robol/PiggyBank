package it.robol.android.piggybank.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public final static int VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, "piggybank", null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create the table for the movements
		db.beginTransaction();
		
		Log.d("piggybank.data", "Creating database for PiggyBank");
		
		try {			
			db.execSQL("CREATE TABLE " + AccountsManager.TABLE + " (" +
					"     id INTEGER PRIMARY KEY, " +
					"     name VARCHAR(80)," +
					"     state VARCHAR(15)" +
					");");
			
			db.execSQL("CREATE TABLE movements (" +
					"     id INTEGER PRIMARY KEY, " +
					"     account_id INTEGER," +
					"     name VARCHAR(80)," +
					"     timestamp INTEGER," +
					"     state VARCHAR(15)," +
					"     amount REAL," +
					"     FOREIGN KEY(account_id) REFERENCES accounts(id)" +
					");");
			
			Log.d("Database", "PiggyBank database has been created");
			
			db.setTransactionSuccessful();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Nothing to do yet, since we have only version 1 for
		// the database. 
	}
	
	

}
