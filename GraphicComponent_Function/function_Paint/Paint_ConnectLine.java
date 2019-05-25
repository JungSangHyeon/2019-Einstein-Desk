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
import data.GlobalData;
import function_Stuff.eTool;
import global.Calculator;
import moveAndZoom.DrawingPanelMoveAndZoom;
import zFunction_Stuff.AFunction;

public class Paint_ConnectLine extends AFunction{
	private static final long serialVersionUID = -8223239223784355678L;
	
	GraphicComponent connectTarget;
	int thick = 10;
	
	Line2D.Double line;
	
	public Paint_ConnectLine(GraphicComponent endGC) {
		connectTarget = endGC;
		buttomPaint = true;
	}

	public void paintComponent(Graphics2D g, Shape shape) {
		if(GCStorage.have(connectTarget)) {
			Stroke startStroke = g.getStroke();
			g.setStroke(new BasicStroke(thick));
			g.setColor(new Color(255, 192, 0));
			
			Point2D.Float startCenter = master.getCenter();
			Point2D.Float endCenter = connectTarget.getCenter();
			
//			startPartLine = new Line2D.Double(startCenter.getX(), startCenter.getY(), endCenter.getX(), startCenter.getY());
//			g.draw(startPartLine);
			
			line = new Line2D.Double(startCenter.getX(), startCenter.getY(), endCenter.getX(), endCenter.getY());
			g.draw(line);
			
//			endPartLine = new Line2D.Double(endCenter.getX(), startCenter.getY(), endCenter.getX(), endCenter.getY());
//			g.draw(endPartLine);
					
			g.setStroke(startStroke);
		}
	}
	
	
	public void mousePressed(MouseEvent e) {if(isTimeToDie(e)){suicide();}}
	public void mouseDragged(MouseEvent e) {if(isTimeToDie(e)){suicide();}System.out.println(123);}
	
	private boolean isTimeToDie(MouseEvent e) {
		if(GlobalData.getNowTool().equals(eTool.eEraserTool.getATool())) {
			Point2D nowPoint = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());
			if(Calculator.distanceLineNPoint(getStartPointOf(line), getEndPointOf(line), nowPoint)<thick/2) {return true;}
		}
		return false;
	}

	private void suicide() {
		master.removeFunction(this);
	}

	private Point2D getStartPointOf(Line2D line) {return new Point2D.Double(line.getX1(), line.getY1());}
	private Point2D getEndPointOf(Line2D line) {return new Point2D.Double(line.getX2(), line.getY2());}
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
