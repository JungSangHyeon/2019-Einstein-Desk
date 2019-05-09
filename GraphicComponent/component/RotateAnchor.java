package component;

import java.io.Serializable;

import processor.Rotator;
import stuff_Component.GraphicComponent;

public class RotateAnchor extends GraphicComponent implements Serializable{
	private static final long serialVersionUID = 1447532678400402555L;

	GraphicComponent targetGC;
	
	public RotateAnchor(GraphicComponent targetGC) {
		this.targetGC=targetGC;
		this.addProcessor(new Rotator());
	}
	
}
