package time_Stuff;

import java.io.Serializable;

import zFunction_Stuff.AFunction;

public abstract class FTimeFunction extends AFunction implements Serializable, Runnable{
	private static final long serialVersionUID = -6743524881365403749L;
	
	Thread th;
	int interval = 50;
	boolean running = false;
	
	public void setInterval(int millisecond) {interval = millisecond;}
	
	@Override
	public void timeIsMove(boolean timeMove) {
		if(timeMove) {
			running = true;
			th = new Thread(this);
			th.start();
		}else {
			running = false;
			if(th!=null) {
				th.interrupt();
				th = null;
			}
		}
	}
	
	@Override
	public void run() {
		while(running) {
			timeAction();
			try {Thread.sleep(interval);}catch(Exception e) {}
		}
	}
	public abstract void timeAction();
}
