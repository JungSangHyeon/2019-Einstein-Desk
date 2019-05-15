package penSettingPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

import data.GlobalData;

@SuppressWarnings("serial")
public class SizeBar extends JPanel{

	boolean mouseIn=false;
	
	RoundRectangle2D.Float handle;
	GeneralPath line;
	
	int roundRectF = 5;
	int hadleLocation = 80; //0~312 = 0~30
	int handleW = 8;
	int handleH = 24;
	int handleY = 78;
	
	int barH = 2;
	int barW = 300;
	int barStartX = 7;
	
	int changeFactor = 0;
	int penFactor = 10;
	int highlightFactor = 7;
	
	Color handleC;
	Color basicHandleC = new Color(0, 183, 195);
	Color mouseOnHandleC = Color.BLACK;
	Color pressedHandleC = new Color(204, 204, 204);
	
	Color leftBarC = new Color(247, 99, 12);
	Color rightBarC = new Color(145, 145, 145);
	
	String target = "";
	
	int lineX = 3;
	int liney = 25;
	
	public SizeBar(String target) {
		this.target=target;
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseMotionHandler());
		handleC = basicHandleC;
		
		line = new GeneralPath();
		line.moveTo(30+lineX,27+liney);
		line.curveTo(110+lineX, -33+liney, 190+lineX, 69+liney, 270+lineX, 5+liney);
		
		if(target.equals("pen")) {changeFactor=penFactor;}
		else if(target.equals("highlight")) {changeFactor=highlightFactor;}
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(242,242,242));
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		if(target.equals("pen")) {g2.setColor(GlobalData.getPenColor());g2.setStroke(new BasicStroke(GlobalData.getPenThick(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));}
		else if(target.equals("highlight")) {g2.setColor(GlobalData.getHighlightColor());g2.setStroke(new BasicStroke(GlobalData.getHighlightThick(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));}
		g2.draw(line);
		
		g2.setColor(rightBarC);
		g2.fillRect(barStartX, handleY+handleH/2-barH/2, barW, barH);
		
		g2.setColor(leftBarC);
		g2.fillRect(barStartX, handleY+handleH/2-barH/2, hadleLocation-handleW/2, barH);
		
		g2.setColor(handleC);
		handle = new  RoundRectangle2D.Float(hadleLocation,handleY,handleW,handleH,roundRectF,roundRectF);
		g2.fill(handle);
	}
	
	class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {if(mouseIn) {mouseDragStart = e.getPoint();}}
		public void mouseReleased(MouseEvent e) {
			handleC = basicHandleC;repaint();
		}
	}
	
	static Point mouseDragStart;
	public class MouseMotionHandler implements  MouseMotionListener{
		public void mouseDragged(MouseEvent e) {
			if(mouseIn&&hadleLocation>0&&hadleLocation<barW) {
				handleC = pressedHandleC;
				hadleLocation += e.getPoint().x - mouseDragStart.x;
				if(hadleLocation<=0) {hadleLocation=1;}
				if(hadleLocation>=barW) {hadleLocation=barW-1;}
				mouseDragStart = e.getPoint();
				if(target.equals("pen")) {GlobalData.setPenThick(hadleLocation/changeFactor);}
				else if(target.equals("highlight")) {GlobalData.setHighlightThick(hadleLocation/changeFactor);}
			}
			repaint();
		}
		public void mouseMoved(MouseEvent e) {
			if(handle.contains(e.getPoint())) {
				mouseIn=true;
				handleC = mouseOnHandleC;
			}else {
				mouseIn=false;
				handleC = basicHandleC;
			}
			repaint();
		}
	}
}
