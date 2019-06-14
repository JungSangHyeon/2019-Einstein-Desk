package slide;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import PDR_NP_Shape.HighlightShape;
import PDR_NP_Shape.pen;
import canvas.CanvasGC;
import zStuff_Function.AFunction;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Image.ImgStorage;

public class FSlide extends AFunction{
	private static final long serialVersionUID = -6960217079754403253L;

	long imageID = -1;
	
	public void loadSlide() {
		SlidePanel.loadSlide(slideNum);
	}
	int slideNum = -1;
	public int getSlideNum() {return slideNum;}
	public FSlide(int i) {slideNum = i;}
	
	public boolean isNowSlide() {
		return GCStorage_Normal.getGCVector()==SlidePanel.getSlide(slideNum);
	}
	
	int hRadio = 15;
	boolean mouseOnMe = false;
	Color selectColor = new Color(68, 114, 196), normalColor = new Color(198,198,198);
	
	
	public void mouseMoved(MouseEvent e) {
		if(master.getShape().contains(e.getPoint())) {mouseOnMe = true;}
		else {mouseOnMe = false;}
	}
	
	public void realPaint(Graphics2D g) {
		if(GCStorage_Normal.getGCVector()==SlidePanel.getSlide(slideNum)) {
			master.setBorderColor(selectColor);
			if(mouseOnMe) {master.setborderThick(4);}
			else {master.setborderThick(3);}
		}else {
			master.setBorderColor(normalColor);
			if(mouseOnMe) {master.setborderThick(2);}
			else {master.setborderThick(1);}
		}
		
		if(isNowSlide()||imageID==-1) {makeImg();}
		Rectangle2D rect = master.getShape().getBounds2D();
		AffineTransform at = new AffineTransform();
		at.translate(rect.getX(), rect.getY());
		
		g.drawImage(ImgStorage.getImage(imageID), at, null);
		g.draw(master.getShape());
	}
	
	private void makeImg() {
		Rectangle2D rect = master.getShape().getBounds2D();
		
		BufferedImage image = new BufferedImage((int)rect.getWidth(), (int)rect.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		float widthRadio = (float) (image.getWidth()/CanvasGC.getWidth());
		float heightRadio = (float) (image.getHeight()/CanvasGC.getHeight());
		AffineTransform at = new AffineTransform();
		at.scale(widthRadio, heightRadio);
		at.translate(-CanvasGC.getX(), -CanvasGC.getY());
		g.transform(at);
		
		CanvasGC.paint(g);
		for(GraphicComponent gc : SlidePanel.getSlide(slideNum)) {gc.bottumPaint(g);}
		for(GraphicComponent gc : SlidePanel.getSlide(slideNum)) {if(!(gc.getAShape() instanceof pen)) {gc.paint(g);}}//shape
		for(GraphicComponent gc : SlidePanel.getSlide(slideNum)) {if(gc.getAShape() instanceof HighlightShape) {gc.paint(g);}}//highlight
		for(GraphicComponent gc : SlidePanel.getSlide(slideNum)) {if(!(gc.getAShape() instanceof HighlightShape)&&gc.getAShape() instanceof pen) {gc.paint(g);}}//pen
		
		if(imageID!=-1) {ImgStorage.changeImg(imageID, image);}
		else {imageID = ImgStorage.addImage(image);}
	}
	
	Point pressPoint;
	public void mousePressed(MouseEvent e){
		pressPoint = e.getPoint();
	}
	public void mouseReleased(MouseEvent e){
		if(pressPoint!=null&&pressPoint.distance(e.getPoint())<5) {
			if(master.getShape().contains(e.getPoint())) {loadSlide();}
		}
	}
}
