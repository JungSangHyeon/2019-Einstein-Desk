package toHome;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.Vector;

import calculation.AffineMath;
import canvas.CanvasGC;
import canvasMoveAndZoom.DrawingPanelMoveAndZoom;
import slide.SlidePanel;
import zStuff_GraphicComponent.GraphicComponent;

public class ToHome {

	static AffineTransform theWayWeCome = new AffineTransform();
	public static void applyAffine(AffineTransform at) {theWayWeCome.preConcatenate(at);}
	
	public static void letsGoBack() {
		AffineTransform theWayToHome = null;
		try {theWayToHome = theWayWeCome.createInverse();}
		catch (NoninvertibleTransformException e) {e.printStackTrace();}
		AffineMath.applyAffineTransformToGC(theWayToHome, CanvasGC.getCanvas());
		for(Vector<GraphicComponent> slide : SlidePanel.getSlides()) {
			for(GraphicComponent gc : slide) {
				AffineMath.applyAffineTransformToGC(theWayToHome, gc);
				gc.setborderThick(gc.getBorderThick()*(float)(theWayToHome.getScaleX()));
				for(GraphicComponent aggreGC : gc.getAllAggregateGCs()) {
					AffineMath.applyAffineTransformToGC(theWayToHome, aggreGC);
					aggreGC.setborderThick(aggreGC.getBorderThick()*(float)(theWayToHome.getScaleX()));
				}
			}
		}	
		theWayWeCome = new AffineTransform();
		DrawingPanelMoveAndZoom.clear();
		SlidePanel.removeAT();
	}
}
