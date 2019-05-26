package data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import lineSetting_Stuff.HighlightColorSelectPanelTESTTTTTT;
import lineSetting_Stuff.PenSizeBarTESTTTTTTTTTT;

public class testContainer2 extends GCPanel_LayoutNull{
	private static final long serialVersionUID = 3141453841486592808L;

	public testContainer2() {
		this.setBounds(800, 500, 322, 250);
		this.setPanelBackgroundColor(new Color(242,242,242));
		this.setPanelBorderColor(new Color(204,204,204));
		this.setborderThick(1);
		
		
		HighlightColorSelectPanelTESTTTTTT colorSetItem = new HighlightColorSelectPanelTESTTTTTT();
		colorSetItem.setLocation(this.getX() + 5, this.getY() + 40);
		this.add(colorSetItem);
		
		PenSizeBarTESTTTTTTTTTT sb = new PenSizeBarTESTTTTTTTTTT();
		sb.setLocation(this.getX() + 5, this.getY() + 132);
		this.add(sb);
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		super.paint(g2d);
		
		g2d.setColor(Color.BLACK);//텍스트는 올리기 애매하다아
		g2d.setFont(new Font(null, Font.PLAIN, 16));
		g2d.drawString("Colors", this.getX()+10, this.getY()+25);
		g2d.drawString("Size", this.getX()+10, this.getY()+127);
	}
}
