package function_Time;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import component_Stuff.GraphicComponent;
import zFunction_Stuff.AFunction;

public class Data_AutoRotate extends AFunction implements Serializable, Runnable{
	private static final long serialVersionUID = -6743524881365403749L;
	
	int dAngle = 1;
	
	Thread th;
	boolean running = false;
	public void timeIsMove(boolean boo) {
		if(boo) {
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
			AffineTransform at = new AffineTransform();//get AT
			at.setToRotation(Math.toRadians(dAngle), master.getCenter().getX(), master.getCenter().getY());
			
			master.setShape(at.createTransformedShape(master.getShape()));//Rotate Shape
			master.setMyCenter(transformPoint(at, master.getCenter()));
			for(Point2D.Float point : master.getPoints()) {//Rotate Points
				Point2D.Float cpoint = transformPoint(at,point);
				point.setLocation(cpoint.x, cpoint.y);
			}
			master.addAngle(dAngle);//add Angle
			
			for(GraphicComponent aggreGC : master.getAllAggregateGCs()) {
				aggreGC.setShape(at.createTransformedShape(aggreGC.getShape()));//Rotate Shape
				aggreGC.setMyCenter(transformPoint(at, aggreGC.getCenter()));
				for(Point2D.Float point : aggreGC.getPoints()) {//Rotate Points
					Point2D.Float cpoint = transformPoint(at,point);
					point.setLocation(cpoint.x, cpoint.y);
				}
				aggreGC.addAngle(dAngle);//add Angle
			}
			
			try {Thread.sleep(50);}catch(Exception e) {}
		}
	}
	
	public Point2D.Float transformPoint(AffineTransform at, Point2D.Float p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {at.transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void paintComponent(Graphics2D g, Shape shape) {}
	public void processSelectEvent(boolean selected) {}
}
