package it.robol.android.piggybank;

import java.util.ArrayList;

import it.robol.android.piggybank.data.Account;
import it.robol.android.piggybank.data.DataProvider;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

public class AccountsAdapter implements ListAdapter {
	
	private DataProvider mProvider = null;
	private ArrayList<Account> mAccounts = new ArrayList<Account>();
	private Context mContext = null;
	
	public AccountsAdapter(Context context) {
		mContext = context;
		mProvider = DataProvider.getInstance(context);
		
		// TODO: Once the Observer interface is built into the DataProvider
		// we should really hook up there. 
		
		reloadData();
	}
	
	public void reloadData() {
		mAccounts = mProvider.getAccountsManager().getAccounts();
	}

	@Override
	public int getCount() {
		return mProvider.getAccountsManager().getCount();
	}

	@Override
	public Object getItem(int arg0) {
		return mAccounts.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return mAccounts.get(arg0).id;
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Account account = mAccounts.get(position);
		
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.account_list_item, null);
		}
		
		TextView textView = (TextView) convertView.findViewById(R.id.textView);
		textView.setText(account.name); 
		
		return convertView;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return getCount() == 0;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return false;
	}

}
