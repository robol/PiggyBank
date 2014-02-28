package it.robol.android.piggybank.data;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MovementManager {
	
	private DatabaseHelper mHelper;
	
	public static final String TABLE = "movements";

	public MovementManager(DatabaseHelper helper) {
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
	
	public ArrayList<Movement> getMovements() {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		
		Cursor cursor = db.rawQuery("SELECT id, account_id, category_id, " +
		    "name, amount, state FROM " + TABLE, null);
		ArrayList<Movement> movements = new ArrayList<Movement>();
		
		if (cursor.moveToFirst()) {
			do {
				Movement.State state = Movement.State.ACTIVE;
				String stateString = cursor.getString(5);
				
				if (stateString.equals("deleted")) {
					state = Movement.State.DELETED;
				}

				Movement newMovement = new Movement (
						cursor.getInt(0),
						cursor.getInt(1),
						cursor.getInt(2),
						cursor.getString(3),
						cursor.getFloat(4),
						state
						);
				movements.add(newMovement);
			} while (cursor.moveToNext());
		}
		
		db.close();
		
		return movements;
	}
	
	public Movement getMovement (long id) {
		SQLiteDatabase db = mHelper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"SELECT name, account_id, category_id, amount, state FROM " + 
				TABLE + " WHERE id=" + id , null);
		
		Movement newMovement = null;
		if (cursor.moveToFirst()) {
			try {
				newMovement = new Movement (id, 
						cursor.getLong(1),
						cursor.getLong(2), 
						cursor.getString(0),
						cursor.getFloat(3),
						Movement.stringToState(cursor.getString(4)));
			} catch (Movement.StateSyntaxException e) {
				e.printStackTrace();
			}		
		}
		
		db.close();
		return newMovement;
	}
	
	public Movement updateMovement (Movement movement) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		
		db.beginTransaction();
		
		ContentValues values = new ContentValues(); 
		
		values.put("name", movement.name);
		values.put("timestamp", Utils.getTimeStamp());
		values.put("account_id", movement.account_id);
		values.put("category_id", movement.category_id);
		values.put("amount", movement.amount);
		values.put("state", Movement.stateToString(movement.state));
		
		try {
			if (movement.id == -1) {
				movement.id = db.insert(TABLE, null, values);
			}
			else {
				db.update(TABLE, values, "id = " + movement.id, null);
			}
			
			db.setTransactionSuccessful();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
		
		db.close();
		
		return movement; 
	}
	
}
