package state;

// 正在售出的状态
public class SoldState implements State {
 
    GumballMachine gumballMachine;
 
    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
	public void insertQuarter() {
		System.out.println("Please wait, we're already giving you a gumball");
	}

	@Override
	public void ejectQuarter() {
		System.out.println("Sorry, you already turned the crank");
	}

	@Override
	public void turnCrank() {
		System.out.println("Turning twice doesn't get you another gumball!");
	}

	@Override
	public void dispense() {
		gumballMachine.releaseBall();
		if (gumballMachine.getCount() > 0) {
			gumballMachine.changeState(StateEnum.QUARTER_OUT);
		} else {
			System.out.println("Oops, out of gumballs!");
			gumballMachine.changeState(StateEnum.SOLD_OUT);
		}
	}

	@Override
	public String toString() {
		return "dispensing a gumball";
	}
}
