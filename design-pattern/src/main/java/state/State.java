package state;

public interface State {

	void insertQuarter(); // 投币

    void ejectQuarter();  // 退币

    void turnCrank();     // 转动摇柄

    void dispense();      // 发糖果
}
