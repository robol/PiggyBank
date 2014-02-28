package it.robol.android.piggybank;

public interface MasterCallbacks {
	/**
	 * Callback for when an item has been selected.
	 */
	public void onItemSelected(String id);
	
	public static MasterCallbacks dummyCallback = new MasterCallbacks () {		
		@Override
		public void onItemSelected(String id) {
		}
	};  
}
