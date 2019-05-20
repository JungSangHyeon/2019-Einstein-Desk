package function_Paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import zFunction_Stuff.AFunction;

public class Paint_penColorBGPaint extends AFunction{
	private static final long serialVersionUID = 5046938002818853280L;

	public void paintComponent(Graphics2D g, Shape shape) {//¿Ã∞‘ πª±Ó?
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.CYAN);
		g2d.fill(shape);
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
