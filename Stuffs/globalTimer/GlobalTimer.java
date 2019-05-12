package globalTimer;

import java.util.Vector;

public class GlobalTimer implements Runnable{

	//User
	static int clockSpeed = 10;
	static String noTimerErrorMSG = "로 Timer안맞추셨습니당";// masterName + noTimerErrorMSG
	
	//System
	static int nowTime =0;
	static int gapRealWorld = 10;//계산하는 시간 때문에 살짝 늦음.
	static int limit = 1000000000;//시간이 int범위를 넘을 때를 대비.
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
		for(Timer timer : timers) {timer.setStartTime(timer.getStartTime()-nowTime);}
		nowTime = 0;
	}

	public static void globalTimerStop() {globalTimerStop = true;}//The World!
	public static void globalTimerRestart() {globalTimerStop = false;}//시간은 다시 흐른다!
	
	public static void setClockSpeed(int speed) {clockSpeed=speed;}//딱 그값으로 설정
	public static void changeClockSpeed(int ratio) {clockSpeed/=ratio;}//현재값에 비율로 설정
	
	public static int getNowTime() {return nowTime;}
	
	public static int addTimer(int timeOut) {
		int id = nowTime;//아마도 고유 할 것이여. 같을 확률 1/10억 
		timers.add(new Timer(id, timeOut, nowTime));
		return id;
	}
	
	public static boolean isTimeOut(int id) {
		Timer mastersTimer = findMasterTimer(id);
		if(mastersTimer==null) {return false;} 
		return timeOutChecker(mastersTimer);
	}
	
	public static void waitTimeOut(int id) {
		Timer mastersTimer = findMasterTimer(id);
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
	
	private static Timer findMasterTimer(int id) {
		for(Timer timer : timers) {
			if(timer.getId()==id) {return timer;}
		}
		System.out.println(id+noTimerErrorMSG);//Error MSG
		return null;
	}
}
