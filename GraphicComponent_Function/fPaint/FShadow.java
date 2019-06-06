package fPaint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import zStuff_Function.AFunction;

public class FShadow extends AFunction {
	private static final long serialVersionUID = 5046938002818853280L;

	static int gap = 1;
	static int transparent = 40;
	static Color[] shadowColors = {
//			new Color(210, 208, 206, transparent),
			
//			new Color(211, 211, 211, transparent),
			new Color(215, 215, 215, transparent),
			new Color(219, 219, 219, transparent),
			new Color(223, 223, 223, transparent),
			new Color(226, 226, 226, transparent),
	};
//	static Color[] shadowColors = {
//			new Color(210, 208, 206),
//			new Color(211, 211, 211),
//			new Color(215, 215, 215),
//			new Color(219, 219, 219),
//			new Color(223, 223, 223),
//			new Color(226, 226, 226),
//	};
	public FShadow() {this.setPaintOrder(PaintZOrder.BOTTOM);}
	
	@Override
	public void realPaint(Graphics2D g2d) {
		Rectangle2D bound = master.getShape().getBounds2D();
		bound = new Rectangle2D.Double(bound.getX(), bound.getY()+1, bound.getWidth()+1, bound.getHeight());
		Vector<Rectangle2D> bounds = new Vector<Rectangle2D>();
		for(int i=0; i<shadowColors.length; i++) {
			Rectangle2D temp = new Rectangle2D.Double(
					bound.getX() - gap*(i+1),
					bound.getY() - gap*(i+1),
					bound.getWidth() + gap*(i+1)*2,
					bound.getHeight() + gap*(i+1)*2
			);
			bounds.add(0,temp);
		}
		int i=0;
		for(Rectangle2D shadow : bounds) {
			g2d.setColor(shadowColors[shadowColors.length-1-i]);
			g2d.fill(shadow);
			i++;
		}
	}
}
