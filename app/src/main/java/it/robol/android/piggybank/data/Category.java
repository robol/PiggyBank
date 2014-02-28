package it.robol.android.piggybank.data;

public class Category {

	public long id;
	public String name;
	public String color;
	
	public Category() {
		id  = -1;
		name = "New Category"; 
		color = "000000";
	}
	
	public Category(String name) {
		this(-1, name, "000000");
	}
	
	public Category(String name, String color) {
		this(-1, name, color);
	}
	
	public Category(long id, String name, String color) {
		this.id = id;
		this.name = name;
		this.color = color;
	}
	
	
}
