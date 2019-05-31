package function_Paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import zFunction_Stuff.AFunction;

public class Paint_showMouseOnMe extends AFunction{
	private static final long serialVersionUID = 5046938002818853280L;

	boolean mouseOnMe = false;
	Color selectColor  = new Color(191,191,191,100);
	
	public void paintComponent(Graphics2D g, Shape shape) {
		if(mouseOnMe) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.setColor(selectColor);
			g2d.fill(shape);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if(master.getShape().contains(e.getPoint())) {mouseOnMe = true;}
		else {mouseOnMe = false;}
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {mouseOnMe = false;}
	public void processSelectEvent(boolean selected) {}
	public void timeIsMove(boolean boo) {}
}
