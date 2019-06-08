package saveAsImage;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Vector;

import PDR_NP_Shape.HighlightShape;
import PDR_NP_Shape.pen;
import canvas.CanvasGC;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class ImgManager {
	
	public static BufferedImage getImage(Vector<GraphicComponent> gcVector) {
		GCStorage_Selected.clearSelected();
		
		//Make Image
		GraphicComponent canvas = CanvasGC.getCanvas();
		Rectangle canvasBound = canvas.getShape().getBounds();
		canvas.setBorderPaint(false);
		BufferedImage image = new BufferedImage((int)canvasBound.getWidth(), (int)canvasBound.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.translate(-canvasBound.getX(), -canvasBound.getY());
		
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {gc.bottumPaint(g);}
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {if(!(gc.getAShape() instanceof pen)) {gc.paint(g);}}//shape
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {if(gc.getAShape() instanceof HighlightShape) {gc.paint(g);}}//highlight
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {if(!(gc.getAShape() instanceof HighlightShape)&&gc.getAShape() instanceof pen) {gc.paint(g);}}//pen
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {gc.topPaint(g);}
		
		g.dispose();
		
		//Re do Zoom & Move
		canvas.setBorderPaint(true);
		
		return image;
	}
}
