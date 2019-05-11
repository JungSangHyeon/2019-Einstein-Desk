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

public class DrawingPanelMouseHadler implements MouseListener, MouseMotionListener, MouseWheelListener{

	DrawingPanel drawingPanel;
	int pressedBTN;
	
	public DrawingPanelMouseHadler(DrawingPanel drawingPanel) {this.drawingPanel=drawingPanel;}

	public void mousePressed(MouseEvent e) {
		pressedBTN = e.getButton();
		if(pressedBTN == MouseEvent.BUTTON1){GlobalData.getNowTool().processEvent(e);}
		else if(pressedBTN == MouseEvent.BUTTON3) {DrawingPanelMoveAndZoom.setDragStartPoint(e.getPoint());}
		drawingPanel.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		if(pressedBTN == MouseEvent.BUTTON1){GlobalData.getNowTool().processEvent(e);}
		drawingPanel.repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		if(pressedBTN == MouseEvent.BUTTON1){
			DragAndDropManager.setComponentMasterPanel(drawingPanel);
			GlobalData.getNowTool().processEvent(e);
		} else if (pressedBTN == MouseEvent.BUTTON3) {DrawingPanelMoveAndZoom.moveCamera(e);}
		drawingPanel.repaint();
	}
	
	public void mouseEntered(MouseEvent e) {DragAndDropManager.setNowMouseOnPanel(drawingPanel);}
	public void mouseWheelMoved(MouseWheelEvent e) {DrawingPanelMoveAndZoom.zoomCamera(e);}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
