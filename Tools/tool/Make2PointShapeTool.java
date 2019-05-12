package tool;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import data.GlobalData;
import moveAndZoom.DrawingPanelMoveAndZoom;
import painter.TextPainter;
import processor.Mover;
import tool_Stuff.ATool;

public class Make2PointShapeTool extends ATool{
	private static final long serialVersionUID = 5000619643824217376L;
	
	public void mousePressed(MouseEvent e) {
		GCStorage.clearSelected();
		GraphicComponent GCData = new GraphicComponent();
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		GCData.addPainter(new TextPainter("testttt", "Icons/jake_22X22.txt"));
		GCData.addProcessor(new Mover());
		setShape(GCData);
		GCStorage.addNewGC(GCData);
	}

	public void mouseDragged(MouseEvent e) {
		GCStorage.getLastGC().setPoint(1, DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage.getLastGC());
	}

	private void setShape(GraphicComponent shapeData) {
		Point2D.Float p1 = shapeData.getPoints().get(0);
		Point2D.Float p2 = shapeData.getPoints().get(1);
		shapeData.setShape(GlobalData.getNow2PShape().getShapeMaker().newShape(p1, p2));
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
