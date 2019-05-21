package onOff;

public class ThickFollowScale { //Canvas의 배율에 따라 Creation의 Thick을 조절한다.
	static boolean on = false;
	public static boolean isOn() {return on;}
	public static void on() {on = true;}
	public static void off() {on = false;}
	public static void onOff() {on=(!on);}
}
