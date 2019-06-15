package createGC;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Vector;

import canvasMoveAndZoom.GlobalAT;
import redoUndo.RedoUndo;
import zStuff_Data.LineData;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GraphicComponent;

public class HighlightTool extends PDRShapeDrawTool{
	private static final long serialVersionUID = -2451691127621671062L;
	
	Color lineColor = Color.BLACK;
	public void setLineColor(Color c) {lineColor = c;}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		GCData.setFillPaint(false);
		GCData.setborderThick(LineData.getHighlightThick());
		GCData.setBorderColor(LineData.getHighlightColor());
	}
	
	@Override
	public void toolPaint(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(1));
		Point nowMousePoint = MouseInfo.getPointerInfo().getLocation();
		if(notOnPanel(nowMousePoint)) {
			g2d.setColor(new Color(166,166,184));
			double diameter = LineData.getHighlightThick()*GlobalAT.getZoom();
			g2d.draw(new Ellipse2D.Double(nowMousePoint.x - diameter/2, nowMousePoint.y - diameter/2 , diameter, diameter ));
		}
	}
	
	private boolean notOnPanel(Point point) {
		for (GraphicComponent panel : GCPanelStorage.getGCPanelVector()) {
			if (panel.getShape().contains(point)) {return false;}
		}
		return true;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		GCStorage_Normal.getLastGC().addPoint(GlobalAT.transformPoint(e.getPoint()));
		setShape(GCStorage_Normal.getLastGC());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Vector<Point2D.Float> points = GCStorage_Normal.getLastGC().getPoints();
		if(points.size()<8) {
			GCStorage_Normal.removeLastGC();//°Á Á¡¸¸ÂïÀº ½¦ÀÔÀº Á×ÀÎ´Ù
		}else {
			Rectangle rect = GCStorage_Normal.getLastGC().getShape().getBounds();
			GCStorage_Normal.getLastGC().setCenter(new Point2D.Float(rect.x+rect.width/2, rect.y+rect.height/2));
			RedoUndo.saveNowInHistory();
		}
	}
}
