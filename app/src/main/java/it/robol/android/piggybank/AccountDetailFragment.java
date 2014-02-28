package it.robol.android.piggybank;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.robol.android.piggybank.data.Account;
import it.robol.android.piggybank.data.DataProvider;

public class AccountDetailFragment extends Fragment {

    public static final String TAG = "AccountDetailFragment";

    public AccountDetailFragment() {}

    private Account mAccount = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, group, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_account_detail, null);

        // Load the Account, if possible.
        if (getArguments() != null) {
            long id;
            id = getArguments().getLong("ID");
            mAccount = DataProvider.getInstance(getActivity()).
                    getAccountsManager().getAccount(id);

            loadAccountData(view);
        }

        return view;
    }

    private void loadAccountData(View view) {
        TextView accountName = (TextView) view.findViewById(R.id.account_detail_name);
        accountName.setText("Account: " + mAccount.name);
    }


}
