package data;

import tool.Make2PointShapeTool; 
import toolStuff.ATool;
import twoPointShapeStuff.ShapeEnum.e2PShape;

public class GlobalData {
	public static ATool nowTool = new Make2PointShapeTool();
	public static e2PShape nowGC = e2PShape.rect;//�̰� ��ư�� ���� �޶����� ����.
}
