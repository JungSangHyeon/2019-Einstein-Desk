package fPaint;

import data.GCPanelStorage;
import zStuff_Function.AFunction;
import zStuff_GraphicComponent.GraphicComponent;

public class FGCpeekaboo extends AFunction{
	private static final long serialVersionUID = 4299512141606163429L;
	
	GraphicComponent  showUpGC;
	boolean beforeSelected = false;
	
	public FGCpeekaboo(GraphicComponent  gc) {this.showUpGC = gc; }
	
	public void processSelectEvent(boolean selected) {
		if(beforeSelected&&selected&&!GCPanelStorage.have(showUpGC)) {
			GCPanelStorage.remove(showUpGC);
			GCPanelStorage.add(showUpGC);
		}else if(beforeSelected&&selected&&GCPanelStorage.have(showUpGC)) {
			GCPanelStorage.remove(showUpGC);
		}else if(!selected) {
			GCPanelStorage.remove(showUpGC);
		}
		beforeSelected = selected;
	}
}
