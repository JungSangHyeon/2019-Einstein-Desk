package shape_Stuff;

import shape.EllipseShape;
import shape.GroupShape;
import shape.HighlightShape;
import shape.PolygonShape;
import shape.RectShape;
import shape.SpeechShape;
import shape.Star4Shape;
import shape.StarNShape;
import shape.StraightLineShape;
import shape.TriangleShape;
import shape.pen;

public enum eShape{
	rect(new RectShape()), 
	ellipse(new EllipseShape()), 
	speech(new SpeechShape()), 
	star4(new Star4Shape()), 
	starN(new StarNShape()), 
	triangle(new TriangleShape()), 
	straightLine(new StraightLineShape()),
	polygon(new PolygonShape()), 
	pen(new pen()), 
	highlight(new HighlightShape()), 
	group(new GroupShape()), 
	;
	
	AShape shape;
	private eShape(AShape shape) {this.shape = shape;}
	public AShape getAShape() {return shape;}
}

