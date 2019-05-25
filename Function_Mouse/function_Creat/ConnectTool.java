package function_Creat;

import java.awt.event.MouseEvent;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import data.GlobalData;
import function_Paint.Paint_ConnectLine;
import function_Stuff.ATool;
import moveAndZoom.DrawingPanelMoveAndZoom;
import shape_Stuff.eShape;

public class ConnectTool extends ATool{
	private static final long serialVersionUID = 1746179459505183985L;

	GraphicComponent GCData, startGC, endGC;
	boolean haveStartGC = false;
	boolean haveEndGC = false;
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {
			//Find Start GC
			for(GraphicComponent gc : GCStorage.getGCVector()) {
				if(gc.getAShape().isSelected(gc, DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {
					startGC = gc;
					haveStartGC = true;
					break;
				}
			}
			
			//Create Connect Line
			if(haveStartGC) {
				GCStorage.clearSelected();
				GCData = new GraphicComponent();
				GCData.addPoint(startGC.getCenter());
				GCData.addPoint(startGC.getCenter());
				GCData.setAShape(eShape.straightLine.getAShape());
				setShape(GCData);
				GCStorage.addNewGC(GCData);
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		//Dragging End Of Line
		if(haveStartGC) {
			GCStorage.getLastGC().setLastPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
			setShape(GCStorage.getLastGC());
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1&&haveStartGC) {
			//Find End GC
			for(GraphicComponent gc : GCStorage.getGCVector()) {
				if(gc.getAShape().isSelected(gc, DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {
					endGC = gc;
					haveEndGC = true;
					break;
				}
			}
			
			//add Function to StartGC & remove Line
			if(haveEndGC) {
				GCStorage.getLastGC().addFunction(new connectLineShapeSetter(startGC, endGC));
//				startGC.addFunction(new Paint_ConnectLine(endGC));
			}
			
			//Reset
			GCData = null;
			startGC = null; 
			endGC = null;
			haveStartGC = false;
			haveEndGC = false;
		}
	}
	
	protected void setShape(GraphicComponent shapeData) {
		shapeData.setShape(GlobalData.getNowShapeMaker().newShape(shapeData.getPoints()));
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
