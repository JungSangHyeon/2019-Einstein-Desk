package shapeMaker_Stuff;

import shapeMaker.EllipseShapeMaker;
import shapeMaker.RectShapeMaker;
import shapeMaker.SpeechShapeMaker;
import shapeMaker.Star4ShapeMaker;
import shapeMaker.StraightLineShapeMaker;
import shapeMaker.TriangleShapeMaker;

public class ShapeMakerEnum {

	public enum e2PShapeMaker{
		rect(new RectShapeMaker()), 
		ellipse(new EllipseShapeMaker()), 
		speech(new SpeechShapeMaker()), 
		star4(new Star4ShapeMaker()), 
		triangle(new TriangleShapeMaker()), 
		line(new StraightLineShapeMaker()), 
		;
		
		A2PShapeMaker shapeMaker;
		private e2PShapeMaker(A2PShapeMaker shapeMaker) {this.shapeMaker = shapeMaker;}
		public A2PShapeMaker getShapeMaker() {return shapeMaker;}
	}
}
