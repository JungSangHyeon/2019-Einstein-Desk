package underMenuBar_PHAUSE;

import java.awt.Color;

import fGlobalDataModify.FChangePanelVector;
import fPaint.FShadow;
import fPaint.FShowMouseOn;
import fText.FTextWrite;
import graphicComponent.ToolSelectGC;
import zStuff_Function.AFunction;
import zStuff_GCPanel_LayoutNull.GCPanel_LayoutNull;
import zStuff_GraphicComponent.GCCreator;
import zStuff_GraphicComponent.GraphicComponent;

public class FilePanel extends GCPanel_LayoutNull{
	private static final long serialVersionUID = -5404710401528945325L;

	LiveUpdatePanel liveUpdatePanel;
	
	public FilePanel() {
		this.setBounds(100,400, 752, 504);
		this.setPanelBackgroundColor(new Color(68, 69, 70));
		this.setBorderPaint(false);
		
		//just gap : 48 x 504
		
		//List
		ListPanel listPanel = new ListPanel();
		listPanel.setGCLocation(this.getX() + 48, this.getY());
		listPanel.setBackgroundColor(Color.green);
		this.add(listPanel);
		
		//Item
		liveUpdatePanel = new LiveUpdatePanel();
		liveUpdatePanel.setGCLocation(listPanel.getX() + listPanel.getWidth(), this.getY());
		liveUpdatePanel.setBackgroundColor(Color.cyan);
		this.add(liveUpdatePanel);
		
		//add Item Changer
		listPanel.add(newListItem());
		
		this.addFunction(new FShadow());
	}
	
	public GraphicComponent newListItem() {
		AFunction[] itemChangeGC = {
				new FChangePanelVector(liveUpdatePanel),
				new FShowMouseOn(),
				new FTextWrite()
		};
		GraphicComponent gc = GCCreator.create(itemChangeGC);
		gc.setText("new File");
		return gc;
	}
}
