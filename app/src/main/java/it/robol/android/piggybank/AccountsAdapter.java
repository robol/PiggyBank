package it.robol.android.piggybank;

import java.util.ArrayList;

import it.robol.android.piggybank.data.Account;
import it.robol.android.piggybank.data.DataProvider;
import it.robol.android.piggybank.data.ManagerListener;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.HashSet;

public class AccountsAdapter implements ListAdapter, ManagerListener {
	
	private DataProvider mProvider = null;
	private ArrayList<Account> mAccounts = new ArrayList<Account>();
	private Context mContext = null;

    private final static String LOG_TAG = "AccountsAdapter";

    private HashSet<DataSetObserver> mObservers = new HashSet<DataSetObserver>();
	
	public AccountsAdapter(Context context) {
		mContext = context;
		mProvider = DataProvider.getInstance(context);

        mProvider.getAccountsManager().registerListener(this);
		
		reloadData();
	}

    public void onManagerChanged() {
        reloadData();
        for (DataSetObserver o : mObservers) {
            o.onChanged();
        }
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

        // final ListView listView = (ListView) parent;
		
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
        mObservers.add(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
        mObservers.remove(observer);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}

}
