package state;

public interface State {
 
    // 封装四种动作
	public void insertQuarter(); // 投币
	public void ejectQuarter();  // 退币
	public void turnCrank();     // 转动摇柄
	public void dispense();      // 发糖果
}
