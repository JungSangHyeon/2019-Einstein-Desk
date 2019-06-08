package fTime;

import java.awt.geom.AffineTransform;
import java.io.Serializable;

import calculation.AffineMath;
import canvas.CanvasGC;
import canvasMoveAndZoom.GlobalAT;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Time.ATimeFunction;

public class FGravity extends ATimeFunction implements Serializable{
	private static final long serialVersionUID = 3260227949051233755L;

	int speed = 10;
	
	@Override
	public void timeAction() {
		double move = 0;
		double maxY = master.getShape().getBounds().getMaxY();
		double canvasMaxY = CanvasGC.getY()+CanvasGC.getHeight();
		if(maxY<canvasMaxY) {
			GraphicComponent interGC = interceptSomeThing();
			if(interGC==null) {
				move = speed*GlobalAT.getZoom();
			}
			}
		else {move = canvasMaxY - maxY;}
		AffineTransform at = new AffineTransform();
		at.translate(0, move);
		AffineMath.applyAffineTransformToGC(at, master);
		for(GraphicComponent aggreGC : master.getAllAggregateGCs()) {
			AffineMath.applyAffineTransformToGC(at, aggreGC);
		}
	}

	private GraphicComponent interceptSomeThing() {
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {
			if(gc!=master&&gc.getShape().intersects(master.getShape().getBounds())) {
				return gc;
			}
		}
		return null;
	}
	
}
