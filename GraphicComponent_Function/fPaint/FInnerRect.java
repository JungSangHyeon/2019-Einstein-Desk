package fPaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import zStuff_Function.AFunction;

public class FInnerRect extends AFunction {
	private static final long serialVersionUID = 5046938002818853280L;

	int border = 1;
	Color mainColor = new Color(242, 148, 54);
	boolean mouseOnMe = false;
	
	public FInnerRect(Color c, int border) {
		this.mainColor = c;
		this.border=border;
	}
	
	@Override
	public void realPaint(Graphics2D g2d) {
		if (mouseOnMe) {
			g2d.setClip(master.getShape());
			Rectangle2D rect = master.getShape().getBounds2D();
			rect = new Rectangle2D.Double(rect.getX(), rect.getY(), rect.getWidth()-border+1, rect.getHeight()-border+1);
			g2d.setColor(mainColor);
			g2d.setStroke(new BasicStroke(border));
			g2d.draw(rect);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if(master.getShape().contains(e.getPoint())) {mouseOnMe = true;}
		else {mouseOnMe = false;}
	}
}
