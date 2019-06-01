package data;

import zStuff_Shape.AShape;
import zStuff_Shape.eShape;

public class ShapeData {
	private static AShape nowShapeMaker = eShape.rect.getAShape();
	public static AShape getNowShapeMaker() {return nowShapeMaker;}
	public static void setNowShapeMaker(AShape shapeMaker) {nowShapeMaker = shapeMaker;}
}
