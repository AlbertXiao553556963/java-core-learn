package state;

public interface State {
 
    // ��װ���ֶ���
	public void insertQuarter(); // Ͷ��
	public void ejectQuarter();  // �˱�
	public void turnCrank();     // ת��ҡ��
	public void dispense();      // ���ǹ�
}
