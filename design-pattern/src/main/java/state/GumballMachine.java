package state;

/**
 * 糖果发放机器
 */
public class GumballMachine {
 
	State soldOutState;
	State noQuarterState;
	State hasQuarterState;
	State soldState;
 
	State currentstate = soldOutState;

	int count = 0;
 
	public GumballMachine(int numberGumballs) {
		soldOutState = new SoldOutState(this);
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldState = new SoldState(this);
 
		this.count = numberGumballs;
 		if (numberGumballs > 0) {
			currentstate = noQuarterState;
		} 
	}
 
	public void insertQuarter() {
		currentstate.insertQuarter();
	}
 
	public void ejectQuarter() {
		currentstate.ejectQuarter();
	}
 
	public void turnCrank() {
		currentstate.turnCrank();
		currentstate.dispense();
	}
 
	void releaseBall() {
		System.out.println("A gumball comes rolling out the slot...");
		if (count != 0) {
			count = count - 1;
		}
	}

    public void changeState(StateEnum stateEnum) {
	    State state = null;
	    switch (stateEnum) {
            case SOLD_OUT:
                state = soldOutState; break;
            case SOLD:
                state = soldOutState; break;
            case QUARTER:
                state = hasQuarterState; break;
            case QUARTER_OUT:
                state = noQuarterState; break;
        }
        this.currentstate = state;
    }
 
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("\nMighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2004");
		result.append("\nInventory: " + count + " gumball");
		if (count != 1) {
			result.append("s");
		}
		result.append("\n");
		result.append("Machine is " + currentstate + "\n");
		return result.toString();
	}

    public int getCount() {
	    return count;
    }
}
