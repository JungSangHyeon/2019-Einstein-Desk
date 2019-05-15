package penSettingPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import data.GlobalData;
import function_Stuff.AFunction;

@SuppressWarnings("serial")
public class ColorCircle extends AFunction{//pixel - int circleWH = 52;

	int r2 = 0;
	int normalr2 = 36;
	int selectedr2 = 32; int selectedBorderr2 = 40;
	int mouseOnr2 = 40; int mouseOnBorderr2 = 48;
	int boarderGap = 4;
	
	Color circleColor, borderColor = Color.BLACK, gapColor = new Color(242,242,242);
	
	Ellipse2D.Float circle;
	
	boolean mouseInCircle = false;
	boolean Selected = false;
	boolean mousePressInCircle = false;
	
	int target;
	public ColorCircle(Color c, int target) {
		circleColor=c;
		r2 = normalr2;
		this.target=target;
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(gapColor);
		g2.fillRect(getX(), getY(), getWidth(), getHeight());
		if(!Selected) {
			if(mousePressInCircle) {
				fillCircle(g2, borderColor, mouseOnBorderr2);
				fillCircle(g2, gapColor, mouseOnBorderr2-boarderGap);
			}
			fillCircle(g2, circleColor, r2);
		}else {
			fillCircle(g2, borderColor, selectedBorderr2);
			fillCircle(g2, gapColor, selectedBorderr2-boarderGap);
			fillCircle(g2, circleColor, selectedr2);
		}
	}

	private void fillCircle(Graphics2D g2, Color color, int r2) {
		g2.setColor(color);
		circle = new Ellipse2D.Float(getX()+getWidth()/2-r2/2, getY()+getHeight()/2-r2/2, r2, r2);
		g2.fill(circle);
	}
	
	public void mousePressed(MouseEvent e) {if(mouseInCircle) {mousePressInCircle = true;}}
	public void mouseDragged(MouseEvent e) {checker(e);}
	public void mouseMoved(MouseEvent e) {
		if(circle.contains(e.getPoint())) {
			if(r2==mouseOnr2) {r2=normalr2;}
		}
		checker(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		if(circle.contains(e.getPoint())) {
			if(mouseInCircle&&mousePressInCircle) {
				Selected = true;
				setLineColor();
			}
			mousePressInCircle = false;
		}else {
			Selected = false;
		}
	}
	
	public void checker(MouseEvent e) {
		if(circle.contains(e.getPoint())) {
			mouseInCircle = true;
			r2=mouseOnr2;
		}else {
			mousePressInCircle = false;
			mouseInCircle = false;
			r2=normalr2;
		}
	}
	
	public void setLineColor() {
		if(target==0) {
			GlobalData.setPenColor(circleColor);
		}else if(target==1) {
			GlobalData.setHighlightColor(circleColor);
		}
	}
	
	private int getX() {return master.getShape().getBounds().x;}
	private int getY() {return master.getShape().getBounds().y;}
	private int getWidth() {return master.getShape().getBounds().width;}
	private int getHeight() {return master.getShape().getBounds().height;}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
