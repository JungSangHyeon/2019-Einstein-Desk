package dataModifyFunction;

import java.io.Serializable;

import button_Stuff.FAShapeButton;
import data.ShapeData;
import shape_Stuff.AShape;

public class FSetShape extends FAShapeButton implements Serializable{
	private static final long serialVersionUID = 787188459680824163L;
	
	AShape aShape;
	public FSetShape(AShape shape) {
		super(shape);
		this.aShape=shape;
	}
	
	@Override
	public void actionPerformed() {ShapeData.setNowShapeMaker(aShape);}
}
