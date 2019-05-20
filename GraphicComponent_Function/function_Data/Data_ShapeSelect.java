package function_Data;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import data.GlobalData;
import shape_Stuff.AShape;
import zFunction_Stuff.AFunction;

public class Data_ShapeSelect extends AFunction implements Serializable{
	private static final long serialVersionUID = 787188459680824163L;
	
	AShape shape;
	
	public Data_ShapeSelect(AShape shape) {this.shape=shape;}
	public void mouseReleased(MouseEvent e) {GlobalData.setNowShapeMaker(shape);}
	public void mouseClicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void paintComponent(Graphics2D g, Shape shape) {}
}
