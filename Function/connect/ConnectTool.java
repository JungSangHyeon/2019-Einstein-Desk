package connect;

import java.awt.Color;
import java.awt.event.MouseEvent;

import canvasMoveAndZoom.GlobalAT;
import redoUndo.RedoUndo;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Shape.eShape;
import zStuff_Tool.ATool;

public class ConnectTool extends ATool{
	private static final long serialVersionUID = 1746179459505183985L;

	GraphicComponent GCData, startGC, endGC;
	boolean haveStartGC = false;
	boolean haveEndGC = false;
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {
			//Find Start GC
			for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {
				if(gc.getAShape().thisGCIsSelected(gc, GlobalAT.transformPoint(e.getPoint()))) {
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
				GCData.setborderThick(10*GlobalAT.getZoom());
				GCData.setBorderColor(new Color(255, 192, 0));
				GCData.setAShape(eShape.straightLine.getAShape());
				GCData.setTakeEvent(false);
				setShape(GCData);
				GCStorage_Normal.addNewGC(GCData);
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		//Dragging End Of Line
		if(haveStartGC) {
			GCStorage_Normal.getLastGC().setLastPoint(GlobalAT.transformPoint(e.getPoint()));
			setShape(GCStorage_Normal.getLastGC());
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1&&haveStartGC) {
			//Find End GC
			for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {
				if(gc!=GCData&&gc.getAShape().thisGCIsSelected(gc, GlobalAT.transformPoint(e.getPoint()))) {
					endGC = gc;
					haveEndGC = true;
					break;
				}
			}
			//add Function to StartGC & remove Line
			if(haveEndGC) {
				GCStorage_Normal.getLastGC().addFunction(new connectLineShapeSetter(startGC, endGC));
				GCData.setFillPaint(false);
				GCData.setBorderPaint(false);
				RedoUndo.saveNowInHistory();
//				startGC.addFunction(new Paint_ConnectLine(endGC));
			}else {
				GCStorage_Normal.removeLastGC();
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
		shapeData.setShape(shapeData.getAShape().newShape(shapeData.getPoints()));
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseWheelMoved(MouseEvent e) {}
}
