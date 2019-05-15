package tool;

import java.awt.Color;
import java.awt.event.MouseEvent;

import data.GCStorage;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class LineDrawTool extends PDRShapeDrawTool{
	private static final long serialVersionUID = -2451691127621671062L;
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		GCData.setFillPaint(false);
		GCData.setborderThick(10);
		GCData.setBorderColor(Color.BLACK);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		GCStorage.getLastGC().addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage.getLastGC());
	}
	
}
