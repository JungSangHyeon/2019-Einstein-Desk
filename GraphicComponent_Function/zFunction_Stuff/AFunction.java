package zFunction_Stuff;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import component_Stuff.GraphicComponent;

public abstract class AFunction  implements Serializable{
	private static final long serialVersionUID = -1736900760929688622L;
	
	protected GraphicComponent master;
	public void setMaster(GraphicComponent graphicComponent) {this.master=graphicComponent;}
	
//	boolean paintOn = true;
//	public abstract boolean isPaintOn();
//	public abstract void setPaintOn(boolean boo);
//	
//	boolean actionOn = true;
//	public abstract boolean isActionOn();
//	public abstract void setActionOn(boolean boo);
	
	protected boolean topPaint = false;
	public boolean isTopPaint() {return topPaint;}
	
	protected boolean buttomPaint = false;
	public boolean isButtomPaint() {return buttomPaint;}
//	public abstract void setTopPaint(boolean boo);
	
//	protected boolean topAction = false;//���߿� ���̺�� �����ϰ� ����
//	public boolean isTopAction() {return topAction;}
//	public abstract void setTopAction(boolean boo);
	
	public void processEvent(MouseEvent e) {//�׳� processEvent�� ������ ����.
		int eID = e.getID();
		if(eID == MouseEvent.MOUSE_PRESSED) {this.mousePressed(e);}
		else if(eID == MouseEvent.MOUSE_RELEASED) {this.mouseReleased(e);}
		else if(eID == MouseEvent.MOUSE_CLICKED) {this.mouseClicked(e);}
		else if(eID == MouseEvent.MOUSE_DRAGGED) {this.mouseDragged(e);}
		else if(eID == MouseEvent.MOUSE_MOVED) {this.mouseMoved(e);}
		else if(eID == MouseEvent.MOUSE_ENTERED) {this.mouseEntered(e);}
		else if(eID == MouseEvent.MOUSE_EXITED) {this.mouseExited(e);}
	}
	
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseClicked(MouseEvent e);
	public abstract void mouseDragged(MouseEvent e);
	public abstract void mouseMoved(MouseEvent e);
	public abstract void mouseEntered(MouseEvent e);
	public abstract void mouseExited(MouseEvent e);
	
	public abstract void paintComponent(Graphics2D g, Shape shape);

	public abstract void processSelectEvent(boolean selected);
	
	public abstract void timeIsMove(boolean boo);
}
