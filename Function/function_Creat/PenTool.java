package function_Creat;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import data.GCStorage;
import data.GlobalData;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class PenTool extends PDRShapeDrawTool{
	private static final long serialVersionUID = -2451691127621671062L;
	
	Color lineColor = Color.BLACK;
	public void setLineColor(Color c) {lineColor = c;}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		GCData.setFillPaint(false);
		GCData.setborderThick(GlobalData.getPenThick());
		GCData.setBorderColor(GlobalData.getPenColor());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		GCStorage.getLastGC().addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage.getLastGC());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Rectangle rect = GCStorage.getLastGC().getShape().getBounds();
		GCStorage.getLastGC().setCenter(new Point2D.Float(rect.x+rect.width/2, rect.y+rect.height/2));
	}
}
