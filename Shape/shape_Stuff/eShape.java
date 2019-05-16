package shape_Stuff;

import shape.EllipseShape;
import shape.pen;
import shape.HighlightShape;
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
	pen(new pen()), 
	highlight(new HighlightShape()), 
	;
	
	AShape shape;
	private eShape(AShape shape) {this.shape = shape;}
	public AShape getShape() {return shape;}
}

