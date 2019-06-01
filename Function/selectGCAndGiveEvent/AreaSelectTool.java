package selectGCAndGiveEvent;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import canvasMoveAndZoom.DrawingPanelMoveAndZoom;
import zStuff_Data.ShapeData;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Shape.AShape;
import zStuff_Shape.eShape;
import zStuff_Tool.ATool;

public class AreaSelectTool extends ATool{
	private static final long serialVersionUID = 3537773334283291318L;
	
	AShape before2PShape;
	Color areaSelectRectColor = new Color(100,100,100,130);
	
	public void mousePressed(MouseEvent e) {
		before2PShape = ShapeData.getNowShapeMaker();
		ShapeData.setNowShapeMaker(eShape.rect.getAShape());
		
		GraphicComponent GCData = new GraphicComponent();
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		GCData.setFillColor(areaSelectRectColor);
		GCData.setBorderPaint(false);
		setShape(GCData);
		GCStorage_Normal.addNewGC(GCData);
	}

	public void mouseDragged(MouseEvent e) {
		GCStorage_Normal.getLastGC().setPoint(1, DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage_Normal.getLastGC());
	}

	private void setShape(GraphicComponent shapeData) {
		shapeData.setShape(ShapeData.getNowShapeMaker().newShape(shapeData.getPoints()));
	}
	
	public void mouseReleased(MouseEvent e) {
		Rectangle2D area = GCStorage_Normal.getLastGC().getShape().getBounds2D();
		GCStorage_Normal.removeGC(GCStorage_Normal.getLastGC());
		ShapeData.setNowShapeMaker(before2PShape);
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {
			if(area.contains(gc.getShape().getBounds2D())) {
				GCStorage_Selected.addSelectedGC(gc);
			}
		}
	}
}
