package function_Data;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import zFunction_Stuff.AFunction;

@SuppressWarnings("serial")
public abstract class Data_ColorCircle_A extends AFunction{

	Ellipse2D.Double circle;
	double boarderGap, R2 = 0;
	double normalR2, selectedR2, mouseOnR2;
	double selectedBorderR2, mouseOnBorderR2;
	boolean mouseInCircle = false, Selected = false, mousePressInCircle = false;
	Color circleColor, borderColor = Color.BLACK, backGroundColor = new Color(242,242,242);
	
	public Data_ColorCircle_A(Color c, int givenR2) {
		circleColor=c;
		R2 = 9*givenR2/13;
		normalR2 = 9*givenR2/13;
		selectedR2 = 8*givenR2/13;
		selectedBorderR2 = 10*givenR2/13;
		mouseOnR2 = 10*givenR2/13;
		mouseOnBorderR2 = 12*givenR2/13;
		boarderGap = givenR2/13;
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(backGroundColor);
		g2.fillRect(getX(), getY(), getWidth(), getHeight());
		
		if(!Selected) {
			if(mousePressInCircle) {
				fillCircle(g2, borderColor, mouseOnBorderR2);
				fillCircle(g2, backGroundColor, mouseOnBorderR2-boarderGap);
			}
			fillCircle(g2, circleColor, R2);
		}else {
			fillCircle(g2, borderColor, selectedBorderR2);
			fillCircle(g2, backGroundColor, selectedBorderR2-boarderGap);
			fillCircle(g2, circleColor, selectedR2);
		}
	}

	private void fillCircle(Graphics2D g2, Color color, double r2) {
		g2.setColor(color);
		circle = new Ellipse2D.Double(getX()+getWidth()/2-r2/2, getY()+getHeight()/2-r2/2, r2, r2);
		g2.fill(circle);
	}
	
	public void mousePressed(MouseEvent e) {if(mouseInCircle) {mousePressInCircle = true;}}
	public void mouseDragged(MouseEvent e) {checker(e);}
	public void mouseMoved(MouseEvent e) {
		if(circle.contains(e.getPoint())) {if(R2==mouseOnR2) {R2=normalR2;}}
		checker(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		if(circle.contains(e.getPoint())) {
			if(mouseInCircle&&mousePressInCircle) {
				Selected = true;
				setLineColor();
			}
			mousePressInCircle = false;
		}else {Selected = false;}
	}
	
	public void checker(MouseEvent e) {
		if(circle.contains(e.getPoint())) {
			mouseInCircle = true;
			R2=mouseOnR2;
		}else {
			mousePressInCircle = false;
			mouseInCircle = false;
			R2=normalR2;
		}
	}
	
	public abstract void setLineColor() ;
	
	private int getX() {return master.getShape().getBounds().x;}
	private int getY() {return master.getShape().getBounds().y;}
	private int getWidth() {return master.getShape().getBounds().width;}
	private int getHeight() {return master.getShape().getBounds().height;}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void processSelectEvent(boolean selected) {}
}
