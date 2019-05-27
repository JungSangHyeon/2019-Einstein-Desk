package function_Paint;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import component_Stuff.GraphicComponent;
import data.GCPanelStorage;
import zFunction_Stuff.AFunction;

public class Paint_GCShowUp extends AFunction{
	private static final long serialVersionUID = 4299512141606163429L;
	
	GraphicComponent  gc;
	boolean show = false;
	
	public Paint_GCShowUp(GraphicComponent  gc) {this.gc = gc;}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		if(show) {gc.paint(g);}
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		show = (!show);
		if(!master.getShape().contains(e.getPoint())) {show=false; GCPanelStorage.remove(gc);}
		if(show) {GCPanelStorage.remove(gc); GCPanelStorage.add(gc);}
		else {GCPanelStorage.remove(gc);}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
