package fGlobalDataModify;

import java.io.Serializable;

import data.ShapeData;
import shape_Stuff.AShape;
import zStuff_Button.AFShapeButton;

public class FSetShape extends AFShapeButton implements Serializable{
	private static final long serialVersionUID = 787188459680824163L;
	
	AShape aShape;
	public FSetShape(AShape shape) {
		super(shape);
		this.aShape=shape;
	}
	
	@Override
	public void actionPerformed() {ShapeData.setNowShapeMaker(aShape);}
}
