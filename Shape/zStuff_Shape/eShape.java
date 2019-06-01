package zStuff_Shape;

import CMC_NP_Shape.PolygonShape;
import PDR_2P_Shape.EllipseShape;
import PDR_2P_Shape.RectShape;
import PDR_2P_Shape.SpeechShape;
import PDR_2P_Shape.Star4Shape;
import PDR_2P_Shape.StarNShape;
import PDR_2P_Shape.StraightLineShape;
import PDR_2P_Shape.TriangleShape;
import PDR_NP_Shape.HighlightShape;
import PDR_NP_Shape.pen;
import special_Shape.GroupShape;

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

