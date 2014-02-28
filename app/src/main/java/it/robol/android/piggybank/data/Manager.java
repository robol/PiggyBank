package it.robol.android.piggybank.data;

import android.util.Log;

import java.util.HashSet;

/**
 * This is a generic class that represents a DataManager. It's extended
 * by AccountsManager, CategoriesManager and the like.
 */
public class Manager {

    private final static String LOG_TAG = "Manager";

    protected HashSet<ManagerListener> mListeners = new HashSet<ManagerListener>();

    /**
     * Register a new {@link ManagerListener} that will be notified of the changes
     * to this {@link Manager}.
     * @param listener the {@link ManagerListener} that should be registered.
     */
    public void registerListener(ManagerListener listener) {
        mListeners.add(listener);
    }

    /**
     * Unregister a {@link ManagerListener} that has bene previously registered with a call
     * to registerListener().
     * @param listener The listener to unregister
     */
    public void unregisterListener (ManagerListener listener) {
        if (! mListeners.remove(listener)) {
            Log.w(LOG_TAG, "Trying to unregister listener that has not previously registered");
        }
    }

    /**
     * Notify the registered {@link ManagerListener} that some data has changed, and thus
     * it should be reloaded.
     */
    protected void notifyDataChanged() {
        for (ManagerListener listener : mListeners) {
            listener.onManagerChanged();
        }
    }


}
