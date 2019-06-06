package underMenuBar_PHAUSE;

import java.awt.Color;

import fPaint.FShadow;
import graphicComponent.ToolSelectGC;
import zStuff_GCPanel_LayoutNull.GCPanel_LayoutNull;
import zStuff_GraphicComponent.GCCreator;

public class FilePanel extends GCPanel_LayoutNull{
	private static final long serialVersionUID = -5404710401528945325L;

	public FilePanel() {
		this.setBounds(100,100, 752, 504);
		this.setPanelBackgroundColor(new Color(68, 69, 70));
		this.setBorderPaint(false);
		
		//just gap : 48 x 504
		
		//List
		ListPanel listPanel = new ListPanel();
		listPanel.setGCLocation(this.getX() + 48, this.getY());
		this.add(listPanel);
		
		//Item
		LiveUpdatePanel liveUpdatePanel = new LiveUpdatePanel();
		liveUpdatePanel.setGCLocation(listPanel.getX() + listPanel.getWidth(), this.getY());
		this.add(liveUpdatePanel);
		
		//add Item Changer
		listPanel.add(GCCreator.create(ToolSelectGC.off));
		
		this.addFunction(new FShadow());
	}
	
}
