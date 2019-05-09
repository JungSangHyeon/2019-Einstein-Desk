package twoPointShapeStuff;

import shapeMakers.EllipseShapeMaker;
import shapeMakers.RectShapeMaker;
import shapeMakers.SpeechShapeMaker;
import shapeMakers.Star4ShapeMaker;
import shapeMakers.StraightLineShapeMaker;
import shapeMakers.TriangleShapeMaker;

public class ShapeEnum {

	public enum e2PShape{
		rect(new RectShapeMaker()), 
		ellipse(new EllipseShapeMaker()), 
		speech(new SpeechShapeMaker()), 
		star4(new Star4ShapeMaker()), 
		triangle(new TriangleShapeMaker()), 
		line(new StraightLineShapeMaker()), 
		;
		
		A2PShapeMaker shapeMaker;
		private e2PShape(A2PShapeMaker shapeMaker) {this.shapeMaker = shapeMaker;}
		public A2PShapeMaker getShapeMaker() {return shapeMaker;}
	}
}
