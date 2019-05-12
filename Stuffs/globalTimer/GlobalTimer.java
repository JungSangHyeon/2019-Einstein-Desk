package globalTimer;

import java.util.Vector;

public class GlobalTimer implements Runnable{

	//User
	static int clockSpeed = 10;
	static String noTimerErrorMSG = "�� Timer�ȸ��߼̽��ϴ�";// masterName + noTimerErrorMSG
	
	//System
	static int nowTime =0;
	static int gapRealWorld = 10;//����ϴ� �ð� ������ ��¦ ����.
	static int limit = 1000000000;//�ð��� int������ ���� ���� ���.
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
	public static void globalTimerRestart() {globalTimerStop = false;}//�ð��� �ٽ� �帥��!
	
	public static void setClockSpeed(int speed) {clockSpeed=speed;}//�� �װ����� ����
	public static void changeClockSpeed(int ratio) {clockSpeed/=ratio;}//���簪�� ������ ����
	
	public static int getNowTime() {return nowTime;}
	
	public static int addTimer(int timeOut) {
		int id = nowTime;//�Ƹ��� ���� �� ���̿�. ���� Ȯ�� 1/10�� 
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
