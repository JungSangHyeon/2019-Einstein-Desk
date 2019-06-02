package canvasMoveAndZoom;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import calculation.AffineMath;
import canvas.CanvasGC;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GraphicComponent;

public class DrawingPanelMoveAndZoom {

	static int maxZoomLevel = 10, minZoomLevel = -20;//알어서 바꿔 쓰시오
	static float zoomFactor = 1.2f;
	public static float nowZoom = 1;
	
	private static int zoomLevel = 0;
	private static Point dragStartPoint;
	
	public static void zoomCamera(MouseWheelEvent e) {
		AffineTransform coordTransform = new AffineTransform();
		Point2D p1 = e.getPoint();
		
		float zoom = 1;
		if (e.getWheelRotation() > 0&&zoomLevel<maxZoomLevel) {zoomLevel++;zoom = 1 / zoomFactor;}
		else if(e.getWheelRotation() < 0&&zoomLevel>minZoomLevel){zoomLevel--;zoom =zoomFactor;}
		nowZoom*=zoom;
		coordTransform.scale(zoom, zoom); 
		
		Point2D.Float p2 = new Point2D.Float();
		try {coordTransform.createInverse().transform(p1, p2);}catch (Exception ex) {ex.printStackTrace();}
		coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
		
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {
			AffineMath.applyAffineTransformToGC(coordTransform, gc);
			gc.setborderThick(gc.getBorderThick()*zoom);
			for(GraphicComponent aggreGC : gc.getAllAggregateGCs()) {
				AffineMath.applyAffineTransformToGC(coordTransform, aggreGC);
				gc.setborderThick(aggreGC.getBorderThick()*zoom);
			}
		}
		AffineMath.applyAffineTransformToGC(coordTransform, CanvasGC.getCanvas());
		CanvasGC.getCanvas().setborderThick(CanvasGC.getCanvas().getBorderThick()*zoom);
	}

	public static void setDragStartPoint(Point p) {dragStartPoint = p;}
	public static void moveCamera(MouseEvent e) {
		AffineTransform coordTransform = new AffineTransform();
		Point dragEndPoint = e.getPoint();
		coordTransform.translate(dragEndPoint.getX() - dragStartPoint.getX(), dragEndPoint.getY() - dragStartPoint.getY());
		
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {
			AffineMath.applyAffineTransformToGC(coordTransform, gc);
			for(GraphicComponent aggreGC : gc.getAllAggregateGCs()) {
				AffineMath.applyAffineTransformToGC(coordTransform, aggreGC);
			}
		}
		AffineMath.applyAffineTransformToGC(coordTransform, CanvasGC.getCanvas());
		dragStartPoint = dragEndPoint;
	}
}