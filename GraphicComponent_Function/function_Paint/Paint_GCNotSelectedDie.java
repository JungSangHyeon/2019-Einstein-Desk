package function_Paint;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import component_Stuff.GraphicComponent;
import data.GCPanelStorage;
import zFunction_Stuff.AFunction;

public class Paint_GCNotSelectedDie extends AFunction{
	private static final long serialVersionUID = 4299512141606163429L;
	
	public void mousePressed(MouseEvent e) {}
	
	public void processSelectEvent(boolean selected) {
		System.out.println("diediedie");
		if(!selected){GCPanelStorage.remove(master);}
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void timeIsMove(boolean boo) {}
}
