package function_Creat;

import java.awt.Rectangle; 
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import data.GlobalData;
import doUndo.RedoUndo;
import function_Shape.Shape_Mover;
import function_Shape.Shape_Resizer;
import function_Shape.Shape_Rotator;
import function_Stuff.ATool;
import function_Stuff.eTool;
import moveAndZoom.DrawingPanelMoveAndZoom;
import onOff.AnchorPaint;

public class PDRShapeDrawTool extends ATool{
	private static final long serialVersionUID = -2451691127621671062L;
	
	GraphicComponent GCData;
	
	public enum state{ready, drawing} 
	state nowState = state.ready;
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {//우클릭으로 화면 이동하면서 그릴 수 있게 함.
			GCStorage.clearSelected();
			GCData = new GraphicComponent();
			GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
			GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
			GCData.setAShape(GlobalData.getNowShapeMaker());
			GCData.addFunction(new Shape_Mover());
			GCData.addFunction(new Shape_Rotator());
			GCData.addFunction(new Shape_Resizer());
			setShape(GCData);
			GCStorage.addNewGC(GCData);
		}
	}

	public void mouseDragged(MouseEvent e) {
		GCStorage.getLastGC().setLastPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage.getLastGC());
	}

	protected void setShape(GraphicComponent shapeData) {
		shapeData.setShape(GlobalData.getNowShapeMaker().newShape(shapeData.getPoints()));
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {
			Rectangle rect = GCStorage.getLastGC().getShape().getBounds();
			GCStorage.getLastGC().setCenter(new Point2D.Float(rect.x+rect.width/2, rect.y+rect.height/2));
			GCStorage.addSelectedGC(GCStorage.getLastGC());
			AnchorPaint.on();
			GlobalData.setNowTool(eTool.eHandTool.getTool());
			RedoUndo.saveNowInHistory();
		}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
