package it.robol.android.piggybank.data;

import android.content.ContentValues;

public class Account {
	
	private DataProvider mProvider = null;
	
	public long id = -1;
	public String name = "New Account";
	public String state = "active";
	
	public Account() {
	}
	
	public Account(long id2, String name, String state) {
		this.id = id2;
		this.name = name;
		this.state = state;
	}
	
	public Account(long id, String name) {
		this(id, name, "active");
	}
	
	public Account(String name) {
		this(-1, name);
	}
	
	public void setProvider(DataProvider provider) {
		mProvider = provider;
	}
	
	public DataProvider getProvider() {
		return mProvider;
	}
	
	public ContentValues asContentValues() {
		ContentValues values = new ContentValues();
		
		if (id != -1)
			values.put("id", id);
		
		values.put("name", name);
		values.put("state", state);
		
		return values;
	}

}
