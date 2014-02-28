package it.robol.android.piggybank;

public interface MasterCallbacks {
	/**
	 * Callback for when an item has been selected.
	 */
	public void onItemSelected(MasterFragment source, long id);
	
	public static MasterCallbacks dummyCallback = new MasterCallbacks () {		
		@Override
		public void onItemSelected(MasterFragment source, long id) {
		}
	};  
}
