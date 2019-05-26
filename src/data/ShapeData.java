package data;

import shape_Stuff.AShape;
import shape_Stuff.eShape;

public class ShapeData {
	private static AShape nowShapeMaker = eShape.rect.getAShape();
	public static AShape getNowShapeMaker() {return nowShapeMaker;}
	public static void setNowShapeMaker(AShape shapeMaker) {nowShapeMaker = shapeMaker;}
}
