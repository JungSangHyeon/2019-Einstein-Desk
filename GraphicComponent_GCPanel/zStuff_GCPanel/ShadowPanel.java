package zStuff_GCPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import zStuff_GCPanel_LayoutNull.GCPanel_LayoutNull;

public class ShadowPanel extends GCPanel_LayoutNull{
	private static final long serialVersionUID = 3141453841486592808L;

	static int gap = 1;
	static Color[] shadowColors = {
			new Color(210, 208, 206),
			new Color(211, 211, 211),
			new Color(215, 215, 215),
			new Color(219, 219, 219),
			new Color(223, 223, 223),
			new Color(226, 226, 226),
	};
	
	@Override
	public void paint(Graphics2D g2d) {
		Rectangle2D bound = this.getShape().getBounds2D();
		for(int i=shadowColors.length-1; i>-1; i--) {
			Rectangle2D temp = new Rectangle2D.Double(
					bound.getX() - gap*i,
					bound.getY() - gap*i,
					bound.getWidth() + gap*2*i,
					bound.getHeight() + gap*2*i
			);
			g2d.setColor(shadowColors[i]);
			g2d.fill(temp);
		}
		super.paint(g2d);
	}
}
