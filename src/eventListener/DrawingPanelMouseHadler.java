package eventListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import data.GlobalData;
import dragAndDrop.DragAndDropManager;
import moveAndZoom.DrawingPanelMoveAndZoom;
import view.DrawingPanel;

public class DrawingPanelMouseHadler implements MouseListener, MouseMotionListener, MouseWheelListener{//manage all event except AContainer

	DrawingPanel drawingPanel;//Master Panel
	int pressedBTN;//for identify dragging BTN   
	//Left BTN = modify Data
	//Right BTN = Move & Zoom
	
	public DrawingPanelMouseHadler(DrawingPanel drawingPanel) {this.drawingPanel=drawingPanel;}

	public void mousePressed(MouseEvent e) {
		pressedBTN = e.getButton();
		if(leftBTNPressed()){GlobalData.getNowTool().processEvent(e);}
		else if(rightBTNPressed()) {DrawingPanelMoveAndZoom.setDragStartPoint(e.getPoint());}
		drawingPanel.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		if(leftBTNPressed()){GlobalData.getNowTool().processEvent(e);}
		drawingPanel.repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		if(leftBTNPressed()){
			DragAndDropManager.setComponentMasterPanel(drawingPanel);
			GlobalData.getNowTool().processEvent(e);
		} else if (rightBTNPressed()) {DrawingPanelMoveAndZoom.moveCamera(e);}
		drawingPanel.repaint();
	}
	
	public void mouseEntered(MouseEvent e) {DragAndDropManager.setNowMouseOnPanel(drawingPanel);}
	public void mouseWheelMoved(MouseWheelEvent e) {DrawingPanelMoveAndZoom.zoomCamera(e);drawingPanel.repaint();}
	public void mouseMoved(MouseEvent e) {GlobalData.getNowTool().processEvent(e);drawingPanel.repaint();}
	public void mouseClicked(MouseEvent e) {
		if(leftBTNPressed()){GlobalData.getNowTool().processEvent(e);}
		drawingPanel.repaint();
	}
	
	private boolean leftBTNPressed() {return pressedBTN == MouseEvent.BUTTON1;}
	private boolean rightBTNPressed() {return pressedBTN == MouseEvent.BUTTON3;}
	
	public void mouseExited(MouseEvent e) {}

}
