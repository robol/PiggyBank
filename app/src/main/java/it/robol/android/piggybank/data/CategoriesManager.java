package it.robol.android.piggybank.data;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CategoriesManager extends Manager {

	public static final String TABLE = "categories";
	
	private DatabaseHelper mHelper;
	
	public CategoriesManager (DatabaseHelper helper) {
		mHelper = helper;
	}
	
	public int getCount() {
		SQLiteDatabase db = mHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT COUNT(id) FROM " + TABLE, null);
		int count = 0;
		
		if (cursor.moveToFirst()) {
			count = cursor.getInt(0);
		}
		
		db.close();
		return count;
	}
	
	public ArrayList<Category> getCategories() {
		ArrayList<Category> categories = new ArrayList<Category>();
		SQLiteDatabase db = mHelper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery("SELECT id, name FROM " + TABLE, null);
		
		if (cursor.moveToFirst()) {
			do {
				categories.add (new Category(
						cursor.getInt(0),
						cursor.getString(1),
						cursor.getString(2)
						));
			} while (cursor.moveToNext());
		}
		
		db.close();
		
		return categories;
	}
	
	public Category getCategory (long id) {
		SQLiteDatabase db = mHelper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"SELECT name, color FROM " + TABLE + " WHERE id=" + id, null);
		if (cursor.moveToFirst()) {
			Category newCategory = new Category (
					id, cursor.getString(0), cursor.getString(1));
			db.close();
			return newCategory;
		}
		else {
			db.close();
			return null;
		}
	}
	
	public Category updateCategory(Category category) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		
		db.beginTransaction();
		
		try {
			
			if (category.id == -1) {
				ContentValues values = new ContentValues();
				values.put("timestamp", Utils.getTimeStamp());
				values.put("color", category.color);
				values.put("name", category.name);
				category.id = db.insert(TABLE, null, values);
			}
			else {
				ContentValues values = new ContentValues();
				values.put("name", category.name);
				values.put("color", category.color);
				values.put("timestamp", Utils.getTimeStamp());			
				db.update(TABLE, values, "id = " + category.id, null);
			}
			
			db.setTransactionSuccessful();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
		
		db.close();
        notifyDataChanged();
		return category;
	}
	
}
