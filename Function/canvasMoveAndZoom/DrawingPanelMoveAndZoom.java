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

	static int maxZoomLevel = 10, minZoomLevel = -150;//알어서 바꿔 쓰시오
	static float zoomFactor = 1.2f;
//	public static float nowZoom = 1;
	static AffineTransform nowApplied = new AffineTransform();
	
	private static int zoomLevel = 0;
	private static Point dragStartPoint;
	
	public static float getZoom() {
		return (float)nowApplied.getScaleX();//x=y니께
	}
	
	public static void zoomCamera(MouseWheelEvent e) {//그래픽에다하면, 마우스 이벤트의 일반화가 안됨.
		AffineTransform coordTransform = new AffineTransform();
		Point2D p1 = e.getPoint();
		
		float zoom = 1;
		if (e.getWheelRotation() > 0&&zoomLevel<maxZoomLevel) {zoomLevel++;zoom = 1 / zoomFactor;}
		else if(e.getWheelRotation() < 0&&zoomLevel>minZoomLevel){zoomLevel--;zoom =zoomFactor;}
		coordTransform.scale(zoom, zoom); 
		nowApplied.scale(zoom, zoom); 
		
		Point2D.Float p2 = new Point2D.Float();
		try {coordTransform.createInverse().transform(p1, p2);}catch (Exception ex) {ex.printStackTrace();}
		
		coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
		nowApplied.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
		
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
		nowApplied.translate(dragEndPoint.getX() - dragStartPoint.getX(), dragEndPoint.getY() - dragStartPoint.getY());
		
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