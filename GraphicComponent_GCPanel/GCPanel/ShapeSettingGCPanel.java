package GCPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import zStuff_GCPanel.PeekabooPanel;
import zStuff_GCPanel_ShapeSetting.CMCShapeSelectGCPanel;
import zStuff_GCPanel_ShapeSetting.PDRShapeSelectGCPanel;

public class ShapeSettingGCPanel extends PeekabooPanel{
	private static final long serialVersionUID = -4033921725411864658L;

	public ShapeSettingGCPanel() {
		this.setBounds(1493, 50, 322, 215 + 52);
		this.setPanelBackgroundColor(new Color(242,242,242));
		this.setPanelBorderColor(new Color(204,204,204));
		this.setborderThick(1);
		
		PDRShapeSelectGCPanel colorSelectPanel = new PDRShapeSelectGCPanel();
		colorSelectPanel.setGCLocation(this.getX() + 5, this.getY() + 40);
		colorSelectPanel.saveClip();
		this.add(colorSelectPanel);
		
		CMCShapeSelectGCPanel sizeBar = new CMCShapeSelectGCPanel();
		sizeBar.setGCLocation(this.getX() + 5, this.getY() + 142 + 52);
		sizeBar.saveClip();
		this.add(sizeBar);
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		super.paint(g2d);
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font(null, Font.PLAIN, 16));
		g2d.drawString("PDR Shape", this.getX()+10, this.getY()+25);
		g2d.drawString("CMC Shape", this.getX()+10, this.getY()+127+52);
	}
}
