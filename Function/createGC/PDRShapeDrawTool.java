package createGC;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Vector;

import canvasMoveAndZoom.DrawingPanelMoveAndZoom;
import fComposite.FInCanvasGCBasicFunction;
import onOff.AnchorPaint;
import redoUndo.RedoUndo;
import zStuff_Data.ShapeData;
import zStuff_Data.ToolData;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Tool.ATool;
import zStuff_Tool.eTool;

public class PDRShapeDrawTool extends ATool{
	private static final long serialVersionUID = -2451691127621671062L;
	
	GraphicComponent GCData;
	
	public enum state{ready, drawing} 
	state nowState = state.ready;
	boolean firstDragAfterPress = false;
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {//우클릭으로 화면 이동하면서 그릴 수 있게 함.
			nowState = state.drawing;
			firstDragAfterPress = true;
			
			GCStorage_Selected.clearSelected();
			GCData = new GraphicComponent();
			GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
			GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
			GCData.setAShape(ShapeData.getNowShapeMaker());
			GCData.addFunction(new FInCanvasGCBasicFunction());
			
			setShape(GCData);
			GCStorage_Normal.addNewGC(GCData);
		}
	}

	public void mouseDragged(MouseEvent e) {
		GCStorage_Normal.getLastGC().setLastPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage_Normal.getLastGC());
	}

	protected void setShape(GraphicComponent shapeData) {
		shapeData.setShape(ShapeData.getNowShapeMaker().newShape(shapeData.getPoints()));
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {
			Vector<Point2D.Float> points = GCStorage_Normal.getLastGC().getPoints();
			if(points.size()<3&&points.get(0).getX()==points.get(1).getX()&&points.get(0).getY()==points.get(1).getY()) {
				GCStorage_Normal.removeLastGC();//걍 점만찍은 쉐입은 죽인다
			}else {
				Rectangle rect = GCStorage_Normal.getLastGC().getShape().getBounds();
				GCStorage_Normal.getLastGC().setCenter(new Point2D.Float(rect.x+rect.width/2, rect.y+rect.height/2));
				
				ToolData.setNowTool(eTool.eHandTool.getATool());
				GCStorage_Selected.addSelectedGC(GCStorage_Normal.getLastGC());
				AnchorPaint.on();
				RedoUndo.saveNowInHistory();
			}
			
		}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseWheelMoved(MouseEvent e) {}
}
