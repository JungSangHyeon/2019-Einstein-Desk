package fPaint;

import java.awt.event.MouseEvent;

import rightClickPanel.ShapeColorSetter.Target;
import rightClickPanel.ShapeColorSettingPanel;
import zStuff_Function.AFunction;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GraphicComponent.GraphicComponent;

public class FReleaseAddPanel extends AFunction{
	private static final long serialVersionUID = 8027294091613532408L;
	
	Target target;
	public FReleaseAddPanel(Target target) {this.target=target;}
	
	public void mouseReleased(MouseEvent e){
		GraphicComponent otherSettingPanel = null;
		for(GraphicComponent gc : GCPanelStorage.getGCPanelVector()) {
			if(gc instanceof ShapeColorSettingPanel) {
				otherSettingPanel = gc;
				break;
			}
		}
		GCPanelStorage.remove(otherSettingPanel);
		if(master.getShape().contains(e.getPoint())) {
			GCPanelStorage.add(new ShapeColorSettingPanel(target));
		}
	}
}
