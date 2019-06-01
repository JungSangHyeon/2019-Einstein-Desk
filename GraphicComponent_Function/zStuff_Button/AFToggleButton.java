package zStuff_Button;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import zStuff_Function.AFunction;

@SuppressWarnings("serial")
public abstract class AFToggleButton extends AFunction{

	Ellipse2D.Double circle;
	double boarderGap, circleDiameter = 0;
	double normalCircleDiameter, selectedCircleDiameter, mouseOnCircleDiameter;
	double selectedBorderDiameter, mouseOnBorderDiameter;
	boolean mouseInCircle = false, Selected = false, mousePressInCircle = false;
	protected Color circleColor;
	Color borderColor = Color.BLACK;
	Color backGroundColor = new Color(242,242,242);
	
	public AFToggleButton(Color circleColor, int diameter) {
		this.circleColor=circleColor;
		this.circleDiameter = 9*diameter/13;
		this.normalCircleDiameter = 9*diameter/13;
		this.selectedCircleDiameter = 8*diameter/13;
		this.selectedBorderDiameter = 10*diameter/13;
		this.mouseOnCircleDiameter = 10*diameter/13;
		this.mouseOnBorderDiameter = 12*diameter/13;
		this.boarderGap = diameter/13;
	}
	
	@Override
	public void realPaint(Graphics2D g) {
		g.setColor(backGroundColor);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		if(!Selected) {
			if(mousePressInCircle) {
				fillCircle(g, borderColor, mouseOnBorderDiameter);
				fillCircle(g, backGroundColor, mouseOnBorderDiameter-boarderGap);
			}
			fillCircle(g, circleColor, circleDiameter);
		}else {
			fillCircle(g, borderColor, selectedBorderDiameter);
			fillCircle(g, backGroundColor, selectedBorderDiameter-boarderGap);
			fillCircle(g, circleColor, selectedCircleDiameter);
		}
	}

	private void fillCircle(Graphics2D g2, Color color, double diameter) {
		g2.setColor(color);
		circle = new Ellipse2D.Double(getX()+getWidth()/2-diameter/2, getY()+getHeight()/2-diameter/2, diameter, diameter);
		g2.fill(circle);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {if(mouseInCircle) {mousePressInCircle = true;}}
	
	@Override
	public void mouseDragged(MouseEvent e) {checker(e);}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(circle.contains(e.getPoint())) {if(circleDiameter==mouseOnCircleDiameter) {circleDiameter=normalCircleDiameter;}}
		checker(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(circle.contains(e.getPoint())) {
			if(mouseInCircle&&mousePressInCircle) {
				Selected = true;
				actionPerformed();
			}
			mousePressInCircle = false;
		}else {Selected = false;}
	}
	
	public void checker(MouseEvent e) {
		if(circle.contains(e.getPoint())) {
			mouseInCircle = true;
			circleDiameter=mouseOnCircleDiameter;
		}else {
			mousePressInCircle = false;
			mouseInCircle = false;
			circleDiameter=normalCircleDiameter;
		}
	}
	
	public abstract void actionPerformed() ;
	private int getX() {return master.getShape().getBounds().x;}
	private int getY() {return master.getShape().getBounds().y;}
	private int getWidth() {return master.getShape().getBounds().width;}
	private int getHeight() {return master.getShape().getBounds().height;}
}
