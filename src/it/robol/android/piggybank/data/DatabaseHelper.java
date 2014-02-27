package it.robol.android.piggybank.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	/**
	 * @brief Version of the database. Please note that the version may
	 * not change even if the database schema does in the development release, 
	 * but is guaranteed to change between incompatible databases released
	 * on the Play Store. 
	 */
	public final static int VERSION = 1;
	
	/**
	 * @brief true if the Database has just been created, i.e., 
	 * if this class was force to (re)build the tables structure.  
	 */
	private boolean mJustCreated = false;

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
					"     state VARCHAR(15)," +
					"     timestamp INTEGER" +
					");");
			
			db.execSQL("CREATE TABLE " + CategoriesManager.TABLE + "( " +
					"     id INTEGER PRIMARY KEY," + 
					"     name VARCHAR(80)," +
					"     color VARCHAR(8)," +
					"     timestamp INTEGER" +
					");");
			
			db.execSQL("CREATE TABLE movements (" +
					"     id INTEGER PRIMARY KEY, " +
					"     account_id INTEGER," +
					"     category_id INTEGER," +
					"     name VARCHAR(80)," +
					"     timestamp INTEGER," +
					"     state VARCHAR(15)," +
					"     amount REAL," +
					"     FOREIGN KEY(account_id) REFERENCES " 
						+ AccountsManager.TABLE + "(id)," +
					"     FOREIGN KEY(category_id) REFERENCES " + 
						CategoriesManager.TABLE + "(id)" +
					");");
			
			Log.d("Database", "PiggyBank database has been created");
			
			// Mark the database as just created, so the DataProvider will
			// inflate the example content as soon as it's loaded. 
			mJustCreated = true;
			
			db.setTransactionSuccessful();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
			mJustCreated = true;
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Nothing to do yet, since we have only version 1 for
		// the database. 
	}
	
	public boolean justCreated() {
		return mJustCreated;
	}
	
	

}
