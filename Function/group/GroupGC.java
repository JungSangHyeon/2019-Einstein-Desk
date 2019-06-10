package group;

import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import canvasMoveAndZoom.GlobalAT;
import fComposite.FInCanvasGCBasicFunction;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Shape.eShape;

public class GroupGC extends GraphicComponent{
	private static final long serialVersionUID = 4167132028348324648L;
	
	public GroupGC(Vector<GraphicComponent> groupMembers) {
		this.addAllAggregateGC(groupMembers);
		this.setBorderPaint(false);
		this.setFillPaint(false);
		
		this.addFunction(new FInCanvasGCBasicFunction());
		this.setAShape(eShape.group.getAShape());
		
		//size
		Path2D path = new Path2D.Float();
		for (GraphicComponent gc : this.getAllAggregateGCs()) {path.append(gc.getShape(), false);}
		Area compound = new Area(path);
		Rectangle2D bound = compound.getBounds2D();
		Point2D.Float lup = new Point2D.Float((float)bound.getX(), (float)bound.getY());
		Point2D.Float rdp = new Point2D.Float((float)bound.getMaxX(), (float)bound.getMaxY());
		this.addPoint(lup);
		this.addPoint(rdp);
		this.setShape(bound);
		
		//center
		this.setCenter(new Point2D.Float((float)bound.getCenterX(), (float)bound.getCenterY()));
	}
	
	@Override
	public void processEvent(MouseEvent e) {
		super.processEvent(e);
		if(e.getID() == MouseEvent.MOUSE_CLICKED) {
			for(GraphicComponent aggreGC : this.getMyAggregateGCs()) {
				if(aggreGC.getShape().contains(GlobalAT.transformPoint(e.getPoint()))) {
					aggreGC.processEvent(e);
				}
			}
		}
	}
	
}
