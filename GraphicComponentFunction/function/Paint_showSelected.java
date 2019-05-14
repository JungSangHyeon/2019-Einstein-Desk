package function;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import function_Stuff.AFunction;

public class Paint_showSelected extends AFunction{
	private static final long serialVersionUID = 5046938002818853280L;

	int hRadio = 10;
	boolean masterSelected = false;
	Color selectColor  = new Color(68, 114, 196);
	
	public void mouseReleased(MouseEvent e) {
		if(master.getShape().contains(e.getPoint())) {masterSelected=true;}
		else {masterSelected=false;}
	}
	
	public void paintComponent(Graphics g, Shape shape) {
		if(masterSelected) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			
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
