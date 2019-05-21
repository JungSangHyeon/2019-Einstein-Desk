package onOff;

public class Ctrl {
	static boolean on = false;
	public static boolean isOn() {return on;}
	public static void on() {on = true;}
	public static void off() {on = false;}
}
