package tool;

import java.awt.Color;
import java.awt.event.MouseEvent;

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
	
}
