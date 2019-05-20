package function_System;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import moveAndZoom.DrawingPanelMoveAndZoom;
import zFunction_Stuff.AFunction;

public class System_Die extends AFunction implements Serializable{
	private static final long serialVersionUID = -2030130460706095868L;
	
	public void mouseReleased(MouseEvent e) {// SAL Ãß°¡!
		if(master.getShape().contains(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))){
			System.exit(0);
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void paintComponent(Graphics2D g, Shape shape) {}
}
