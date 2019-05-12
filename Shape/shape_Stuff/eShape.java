package shape_Stuff;

import shape.EllipseShape;
import shape.FreeLineShape;
import shape.PolygonShape;
import shape.RectShape;
import shape.SpeechShape;
import shape.Star4Shape;
import shape.StraightLineShape;
import shape.TriangleShape;

public enum eShape{
	rect(new RectShape()), 
	ellipse(new EllipseShape()), 
	speech(new SpeechShape()), 
	star4(new Star4Shape()), 
	triangle(new TriangleShape()), 
	straightLine(new StraightLineShape()),
	
	polygon(new PolygonShape()), 
	freeLine(new FreeLineShape()), 
	;
	
	AShape shapeMaker;
	private eShape(AShape shapeMaker) {this.shapeMaker = shapeMaker;}
	public AShape getShapeMaker() {return shapeMaker;}
}

