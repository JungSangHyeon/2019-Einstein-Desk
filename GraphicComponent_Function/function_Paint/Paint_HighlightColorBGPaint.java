package function_Paint;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import data.LineData;
import zFunction_Stuff.AFunction;

public class Paint_HighlightColorBGPaint extends AFunction{
	private static final long serialVersionUID = 5046938002818853280L;

	public void paintComponent(Graphics2D g, Shape shape) {//¿Ã∞‘ πª±Ó?
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(LineData.getHighlightColor());
		g2d.fill(shape);
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void processSelectEvent(boolean selected) {}
	public void timeIsMove(boolean boo) {}
}
