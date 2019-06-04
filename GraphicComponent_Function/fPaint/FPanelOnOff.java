package fPaint;

import zStuff_Button.A3LineButton;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GraphicComponent.GraphicComponent;

public class FPanelOnOff extends A3LineButton{
	private static final long serialVersionUID = -7439935490427729311L;

	boolean on = false;
	GraphicComponent gc;
	
	public FPanelOnOff(GraphicComponent target) {this.gc= target;}
	
	@Override
	public void realReaction() {
		on = (!on);
		if(on) {GCPanelStorage.add(gc);}
		else {GCPanelStorage.remove(gc);}
	}

}
