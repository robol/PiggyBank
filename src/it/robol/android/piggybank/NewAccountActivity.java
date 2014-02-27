package it.robol.android.piggybank;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class NewAccountActivity extends FragmentActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		getSupportFragmentManager().beginTransaction().add(android.R.id.content, 
				new NewAccountFragment(), NewAccountFragment.TAG);
	}

}
