package function_Paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import zFunction_Stuff.AFunction;

public class Paint_showSelectedTool extends AFunction{
	private static final long serialVersionUID = 8565884211859423875L;
	
	int hRadio = 15;
	boolean masterSelected = false;
	Color selectColor  = new Color(68, 114, 196);
	
	public void mouseReleased(MouseEvent e) {
		if(master.getShape().contains(e.getPoint())) {masterSelected=true;}
		else {masterSelected=false;}
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		if(masterSelected) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.setClip(shape);
			g2d.setColor(selectColor);
			Rectangle2D r = shape.getBounds2D();
			g2d.fill(new Rectangle2D.Double(r.getX(), r.getY()+r.getHeight() - r.getHeight()/hRadio, r.getWidth(), r.getHeight()/hRadio));
			g2d.setClip(null);
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
