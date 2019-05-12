package tool;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import data.GlobalData;
import moveAndZoom.DrawingPanelMoveAndZoom;
import processor.Mover;
import shapeMaker_Stuff.ShapeMakerEnum.e2PShapeMaker;
import tool_Stuff.ATool;

public class AreaSelectTool extends ATool{
	private static final long serialVersionUID = 3537773334283291318L;
	
	e2PShapeMaker before2PShape;
	
	public void mousePressed(MouseEvent e) {
		before2PShape = GlobalData.getNow2PShape();
		GlobalData.setNow2PShape(e2PShapeMaker.rect);
		
		GraphicComponent GCData = new GraphicComponent();
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		GCData.addProcessor(new Mover());
		GCData.setFillColor(new Color(100,100,100,100));
		GCData.setBorderColor(new Color(0,0,0,1));
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
	
	public void mouseReleased(MouseEvent e) {
		Rectangle2D area = GCStorage.getLastGC().getShape().getBounds2D();
		GCStorage.removeGC(GCStorage.getLastGC());
		
		GlobalData.setNow2PShape(before2PShape);
		for(GraphicComponent gc : GCStorage.getGCVector()) {
			if(area.contains(gc.getShape().getBounds2D())) {
				GCStorage.addSelectedGC(gc);
			}
		}
		
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
