package tool;

import java.awt.event.MouseEvent;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import data.GlobalData;
import moveAndZoom.DrawingPanelMoveAndZoom;
import processor.Mover;
import tool_Stuff.ATool;
import tool_Stuff.eTool;

public class CMCShapeDrawTool extends ATool{
	private static final long serialVersionUID = -2451691127621671062L;
	
	boolean firstClick = true;
	GraphicComponent GCData;
	
	public void mouseClicked(MouseEvent e) {
		if(firstClick) {initDrawing(e);}
		if(e.getClickCount()==1) {keepDrawing(e);}
		else if(e.getClickCount()==2) {finishDrawing(e);}
	}
	
	public void mouseMoved(MouseEvent e) {
		GCStorage.getLastGC().setLastPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage.getLastGC());
	}
	
	private void initDrawing(MouseEvent e) {
		GCStorage.clearSelected();
		GCData = new GraphicComponent();
//	GCData.addPainter(new TextPainter("testttt", "Icons/jake_22X22.txt"));
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		GCData.addProcessor(new Mover());
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
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
