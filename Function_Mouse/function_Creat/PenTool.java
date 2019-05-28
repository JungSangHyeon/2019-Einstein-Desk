package function_Creat;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import data.GCStorage_Normal;
import data.LineData;
import doUndo.RedoUndo;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class PenTool extends PDRShapeDrawTool{
	private static final long serialVersionUID = -2451691127621671062L;
	
	Color lineColor = Color.BLACK;
	public void setLineColor(Color c) {lineColor = c;}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		GCData.setFillPaint(false);
		GCData.setborderThick(LineData.getPenThick());
		GCData.setBorderColor(LineData.getPenColor());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		GCStorage_Normal.getLastGC().addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage_Normal.getLastGC());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Rectangle rect = GCStorage_Normal.getLastGC().getShape().getBounds();
		GCStorage_Normal.getLastGC().setMyCenter(new Point2D.Float(rect.x+rect.width/2, rect.y+rect.height/2));
		RedoUndo.saveNowInHistory();
	}
}
