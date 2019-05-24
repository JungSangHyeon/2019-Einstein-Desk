package function_Paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import zFunction_Stuff.AFunction;

public class Paint_ConnectLine extends AFunction{
	private static final long serialVersionUID = -8223239223784355678L;
	
	GraphicComponent connectTarget;
	int thick = 10;
	
	public Paint_ConnectLine(GraphicComponent endGC) {
		connectTarget = endGC;
		buttomPaint = true;
	}

	public void paintComponent(Graphics2D g, Shape shape) {
		if(GCStorage.have(connectTarget)) {
			Stroke startStroke = g.getStroke();
			g.setStroke(new BasicStroke(thick));
			g.setColor(Color.BLACK);
			
			Point2D.Float startCenter = master.getCenter();
			Point2D.Float endCenter = connectTarget.getCenter();
			
			Shape line = new Line2D.Double(startCenter.getX(), startCenter.getY(), endCenter.getX(), endCenter.getY());
			g.draw(line);
			
			g.setStroke(startStroke);
		}
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
