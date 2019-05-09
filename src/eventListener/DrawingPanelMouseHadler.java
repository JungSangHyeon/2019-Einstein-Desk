package eventListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import data.GlobalData;
import dragAndDrop.DragAndDropManager;
import moveAndZoom.MoveAndZoom;
import view.DrawingPanel;

public class DrawingPanelMouseHadler implements MouseListener, MouseMotionListener, MouseWheelListener{

	DrawingPanel drawingPanel;
	boolean setMasterOneTime = true;
	boolean dragOn = false;
	public DrawingPanelMouseHadler(DrawingPanel drawingPanel) {this.drawingPanel=drawingPanel;}

	public void mousePressed(MouseEvent e) {
		 if (e.getButton() == MouseEvent.BUTTON3) {
			 MoveAndZoom.setDragStartPoint(e.getPoint());
			 dragOn = true;
		}else {
			GlobalData.nowTool.mousePressed(e);
		}
		drawingPanel.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		GlobalData.nowTool.mouseReleased(e);
		dragOn = false;
	}
	
	public void mouseDragged(MouseEvent e) {
		if (dragOn) {
			MoveAndZoom.moveCamera(e);
		}else {
			GlobalData.nowTool.mouseDragged(e);
			DragAndDropManager.setComponentMasterPanel(drawingPanel);
		}
		drawingPanel.repaint();
	}
	
	public void mouseEntered(MouseEvent e) {
		DragAndDropManager.setNowMouseOnPanel(drawingPanel);
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseWheelMoved(MouseWheelEvent e) {MoveAndZoom.zoomCamera(e);}

}
