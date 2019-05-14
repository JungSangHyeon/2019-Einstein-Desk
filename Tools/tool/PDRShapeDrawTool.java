package tool;

import java.awt.event.MouseEvent;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import data.GlobalData;
import moveAndZoom.DrawingPanelMoveAndZoom;
import processor.Mover;
import tool_Stuff.ATool;

public class PDRShapeDrawTool extends ATool{
	private static final long serialVersionUID = -2451691127621671062L;
	
	GraphicComponent GCData;
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {//우클릭으로 화면 이동하면서 그릴 수 있게 함.
			GCStorage.clearSelected();
			GCData = new GraphicComponent();
			GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
			GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
			GCData.addProcessor(new Mover());
			setShape(GCData);
			GCStorage.addNewGC(GCData);
		}
	}

	public void mouseDragged(MouseEvent e) {
		GCStorage.getLastGC().addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage.getLastGC());
	}

	private void setShape(GraphicComponent shapeData) {
		shapeData.setShape(GlobalData.getNowShapeMaker().newShape(shapeData.getPoints()));
	}
	
	public void mouseReleased(MouseEvent e) {}//GlobalData.setNowTool(eTool.eHandTool.getTool());
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
