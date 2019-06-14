package fGlobalDataModify;

import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Vector;

import zStuff_Function.AFunction;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GraphicComponent;

public class FChangePanelVector extends AFunction implements Serializable{
	private static final long serialVersionUID = -6743524881365403749L;
	
	GCPanel_LayoutPixel_Y gcPanel;
	Vector<GraphicComponent> gcVector;
	
	public FChangePanelVector(GCPanel_LayoutPixel_Y targetPanel) {
		this.gcPanel = targetPanel;
		this.gcVector = new Vector<GraphicComponent>();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		gcPanel.changeVector(gcVector);
	}
}
