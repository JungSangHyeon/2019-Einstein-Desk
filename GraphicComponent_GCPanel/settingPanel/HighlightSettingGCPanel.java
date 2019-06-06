package settingPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import fPaint.FShadow;
import zStuff_GCPanel.PeekabooPanel;
import zStuff_GCPanel_LineSetting.HighlightColorSelectGCPanel;
import zStuff_GCPanel_LineSetting.HighlightSizeBarGCPanel;

public class HighlightSettingGCPanel extends PeekabooPanel{
	private static final long serialVersionUID = 3141453841486592808L;

	public HighlightSettingGCPanel() {
		this.setBounds(1399, 50, 322, 250);
		this.setPanelBackgroundColor(new Color(242,242,242));
		this.setPanelBorderColor(new Color(204,204,204));
		this.setborderThick(1);
		
		HighlightColorSelectGCPanel colorSelectPanel = new HighlightColorSelectGCPanel();
		colorSelectPanel.setGCLocation(this.getX() + 5, this.getY() + 40);
		this.add(colorSelectPanel);
		
		HighlightSizeBarGCPanel sizeBarPanel = new HighlightSizeBarGCPanel();
		sizeBarPanel.setGCLocation(this.getX() + 5, this.getY() + 132);
		this.add(sizeBarPanel);
		
		this.addFunction(new FShadow());
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		super.paint(g2d);
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font(null, Font.PLAIN, 16));
		g2d.drawString("Colors", this.getX()+10, this.getY()+25);
		g2d.drawString("Size", this.getX()+10, this.getY()+127);
	}
}
