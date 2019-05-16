package lineSetting_Stuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class SizeBarAndLine extends JPanel{

	GeneralPath line;
	boolean mouseIn=false;
	RoundRectangle2D.Float handle;
	
	int roundRectF = 5;
	int barH = 2, barW = 300, barStartX = 7;
	int hadleLocation = 80, handleW = 8, handleH = 24, handleY = 78;
	
	Color handleC, basicHandleC = new Color(0, 183, 195), mouseOnHandleC = Color.BLACK, pressedHandleC = new Color(204, 204, 204);
	Color leftBarC = new Color(247, 99, 12), rightBarC = new Color(145, 145, 145);
	
	public SizeBarAndLine() {
		this.setSize(312,110);
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseMotionHandler());
		handleC = basicHandleC;
		
		int lineX = 3, lineY = 25;//¾ß´Â ³ÀµÓ½Ã´Ù
		line = new GeneralPath();
		line.moveTo(30+lineX,27+lineY);
		line.curveTo(110+lineX, -33+lineY, 190+lineX, 69+lineY, 270+lineX, 5+lineY);
	}
	
	public void paintImg(Graphics2D img_g) {
		img_g.drawImage(myImg, this.getX(), this.getY(), null);
	}
	
	Image myImg;
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		myImg = this.createImage(this.getWidth(),this.getHeight());
		Graphics2D img_g = (Graphics2D)myImg.getGraphics();
		img_g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		img_g.setColor(new Color(242,242,242));  img_g.fillRect(0, 0, getWidth(), getHeight());
		img_g.setColor(getColor()); img_g.setStroke(getTargetStroke()); img_g.draw(line);
		img_g.setColor(rightBarC); img_g.fillRect(barStartX, handleY+handleH/2-barH/2, barW, barH);
		img_g.setColor(leftBarC); img_g.fillRect(barStartX, handleY+handleH/2-barH/2, hadleLocation-handleW/2, barH);
		handle = new  RoundRectangle2D.Float(hadleLocation,handleY,handleW,handleH,roundRectF,roundRectF);
		img_g.setColor(handleC); img_g.fill(handle);
		g2.drawImage(myImg, 0,0, null);
	}
	
	public Image getImage() {return myImg;}
	
	public abstract Color getColor();
	public abstract Stroke getTargetStroke();
	public abstract void setThick(int hadleLocation);
	
	class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {if(mouseIn) {mouseDragStart = e.getPoint();}}
		public void mouseReleased(MouseEvent e) {handleC = basicHandleC; repaint();}
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
				setThick(hadleLocation);
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
