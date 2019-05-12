package tool;

import java.awt.event.MouseEvent;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import data.GlobalData;
import moveAndZoom.DrawingPanelMoveAndZoom;
import processor.Mover;
import tool_Stuff.ATool;
import tool_Stuff.eTool;

public class PDRShapeDrawTool extends ATool{
	private static final long serialVersionUID = -2451691127621671062L;
	
	public void mousePressed(MouseEvent e) {
		GCStorage.clearSelected();
		GraphicComponent GCData = new GraphicComponent();
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		GCData.addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
//		GCData.addPainter(new TextPainter("testttt", "Icons/jake_22X22.txt"));
		GCData.addProcessor(new Mover());
		setShape(GCData);
		GCStorage.addNewGC(GCData);
	}

	public void mouseDragged(MouseEvent e) {
		GCStorage.getLastGC().addPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage.getLastGC());
	}

	private void setShape(GraphicComponent shapeData) {
		shapeData.setShape(GlobalData.getNowShapeMaker().newShape(shapeData.getPoints()));
	}
	
	public void mouseReleased(MouseEvent e) {GlobalData.setNowTool(eTool.eHandTool.getTool());}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
