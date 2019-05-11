package globalTimer;

public class Timer {
	private int id;
	private int timeOut = 0, startTime = 0;
	public Timer(int id, int timeOut, int startTime) {
		this.id = id;
		this.timeOut = timeOut;
		this.startTime = startTime;
	}
	public int getId() {return this.id;}
	public int getTimeOut() {return this.timeOut;}
	public int getStartTime() {return this.startTime;}
	public void setStartTime(int time) {this.startTime=time;}
}
