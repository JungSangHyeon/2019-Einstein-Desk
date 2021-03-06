package settingPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import fPaint.FShadow;
import zStuff_GCPanel.NullPeekabooPanel;
import zStuff_GCPanel_ShapeSetting.CMCShapeSelectGCPanel;
import zStuff_GCPanel_ShapeSetting.PDRShapeSelectGCPanel;

public class ShapeSettingGCPanel extends NullPeekabooPanel{
	private static final long serialVersionUID = -4033921725411864658L;

	public ShapeSettingGCPanel() {
		this.setBounds(1493, 50, 322, 215 + 52);
		this.setPanelBackgroundColor(new Color(242,242,242));
		this.setPanelBorderColor(new Color(204,204,204));
		this.setborderThick(1);
		
		PDRShapeSelectGCPanel pdrShapeSelectPanel = new PDRShapeSelectGCPanel();
		pdrShapeSelectPanel.setGCLocation(this.getX() + 5, this.getY() + 40);
		pdrShapeSelectPanel.addItems();
		this.add(pdrShapeSelectPanel);
		
		CMCShapeSelectGCPanel cmcShapeSelectPanel = new CMCShapeSelectGCPanel();
		cmcShapeSelectPanel.setGCLocation(this.getX() + 5, this.getY() + 142 + 52);
		cmcShapeSelectPanel.addItems();
		this.add(cmcShapeSelectPanel);
		
		this.addFunction(new FShadow());
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
