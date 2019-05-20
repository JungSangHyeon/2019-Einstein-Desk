package function_Data;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import data.GlobalData;
import function_Stuff.ATool;
import zFunction_Stuff.AFunction;

public class Data_ToolSelector extends AFunction implements Serializable{
	private static final long serialVersionUID = -6743524881365403749L;
	
	ATool tool;
	
	public Data_ToolSelector(ATool tool) {this.tool=tool;}
	public void mouseReleased(MouseEvent e) {GlobalData.setNowTool(tool);}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void paintComponent(Graphics2D g, Shape shape) {}
}
