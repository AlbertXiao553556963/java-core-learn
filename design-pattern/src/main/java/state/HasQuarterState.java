package state;

// 已经投币的状态
public class HasQuarterState implements State {

	GumballMachine gumballMachine;

	public HasQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("You can't insert another quarter");
	}

	@Override
	public void ejectQuarter() {
		System.out.println("Quarter returned");
		gumballMachine.changeState(StateEnum.QUARTER_OUT);
	}

	@Override
	public void turnCrank() {
		System.out.println("You turned...");
		gumballMachine.changeState(StateEnum.SOLD_OUT);
	}

	@Override
    public void dispense() {
        System.out.println("No gumball dispensed");
    }

    @Override
	public String toString() {
		return "waiting for turn of crank";
	}
}
