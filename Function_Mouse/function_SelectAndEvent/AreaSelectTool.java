package function_SelectAndEvent;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import data.GlobalData;
import function_Shape.Shape_Mover;
import function_Stuff.ATool;
import moveAndZoom.DrawingPanelMoveAndZoom;
import shape_Stuff.AShape;
import shape_Stuff.eShape;

public class AreaSelectTool extends ATool{//extends TwoPointShapeTool 할까나. 아님 분리된 상태가 더 좋은가?
	private static final long serialVersionUID = 3537773334283291318L;
	
	AShape before2PShape;
	Color areaSelectRectColor = new Color(100,100,100,130);
	
	public void mousePressed(MouseEvent e) {
		before2PShape = GlobalData.getNowShapeMaker();
		GlobalData.setNowShapeMaker(eShape.rect.getAShape());
		
		GraphicComponent GCData = new GraphicComponent();
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		GCData.addFunction(new Shape_Mover());
		GCData.setFillColor(areaSelectRectColor);
		GCData.setBorderPaint(false);
		setShape(GCData);
		GCStorage.addNewGC(GCData);
	}

	public void mouseDragged(MouseEvent e) {
		GCStorage.getLastGC().setPoint(1, DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage.getLastGC());
	}

	private void setShape(GraphicComponent shapeData) {
		shapeData.setShape(GlobalData.getNowShapeMaker().newShape(shapeData.getPoints()));
	}
	
	public void mouseReleased(MouseEvent e) {
		Rectangle2D area = GCStorage.getLastGC().getShape().getBounds2D();
		GCStorage.removeGC(GCStorage.getLastGC());
		GlobalData.setNowShapeMaker(before2PShape);
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
	public void mouseWheelMoved(MouseEvent e) {}
}
