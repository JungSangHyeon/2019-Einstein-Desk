package globalTimer;

public class Timer {
	private String master = "";
	private int timeOut = 0, startTime = 0;
	public Timer(String master, int timeOut, int startTime) {
		this.master = master;
		this.timeOut = timeOut;
		this.startTime = startTime;
	}
	public String getMaster() {return this.master;}
	public int getTimeOut() {return this.timeOut;}
	public int getStartTime() {return this.startTime;}
	public void setStartTime(int time) {this.startTime=time;}
}
