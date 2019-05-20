package function_Creat;

import java.awt.event.MouseEvent;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import data.GlobalData;
import function_Shape.Shape_Mover;
import function_Stuff.ATool;
import function_Stuff.eTool;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class CMCShapeDrawTool extends ATool{
	private static final long serialVersionUID = -2451691127621671062L;
	
	boolean firstClick = true;
	GraphicComponent GCData;
	
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {//우클릭으로 화면 이동하면서 그릴 수 있게 함.
			if(firstClick) {initDrawing(e);}
			if(e.getClickCount()==1) {keepDrawing(e);}
			else if(e.getClickCount()==2) {finishDrawing(e);}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if(GCData!=null) {
			setShape(GCStorage.getLastGC());
			GCData.setLastPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		}
	}
	
	private void initDrawing(MouseEvent e) {
		GCStorage.clearSelected();
		GCData = new GraphicComponent();
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		GCData.addFunction(new Shape_Mover());
		GCData.setAShape(GlobalData.getNowShapeMaker());
		GCStorage.addNewGC(GCData);
		firstClick=false;
	}

	private void keepDrawing(MouseEvent e) {
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCData);
	}

	private void finishDrawing(MouseEvent e) {
		firstClick = true;
		GCData = null;
		GlobalData.setNowTool(eTool.eHandTool.getTool());
	}

	private void setShape(GraphicComponent shapeData) {
		shapeData.setShape(GlobalData.getNowShapeMaker().newShape(shapeData.getPoints()));
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {GlobalData.setNowTool(eTool.eHandTool.getTool());}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
