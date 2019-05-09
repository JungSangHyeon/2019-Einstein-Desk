package globalTimer;

import java.util.Vector;

public class GlobalTimer implements Runnable{

	//Change
	static int clockSpeed = 10;
	static int gapRealWorld = 10;//계산하는 시간 때문에 살짝 늦음.
	static int limit = 1000000000;//시간이 int범위를 넘을 때를 대비.
	static String noTimerErrorMSG = "로 Timer안맞추셨습니당";// masterName + noTimerErrorMSG
	
	//No Change
	static int nowTime =0;
	static boolean globalTimerStop = false;
	static Vector<Timer> timers = new Vector<Timer>();
	
	public GlobalTimer() {
		 Thread th = new Thread(this);
		 th.start();
	}
	
	public void run() {
		while(true) {
			while(!globalTimerStop) {
				nowTime+=clockSpeed+gapRealWorld;//
				if(nowTime>limit) {limitBreakAction();}
				try {Thread.sleep(clockSpeed);} catch (Exception e) {}
			}
			try {Thread.sleep(clockSpeed);} catch (Exception e) {}
		}
	}
	private void limitBreakAction() {
		for(Timer timer : timers) {
			timer.setStartTime(timer.getStartTime()-nowTime);
		}
		nowTime = 0;
	}

	public static void globalTimerStop() {globalTimerStop = true;}//The World!
	public static void globalTimerRestart() {globalTimerStop = false;}//시간은 다시 흐른다!
	public static int getNowTime() {return nowTime;}
	public static void setClockSpeed(int speed) {clockSpeed=speed;}//딱 그값으로 설정
	public static void changeClockSpeed(int ratio) {clockSpeed/=ratio;}//현재값에 비율로 설정
	
	public static void addTimer(String master, int timeOut) {//master는 Identifier. timeOut(ms)
		 timers.add(new Timer(master, timeOut, nowTime));
	}
	
	public static boolean isTimeOut(String master) {
		Timer mastersTimer = findMasterTimer(master);
		if(mastersTimer==null) {return false;} 
		return timeOutChecker(mastersTimer);
	}
	
	public static void waitTimeOut(String master) {
		Timer mastersTimer = findMasterTimer(master);
		if(mastersTimer!=null) {
			while(!timeOutChecker(mastersTimer)) {
				try {Thread.sleep(clockSpeed);} catch (Exception e) {}
			}
		}
	}
	
	private static boolean timeOutChecker(Timer mastersTimer) {
		boolean result = false;
		if (nowTime - mastersTimer.getStartTime() > mastersTimer.getTimeOut()) {
			timers.removeElement(mastersTimer);
			result = true;
		}
		return result;
	}
	
	private static Timer findMasterTimer(String master) {
		for(Timer timer : timers) {
			if(timer.getMaster().equals(master)) {
				return timer;
			}
		}
		System.out.println(master+noTimerErrorMSG);//Error MSG
		return null;
	}
}
