package function_Creat;

import java.awt.event.MouseEvent;

import component_Stuff.GraphicComponent;
import data.GCStorage_Normal;
import data.GCStorage_Selected;
import data.ShapeData;
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
			for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {
				if(gc.getAShape().thisGCIsSelected(gc, DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {
					startGC = gc;
					haveStartGC = true;
					break;
				}
			}
			
			//Create Connect Line
			if(haveStartGC) {
				GCStorage_Selected.clearSelected();
				GCData = new GraphicComponent();
				GCData.addPoint(startGC.getCenter());
				GCData.addPoint(startGC.getCenter());
				GCData.setAShape(eShape.straightLine.getAShape());
				setShape(GCData);
				GCStorage_Normal.addNewGC(GCData);
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		//Dragging End Of Line
		if(haveStartGC) {
			GCStorage_Normal.getLastGC().setLastPoint(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()));
			setShape(GCStorage_Normal.getLastGC());
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1&&haveStartGC) {
			//Find End GC
			for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {
				if(gc.getAShape().thisGCIsSelected(gc, DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {
					endGC = gc;
					haveEndGC = true;
					break;
				}
			}
			
			//add Function to StartGC & remove Line
			if(haveEndGC) {
				GCStorage_Normal.getLastGC().addFunction(new connectLineShapeSetter(startGC, endGC));
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
		shapeData.setShape(ShapeData.getNowShapeMaker().newShape(shapeData.getPoints()));
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseWheelMoved(MouseEvent e) {}
}
