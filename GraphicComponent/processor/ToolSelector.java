package processor;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import data.GlobalData;
import processor_Stuff.AMouseActionProcessor;
import tool_Stuff.ATool;

public class ToolSelector extends AMouseActionProcessor implements Serializable{
	private static final long serialVersionUID = -6743524881365403749L;
	
	ATool tool;
	
	public ToolSelector(ATool tool) {this.tool=tool;}
	public void mouseReleased(MouseEvent e) {GlobalData.setNowTool(tool);}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
}
