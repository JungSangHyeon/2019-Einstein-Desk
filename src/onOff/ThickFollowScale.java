package onOff;

public class ThickFollowScale { //Canvas�� ������ ���� Creation�� Thick�� �����Ѵ�.
	static boolean on = false;
	public static boolean isOn() {return on;}
	public static void on() {on = true;}
	public static void off() {on = false;}
	public static void onOff() {on=(!on);}
}
