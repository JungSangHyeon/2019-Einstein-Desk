package eventListener;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

import canvasMoveAndZoom.GlobalAT;
import presentation.Presentation;
import selectGCAndGiveEvent.GCPanelGiveActionTool;
import selectGCAndGiveEvent.HandTool;
import view.DrawingPanel;
import zStuff_Data.ToolData;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GCPanel.NullPeekabooPanel;
import zStuff_GCPanel.PixelPeekabooPanel;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Text.FTextWrite_Stuff;

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
		if(FTextWrite_Stuff.isTextEditAreaFocusOwner()) {FTextWrite_Stuff.removeFocusTextEditArea();}//For Text Write Function. remove Focus
		if(rightBTNPressed()) {GlobalAT.setDragStartPoint(e.getPoint());}//For Canvas Move. set Start Point
		processEvent(e);
		offPekabooPanel();
	}

	public void mouseDragged(MouseEvent e) {
		if (rightBTNPressed()) {GlobalAT.moveCamera(e);}//Canvas Move
		processEvent(e);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		processEvent(e);
		if(!gCPanelGiveActionTool.isTakeEvent()) {GlobalAT.zoomCamera(e);}//Canvas Zoom If GCPanel Not Take Event
		if(Presentation.isOn()) {
			if (e.getWheelRotation()>0) {Presentation.nextSlide();}
			else if(e.getWheelRotation()<0){Presentation.beforeSlide();}
		}
	}
	
	public void processEvent(MouseEvent e) {
		gCPanelGiveActionTool.processEvent(e);//Give Event To GCPanel
		if(!rightBTNPressed()){if(!gCPanelGiveActionTool.isTakeEvent()) {ToolData.getNowTool().processEvent(e);}}//Give Event To Tool If GCPanel Not Take Event
		drawingPanel.repaint();
	}
	
	public void mouseMoved(MouseEvent e) {
		processEvent(e);
		if(!(ToolData.getNowTool() instanceof HandTool)) {drawingPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}
	}
	
	public void mouseClicked(MouseEvent e) {processEvent(e);}
	public void mouseReleased(MouseEvent e) {processEvent(e); pressedBTN=-1;}
	
//	private boolean leftBTNPressed() {return pressedBTN == MouseEvent.BUTTON1;}
	private boolean rightBTNPressed() {return pressedBTN == MouseEvent.BUTTON3;}
//	private boolean noBTNPressed() {return pressedBTN == MouseEvent.NOBUTTON;}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	private void offPekabooPanel() {//삭제 몬하나.
		if(!gCPanelGiveActionTool.isTakeEvent()) {
			Vector<GraphicComponent> panels = GCPanelStorage.getGCPanelVector();
			for(int i = panels.size()-1; i>-1; i--) {
				if(panels.get(i) instanceof NullPeekabooPanel|| panels.get(i) instanceof PixelPeekabooPanel) {
					GCPanelStorage.remove(panels.get(i));
				}
			}
		}
	}
}
