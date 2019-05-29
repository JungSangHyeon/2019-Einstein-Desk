package global;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import component_Stuff.GraphicComponent;
import function_Shape.Shape_Mover;
import function_Shape.Shape_Resizer;
import moveAndZoom.DrawingPanelMoveAndZoom;
import shape_Stuff.eShape;

public class GCCanvas {

	static GraphicComponent canvas;
//	int gap = 100;
	int width = 1455, height = 820;
	int screenWidth = 1920, screenHeight = 1080;
	
	public GCCanvas() {
		canvas = new GraphicComponent();
		canvas.setFillColor(Color.white);
		canvas.setBorderPaint(false);
		
		canvas.addFunction(new Shape_Mover());
		canvas.addFunction(new Shape_Resizer());
		
		canvas.setAShape(eShape.rect.getAShape());
		
		canvas.addPoint(DrawingPanelMoveAndZoom.transformPoint(new Point2D.Float(screenWidth/2 - width/2, screenHeight/2 - height/2)));
		canvas.addPoint(DrawingPanelMoveAndZoom.transformPoint(new Point2D.Float(screenWidth/2 + width/2, screenHeight/2 + height/2)));
		canvas.setShape(canvas.getAShape().newShape(canvas.getPoints()));
		
		Rectangle rect = canvas.getShape().getBounds();
		canvas.setMyCenter(new Point2D.Float(rect.x+rect.width/2, rect.y+rect.height/2));
	}

	public static void paint(Graphics2D g2d) {canvas.paint(g2d);}
	public static void topPaint(Graphics2D g2d) {canvas.topPaint(g2d);}
	
	public static GraphicComponent getCanvas() {return canvas;}

	public static void processEvent(MouseEvent e) {
		canvas.setSelected(true);
		canvas.processEvent(e);
	}
}
