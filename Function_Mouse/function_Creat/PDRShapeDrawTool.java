package function_Creat;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import component_Stuff.GraphicComponent;
import data.GCStorage_Normal;
import data.GCStorage_Selected;
import data.ShapeData;
import data.ToolData;
import doUndo.RedoUndo;
import function_Paint.Paint_TextWrite;
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
			GCStorage_Selected.clearSelected();
			GCData = new GraphicComponent();
			GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
			GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
			GCData.setAShape(ShapeData.getNowShapeMaker());
			GCData.addFunction(new Shape_Mover());
			GCData.addFunction(new Shape_Rotator());
			GCData.addFunction(new Shape_Resizer());
			GCData.addFunction(new Paint_TextWrite());
			
//			GCData.setOtherCenter(new Point2D.Float(0, 0));
//			GCData.useOtherCenter();
			
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
			Rectangle rect = GCStorage_Normal.getLastGC().getShape().getBounds();
			GCStorage_Normal.getLastGC().setMyCenter(new Point2D.Float(rect.x+rect.width/2, rect.y+rect.height/2));
			ToolData.setNowTool(eTool.eHandTool.getATool());
			GCStorage_Selected.addSelectedGC(GCStorage_Normal.getLastGC());
			AnchorPaint.on();
			RedoUndo.saveNowInHistory();
		}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseWheelMoved(MouseEvent e) {}
}
