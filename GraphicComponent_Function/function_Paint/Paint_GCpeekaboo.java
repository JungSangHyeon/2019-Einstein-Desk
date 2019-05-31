package function_Paint;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import component_Stuff.GraphicComponent;
import data.GCPanelStorage;
import zFunction_Stuff.AFunction;

public class Paint_GCpeekaboo extends AFunction{
	private static final long serialVersionUID = 4299512141606163429L;
	
	GraphicComponent  gc;
	boolean doubleSelected = false;
	
	public Paint_GCpeekaboo(GraphicComponent  gc) {this.gc = gc; }
	
	public void mousePressed(MouseEvent e) {
		for(GraphicComponent gc : master.getAssociateGCs()) {gc.setSelected(false);}
		master.setSelected(true);
		doubleSelected = (!doubleSelected);
	}
	
	public void processSelectEvent(boolean selected) {
		if(selected&&doubleSelected) {GCPanelStorage.remove(gc); GCPanelStorage.add(gc); }
		else {GCPanelStorage.remove(gc); doubleSelected = false;}
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
