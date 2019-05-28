package function_Data;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import doUndo.RedoUndo;
import zFunction_Stuff.AFunction;

public class Data_Redo extends AFunction implements Serializable{
	private static final long serialVersionUID = -6743524881365403749L;
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {RedoUndo.redo();}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void paintComponent(Graphics2D g, Shape shape) {}
	public void processSelectEvent(boolean selected) {}
}
