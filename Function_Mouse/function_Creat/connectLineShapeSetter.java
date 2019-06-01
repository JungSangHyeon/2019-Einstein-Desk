package function_Creat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import data.GCStorage_Normal;
import zStuff_Function.AFunction;
import zStuff_Function.AFunction.PaintZOrder;
import zStuff_GraphicComponent.GraphicComponent;

public class connectLineShapeSetter extends AFunction {
	private static final long serialVersionUID = 3933314511832627932L;

	GraphicComponent startGC, endGC;
	int thick = 10;
	
	public connectLineShapeSetter(GraphicComponent startGC, GraphicComponent endGC) {
//		this.buttomPaint = true;
		this.setPaintOrder(PaintZOrder.BOTTOM);
		this.startGC=startGC;
		this.endGC=endGC;
	}

	public void realPaint(Graphics2D g) {
		if(GCStorage_Normal.have(startGC)&&GCStorage_Normal.have(endGC)) {
			Stroke startStroke = g.getStroke();
			g.setStroke(new BasicStroke(thick, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.setColor(new Color(255, 192, 0));
			
			Point2D.Float startCenter = startGC.getCenter();
			Point2D.Float endCenter = endGC.getCenter();
			
			Line2D.Double line = new Line2D.Double(startCenter.getX(), startCenter.getY(), endCenter.getX(), endCenter.getY());
			
			g.draw(line);
			master.getPoints().get(0).setLocation(startCenter.getX(), startCenter.getY());
			master.getPoints().get(1).setLocation(endCenter.getX(), endCenter.getY());
			master.setCenter(new Point2D.Float((float)(startCenter.getX()+endCenter.getX())/2, (float)(startCenter.getY()+endCenter.getY())/2));
			
			master.setShape(line);
					
			g.setStroke(startStroke);
		}else {
			GCStorage_Normal.killGCAtNextPaint(master);
		}
	}
	
	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void processSelectEvent(boolean selected) {}

	public void timeIsMove(boolean boo) {}
}
