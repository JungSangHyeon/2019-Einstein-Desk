package tool;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import data.GCStorage;
import data.GlobalData;
import moveAndZoom.MoveAndZoom;
import painter.ImgPainter;
import processor.Mover;
import stuff_Component.GraphicComponent;
import toolStuff.ATool;

public class Make2PointShapeTool extends ATool{
	private static final long serialVersionUID = 5000619643824217376L;
	
	public void mousePressed(MouseEvent e) {
		GraphicComponent GCData = new GraphicComponent();
//		GCData.addPainter(new ImgPainter("", "Icons/jake_22X22.txt"));
		GCData.addPoint(MoveAndZoom.transformPoint(e.getPoint()));
		GCData.addPoint(MoveAndZoom.transformPoint(e.getPoint()));
		GCData.addProcessor(new Mover());
		setShape(GCData);
		GCStorage.addNewGC(GCData);
	}

	public void mouseDragged(MouseEvent e) {
		GCStorage.getLastGC().setPoint(1, MoveAndZoom.transformPoint(e.getPoint()));
		setShape(GCStorage.getLastGC());
	}

	private void setShape(GraphicComponent shapeData) {
		Point2D.Float p1 = shapeData.getPoints().get(0);
		Point2D.Float p2 = shapeData.getPoints().get(1);
		shapeData.setShape(GlobalData.nowGC.getShapeMaker().newShape(p1, p2));
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
