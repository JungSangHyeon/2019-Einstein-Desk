package fPaint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import zStuff_Function.AFunction;

public class FShowMouseOn extends AFunction{
	private static final long serialVersionUID = 5046938002818853280L;

	boolean mouseOnMe = false;
	Color selectColor  = new Color(191,191,191,100);
	
	public void realPaint(Graphics2D g) {
		if(mouseOnMe) {
			g.setColor(selectColor);
			g.fill(master.getShape());
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if(master.getShape().contains(e.getPoint())) {mouseOnMe = true;}
		else {mouseOnMe = false;}
	}
}
