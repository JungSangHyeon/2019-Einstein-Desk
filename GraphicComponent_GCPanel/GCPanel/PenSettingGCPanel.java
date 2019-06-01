package GCPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import fPaint.FKillMasterIfNotSelected;
import zStuff_GCPanel.PeekabooPanel;
import zStuff_GCPanel_LineSetting.PenColorSelectGCPanel;
import zStuff_GCPanel_LineSetting.PenSizeBarGCPanel;

public class PenSettingGCPanel extends PeekabooPanel{
	private static final long serialVersionUID = 3141453841486592808L;

	public PenSettingGCPanel() {
		this.setBounds(1351, 50, 322, 458);
		this.setPanelBackgroundColor(new Color(242,242,242));
		this.setPanelBorderColor(new Color(204,204,204));
		this.setborderThick(1);
		
		PenColorSelectGCPanel colorSelectPanel = new PenColorSelectGCPanel();
		colorSelectPanel.setGCLocation(this.getX() + 5, this.getY() + 40);
		this.add(colorSelectPanel);
		
		PenSizeBarGCPanel sizeBar = new PenSizeBarGCPanel();
		sizeBar.setGCLocation(this.getX() + 5, this.getY() + 340);
		this.add(sizeBar);
		
		this.addFunction(new FKillMasterIfNotSelected());
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		super.paint(g2d);
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font(null, Font.PLAIN, 16));
		g2d.drawString("Colors", this.getX()+10, this.getY()+25);
		g2d.drawString("Size", this.getX()+10, this.getY()+335);
	}
}
