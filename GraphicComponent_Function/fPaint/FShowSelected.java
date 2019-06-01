package fPaint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import zStuff_Function.AFunction;

public class FShowSelected extends AFunction{
	private static final long serialVersionUID = 8565884211859423875L;
	
	int hRadio = 15;
	Color selectColor = new Color(68, 114, 196);
	
	public void realPaint(Graphics2D g) {
		if(master.isSelected()) {
			g.setColor(selectColor);
			Rectangle2D r = master.getShape().getBounds2D();
			g.fill(new Rectangle2D.Double(r.getX(), r.getY()+r.getHeight() - r.getHeight()/hRadio, r.getWidth(), r.getHeight()/hRadio));
		}
	}
}
