package it.robol.android.piggybank.data;

public class Movement {
	
	public enum State {
		ACTIVE,
		DELETED
	}
	
	public static class StateSyntaxException extends Exception {
		private static final long serialVersionUID = 1L;
	}

	public static String stateToString (State state) {
		switch (state) {
		case ACTIVE:
			return "active";
		case DELETED:
			return "deleted";
		default:
			return "active";
		}
	}
	
	public static State stringToState (String stateString) throws StateSyntaxException {
		if (stateString.contentEquals("active")) {
			return State.ACTIVE;
		}
		if (stateString.contentEquals("deleted")) {
			return State.DELETED;
		}
		
		throw new StateSyntaxException();
	}

	public long id;
	public long account_id;
	public long category_id;
	public String name; 
	public double amount;
	public State state;
	
	public Movement (long id, long account_id, long category_id, 
			String name, double amount, State state) {
		this.id = id;
		this.account_id = account_id;
		this.category_id = category_id;
		this.name = name;
		this.amount = amount;
		this.state = state;
	}
	
	public Movement() {
		this(-1, -1, -1, "New Movement", 0.0, State.ACTIVE);
	}
	
	public Movement(long id, Account account, Category category, 
			String name, double amount) {
		this(id, account.id, category.id, name, amount, State.ACTIVE);
	}
	
}
