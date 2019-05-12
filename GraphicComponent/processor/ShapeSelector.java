package processor;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import data.GlobalData;
import processor_Stuff.AMouseActionProcessor;
import shape_Stuff.AShape;

public class ShapeSelector extends AMouseActionProcessor implements Serializable{
	private static final long serialVersionUID = 787188459680824163L;
	
	AShape shape;
	
	public ShapeSelector(AShape shape) {this.shape=shape;}
	public void mouseReleased(MouseEvent e) {GlobalData.setNowShapeMaker(shape);}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
}
