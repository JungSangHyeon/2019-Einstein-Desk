package fSystem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Vector;

import PDR_NP_Shape.HighlightShape;
import PDR_NP_Shape.pen;
import calculation.AffineMath;
import canvas.CanvasGC;
import canvasMoveAndZoom.GlobalAT;
import slide.SlidePanel;
import slidePanel.SlideOnPanel;
import toHome.ToHome;
import zStuff_Function.AFunction;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GraphicComponent.GraphicComponent;

public class FOpenFile extends AFunction implements Serializable{
	private static final long serialVersionUID = -2030130460706095868L;
	
	Vector<Vector<GraphicComponent>> slides = new Vector<Vector<GraphicComponent>>();
	
	public FOpenFile() {
		Vector<GraphicComponent> slide = new Vector<GraphicComponent>();
		slides.add(slide);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {load();}
	}

	private void load() {
		SlidePanel.setSlide(slides);
		ToHome.letsGoBack();
		GCPanelStorage.add(new SlideOnPanel());
	}
	
	public void realPaint(Graphics2D g) {
//		if (mouseOnMe) {
//			master.setBorderColor(selectColor);
//			master.setborderThick(10);
//		} else {
//			master.setBorderColor(normalColor);
//			master.setborderThick(1);
//		}
		
		g.setClip(master.getShape());
		
		Rectangle2D rect = master.getShape().getBounds2D();
//		AffineTransform at = new AffineTransform();
//		at.translate(rect.getX(), rect.getY());
		
		g.rotate(Math.toRadians(master.getAngle()), master.getCenter().getX(), master.getCenter().getY());//affine에 넣으면 안되네여
		
		if (mouseOnMe) {
			Rectangle2D brect = AffineMath.getRotateShape(master.getShape(), -master.getAngle(), master.getCenter()).getBounds2D();
			g.setColor(selectColor);
			g.setStroke(new BasicStroke(10));
			g.draw(brect);
		}
		
		makeImg(g);
//		g.draw(master.getShape());
//		g.drawImage(makeImg(g), at, null);
	}
	
	private BufferedImage makeImg(Graphics2D g2) {
//		Rectangle2D rect = master.getShape().getBounds2D();
		Rectangle2D rect = AffineMath.getRotateShape(master.getShape(), -master.getAngle(), master.getCenter()).getBounds2D();
		BufferedImage image = new BufferedImage((int)rect.getWidth(), (int)rect.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		float widthRadio = (float) (image.getWidth()/CanvasGC.getWidth());
		float heightRadio = (float) (image.getHeight()/CanvasGC.getHeight());
		AffineTransform at = new AffineTransform();
		at.setToTranslation(rect.getX(), rect.getY());
		at.scale(widthRadio, heightRadio);
		at.translate(-CanvasGC.getX(), -CanvasGC.getY());
		
		g2.transform(at);
		
		try {
			for(GraphicComponent gc : slides.get(0)) {gc.bottumPaint(g2);}
			for(GraphicComponent gc : slides.get(0)) {if(!(gc.getAShape() instanceof pen)) {gc.paint(g2);}}//shape
			for(GraphicComponent gc : slides.get(0)) {if(gc.getAShape() instanceof HighlightShape) {gc.paint(g2);}}//highlight
			for(GraphicComponent gc : slides.get(0)) {if(!(gc.getAShape() instanceof HighlightShape)&&gc.getAShape() instanceof pen) {gc.paint(g2);}}//pen
		}catch(Exception e) {}
		return image;
	}
	
	int hRadio = 15;
	boolean mouseOnMe = false;
	Color selectColor = new Color(68, 114, 196), normalColor = new Color(198,198,198);
	
	public void mouseMoved(MouseEvent e) {
		if(master.getShape().contains(GlobalAT.transformPoint(e.getPoint()))) {mouseOnMe = true;}
		else {mouseOnMe = false;}
	}
	
}
