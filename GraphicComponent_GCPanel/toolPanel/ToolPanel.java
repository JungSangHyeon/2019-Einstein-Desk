package toolPanel;

import java.awt.Color;
import java.awt.Graphics2D;

import fPaint.FShadow;
import zStuff_GCPanel_LayoutNull.GCPanel_LayoutNull;

public class ToolPanel extends GCPanel_LayoutNull{
	private static final long serialVersionUID = -2570655785858887142L;
	
	ToolSelectGCPanel toolSelectGCPanel;
	ToolBTNGCPanel toolBTNGCPanel;
	OffGCPanel offGCPanel;
	
	public ToolPanel() {
		this.setBounds(1920 - 48*9 -1, 0, 48*9 +1, 48-1 );//height가 왜 1 더 긴거여?
//		this.setBounds(100,100, 48*9 +1, 48-1 );//height가 왜 1 더 긴거여?
		this.setborderThick(0);
		
		toolSelectGCPanel = new ToolSelectGCPanel();
		toolSelectGCPanel.setGCLocation(this.getX(), this.getY());
		toolSelectGCPanel.addItems();
		this.add(toolSelectGCPanel);
		
		toolBTNGCPanel = new ToolBTNGCPanel();
		toolBTNGCPanel.setGCLocation(toolSelectGCPanel.getX() + toolSelectGCPanel.getWidth() + 1, this.getY());
		toolBTNGCPanel.addItems();
		this.add(toolBTNGCPanel);
		
		offGCPanel = new OffGCPanel();
		offGCPanel.setGCLocation(toolBTNGCPanel.getX() + toolBTNGCPanel.getWidth(), this.getY());
		offGCPanel.addItems();
		this.add(offGCPanel);
		
		this.addFunction(new FShadow());
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		super.paint(g2d);
		g2d.setColor(new Color(138, 138, 138));
		g2d.fillRect(toolSelectGCPanel.getX() + toolSelectGCPanel.getWidth(), this.getY(), 1, 48);
	}
}
