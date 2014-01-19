package it.robol.android.piggybank;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import it.robol.android.piggybank.dummy.DummyContent;

/**
 * A list fragment representing a list of Movements. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link MovementDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class MovementListFragment extends MasterFragment {

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public MovementListFragment() {
	}

	@SuppressLint("InlinedApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO: replace with a real list adapter.
		setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
				Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? 
						android.R.layout.simple_list_item_activated_1 : 
							android.R.layout.simple_list_item_1,
				android.R.id.text1, DummyContent.ITEMS));
	}
	
	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getDetailFragmentClass() {
		return MovementDetailFragment.class;
	}
}
