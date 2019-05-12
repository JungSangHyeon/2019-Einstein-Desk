package affineScrollPanel;

import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AffineScrollPanel extends JPanel {

	protected AffineTransform scrollAT;
	int deep = 0, deepLevel = 0, speed = 20;
	
	public AffineScrollPanel() {
		scrollAT = new AffineTransform();
		this.addMouseWheelListener(new MouseWheelHadler());
	}
	
	public class MouseWheelHadler implements MouseWheelListener{
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() > 0&&deep<deepLevel) {wheelDownAction();}
			else if(e.getWheelRotation() < 0&&deepLevel<0){wheelUpAction();}
			repaint();
		}
	}
	
	public void wheelDownAction() {deepLevel--; scrollAT.translate(0,-speed);}
	public void wheelUpAction() {deepLevel++; scrollAT.translate(0,speed);}
	
	public void setDeepLevel(int deepLevel) {this.deepLevel=deepLevel;}
	
	public void deeper(int howDeep) {this.deep-=howDeep;}
	public int getNowDeep() {return deepLevel*speed;}
	public void setDeep(int deep) {this.deep=deep;}
	
	public void setSpeed(int speed) {this.speed=speed;}
	public int getSpeed() {return this.speed;}
	
	public Point2D.Float transformPoint(Point p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {scrollAT.createInverse().transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}

}
