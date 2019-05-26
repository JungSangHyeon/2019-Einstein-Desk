package eventListener;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.util.Vector;

import javax.swing.JPanel;

import component_Stuff.GraphicComponent;
import data.GCStorage_Normal;
import data.GCStorage_Selected;
import data.ToolData;
import dragAndDrop.DragAndDropManager;
import function_Paint.Paint_TextWrite_Stuff;
import function_SelectAndEvent.GCPanelGiveActionTool;
import moveAndZoom.DrawingPanelMoveAndZoom;
import view.DrawingPanel;

public class DrawingPanelMouseHadler implements MouseListener, MouseMotionListener, MouseWheelListener{//manage all event except AContainer

	DrawingPanel drawingPanel;//Master Panel
	int pressedBTN = -1;//for identify dragging BTN   
	//Left BTN = modify Data
	//Right BTN = Move & Zoom
	
	GCPanelGiveActionTool gCPanelGiveActionTool;
	public DrawingPanelMouseHadler(DrawingPanel drawingPanel) {
		this.drawingPanel=drawingPanel;
		gCPanelGiveActionTool = new GCPanelGiveActionTool();
	}

	public void mousePressed(MouseEvent e) {//event to tool, Canvas Move
		gCPanelGiveActionTool.processEvent(e);
		
		if(Paint_TextWrite_Stuff.isTextEditAreaFocusOwner()) {
			Paint_TextWrite_Stuff.removeFocusTextEditArea();
		}
		if(pressedBTN==-1) {//null
			pressedBTN = e.getButton();
			if(leftBTNPressed()||wheelBTNPressed()){
				if(gCPanelGiveActionTool.getMaster()==null) {
					ToolData.getNowTool().processEvent(e);
				}
			}
			else if(rightBTNPressed()) {DrawingPanelMoveAndZoom.setDragStartPoint(e.getPoint());}
			drawingPanel.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {//event to tool
		gCPanelGiveActionTool.processEvent(e);
		if(leftBTNPressed()||wheelBTNPressed()){
			if(gCPanelGiveActionTool.getMaster()==null) {
				ToolData.getNowTool().processEvent(e);
		}}
		drawingPanel.repaint();
		if(e.getButton() == pressedBTN) {
			pressedBTN = -1;//null
		}
	}
	
	public void mouseDragged(MouseEvent e) {//event to tool, D&D panel Check, Canvas Move
		gCPanelGiveActionTool.processEvent(e);
		if(leftBTNPressed()||wheelBTNPressed()){
			DragAndDropManager.setComponentMasterPanel(drawingPanel);
			if(gCPanelGiveActionTool.getMaster()==null) {
				ToolData.getNowTool().processEvent(e);
			}
		} else if (rightBTNPressed()) {DrawingPanelMoveAndZoom.moveCamera(e);}
		drawingPanel.repaint();
	}
	
	public void mouseMoved(MouseEvent e) {//event to tool & on Shape <-- 다른건 죄다 Select후 tool이 전달함.
		gCPanelGiveActionTool.processEvent(e);
		if(gCPanelGiveActionTool.getMaster()==null) {
			ToolData.getNowTool().processEvent(e);
			boolean onNothing = true;//for cursor control
			Vector<GraphicComponent> Components = GCStorage_Selected.getSelectedGCVector();
			for(int i=Components.size()-1; i>-1; i--) {
				if(Components.get(i).isTopSelected(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {
					onNothing = false;
					Components.get(i).processEvent(e);//HMMMMMMMMMMMMMMMM
					break;
				}
			}
			if(onNothing) {
				for(int i=Components.size()-1; i>-1; i--) {
					if(Components.get(i).getAShape().isSelected(Components.get(i), DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {
						onNothing = false;
						Components.get(i).processEvent(e);//HMMMMMMMMMMMMMMMM
						break;
					}
				}
			}
			if(onNothing) {((JPanel) e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}
		}
		drawingPanel.repaint();
	}
	
	public void mouseClicked(MouseEvent e) {//event to tool
		gCPanelGiveActionTool.processEvent(e);
		if(gCPanelGiveActionTool.getMaster()==null) {
			ToolData.getNowTool().processEvent(e);
		}
		drawingPanel.repaint();
	}
	
	public void mouseEntered(MouseEvent e) {DragAndDropManager.setNowMouseOnPanel(drawingPanel);}//only D&D
	public void mouseWheelMoved(MouseWheelEvent e) {
		gCPanelGiveActionTool.processEvent(e);
		if(gCPanelGiveActionTool.getMaster()==null) {
			ToolData.getNowTool().processEvent(e);
			Vector<GraphicComponent> Components = GCStorage_Normal.getGCVector();
			Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());
			for(int i=Components.size()-1; i>-1; i--) {
				if(Components.get(i).getAShape().isSelected(Components.get(i), nowPoint)) {
					Components.get(i).processEvent(e);
					break;
				}
			}
			
			DrawingPanelMoveAndZoom.zoomCamera(e);
		}
		drawingPanel.repaint();
		
		}//only zoom
	
	private boolean leftBTNPressed() {return pressedBTN == MouseEvent.BUTTON1;}
	private boolean wheelBTNPressed() {return pressedBTN == MouseEvent.BUTTON2;}
	private boolean rightBTNPressed() {return pressedBTN == MouseEvent.BUTTON3;}
	
	public void mouseExited(MouseEvent e) {}
}
