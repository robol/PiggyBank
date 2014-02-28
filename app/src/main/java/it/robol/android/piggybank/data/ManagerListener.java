package it.robol.android.piggybank.data;

public interface ManagerListener {

    /**
     * Instance interested in listening to the changes of a Manager class should
     * implement this and register themselves with the proper registerListener()
     * methods.
     */
    public void onManagerChanged();

}
