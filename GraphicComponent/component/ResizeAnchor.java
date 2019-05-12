package component;

import java.io.Serializable;

import component_Stuff.GraphicComponent;
import processor.Resizer;

public class ResizeAnchor extends GraphicComponent implements Serializable{
	private static final long serialVersionUID = 595132173176855082L;
	
	GraphicComponent targetGC;
	
	public ResizeAnchor(GraphicComponent targetGC) {
		this.targetGC=targetGC;
		this.addProcessor(new Resizer());
	}
	
}
