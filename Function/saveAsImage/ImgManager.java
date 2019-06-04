package saveAsImage;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Vector;

import calculation.AffineMath;
import canvas.CanvasGC;
import canvasMoveAndZoom.DrawingPanelMoveAndZoom;
import zStuff_GraphicComponent.GraphicComponent;

public class ImgManager {
	
	public static BufferedImage getImage(Vector<GraphicComponent> gcVector) {
		//Undo Zoom & Move
		AffineTransform nowApplied = DrawingPanelMoveAndZoom.getNowApplied();
		AffineTransform back = new AffineTransform();
		back.scale(1/nowApplied.getScaleX(), 1/nowApplied.getScaleY());
		back.translate(-nowApplied.getTranslateX(), -nowApplied.getTranslateY());
		AffineMath.applyAffineToAllGC(back);
		
		//Make Image
		GraphicComponent canvas = CanvasGC.getCanvas();
		Rectangle canvasBound = canvas.getShape().getBounds();
		canvas.setBorderPaint(false);
		BufferedImage image = new BufferedImage((int)canvasBound.getWidth(), (int)canvasBound.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.translate(-canvasBound.getX(), -canvasBound.getY());
		for(GraphicComponent gc : gcVector) {gc.paint(g);}
		g.dispose();
		
		//Re do Zoom & Move
		canvas.setBorderPaint(true);
		AffineMath.applyAffineToAllGC(nowApplied);
		
		return image;
	}
}
