package eventListener;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import data.ToolData;
import function_Paint.Paint_TextWrite_Stuff;
import function_SelectAndEvent.GCPanelGiveActionTool;
import function_SelectAndEvent.HandTool;
import moveAndZoom.DrawingPanelMoveAndZoom;
import view.DrawingPanel;

public class DrawingPanelMouseHadler implements MouseListener, MouseMotionListener, MouseWheelListener{//manage all event except AContainer

	GCPanelGiveActionTool gCPanelGiveActionTool;
	DrawingPanel drawingPanel;//Master Panel
	int pressedBTN;
	
	public DrawingPanelMouseHadler(DrawingPanel drawingPanel) {
		this.drawingPanel=drawingPanel;
		gCPanelGiveActionTool = new GCPanelGiveActionTool();
	}

	public void mousePressed(MouseEvent e) {
		pressedBTN = e.getButton();
		if(Paint_TextWrite_Stuff.isTextEditAreaFocusOwner()) {Paint_TextWrite_Stuff.removeFocusTextEditArea();}//For Text Write Function. remove Focus
		if(rightBTNPressed()) {DrawingPanelMoveAndZoom.setDragStartPoint(e.getPoint());}//For Canvas Move. set Start Point
		processEvent(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (rightBTNPressed()) {DrawingPanelMoveAndZoom.moveCamera(e);}//Canvas Move
		processEvent(e);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		processEvent(e);
		if(!gCPanelGiveActionTool.isTakeEvent()) {DrawingPanelMoveAndZoom.zoomCamera(e);}//Canvas Zoom If GCPanel Not Take Event
	}
	
	public void processEvent(MouseEvent e) {
		gCPanelGiveActionTool.processEvent(e);//Give Event To GCPanel
		if(!rightBTNPressed()){if(!gCPanelGiveActionTool.isTakeEvent()) {ToolData.getNowTool().processEvent(e);}}//Give Event To Tool If GCPanel Not Take Event
//		drawingPanel.repaint();
	}
	
	public void mouseMoved(MouseEvent e) {
		processEvent(e);
		if(!(ToolData.getNowTool() instanceof HandTool)) {drawingPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}
	}
	
	public void mouseClicked(MouseEvent e) {processEvent(e);}
	public void mouseReleased(MouseEvent e) {processEvent(e);}
	
//	private boolean leftBTNPressed() {return pressedBTN == MouseEvent.BUTTON1;}
	private boolean rightBTNPressed() {return pressedBTN == MouseEvent.BUTTON3;}
//	private boolean noBTNPressed() {return pressedBTN == MouseEvent.NOBUTTON;}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
