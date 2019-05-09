package data;

import tool.Make2PointShapeTool; 
import toolStuff.ATool;
import twoPointShapeStuff.ShapeEnum.e2PShape;

public class GlobalData {
	public static ATool nowTool = new Make2PointShapeTool();
	public static e2PShape nowGC = e2PShape.rect;//이거 버튼에 따라 달라지게 하자.
}
