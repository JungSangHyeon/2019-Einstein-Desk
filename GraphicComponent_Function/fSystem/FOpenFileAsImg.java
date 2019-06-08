package fSystem;
//package fSystem;
//
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.event.MouseEvent;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Rectangle2D;
//import java.awt.image.BufferedImage;
//import java.io.Serializable;
//import java.util.Vector;
//
//import PDR_NP_Shape.HighlightShape;
//import PDR_NP_Shape.pen;
//import SAL.ProjectManager;
//import calculation.AffineMath;
//import canvas.CanvasGC;
//import canvasMoveAndZoom.GlobalAT;
//import slide.SlidePanel;
//import slidePanel.SlideOnPanel;
//import toolPanel.ToolPanel;
//import zStuff_Function.AFunction;
//import zStuff_GCPanel.GCPanelStorage;
//import zStuff_GraphicComponent.GraphicComponent;
//import zStuff_Image.ImgStorage;
//
//public class FOpenFile extends AFunction implements Serializable{
//	private static final long serialVersionUID = -2030130460706095868L;
//	
//	Vector<Vector<GraphicComponent>> slides = new Vector<Vector<GraphicComponent>>();
//	
//	public FOpenFile() {
//		Vector<GraphicComponent> slide = new Vector<GraphicComponent>();
//		slides.add(slide);
//	}
//	
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		if(e.getClickCount()==2) {load();}
//	}
//
//	private void load() {
//		GCPanelStorage.add(new ToolPanel());
//		GCPanelStorage.add(new SlideOnPanel());
//		SlidePanel.setSlide(slides);
//		ProjectManager.loadProject();
//	}
//	
//	public void realPaint(Graphics2D g) {
//		if (mouseOnMe) {
//			master.setBorderColor(selectColor);
//			master.setborderThick(4);
//		} else {
//			master.setBorderColor(normalColor);
//			master.setborderThick(1);
//		}
//		
//		g.setClip(master.getShape());
//		
//		Rectangle2D rect = master.getShape().getBounds2D();
//		AffineTransform at = new AffineTransform();
//		at.translate(rect.getX(), rect.getY());
//		
//		makeImg();
//		
////		g.draw(master.getShape());
////		if(imgID==-1) {makeImg(g);}
//		
//		g.rotate(Math.toRadians(master.getAngle()), master.getCenter().getX(), master.getCenter().getY());//affine에 넣으면 안되네여
//		g.drawImage(ImgStorage.getImage(imgID), at, null);
//	}
//	
//	private void makeImg() {
////		Rectangle2D rect = master.getShape().getBounds2D();
//		Rectangle2D rect = AffineMath.getRotateShape(master.getShape(), -master.getAngle(), master.getCenter()).getBounds2D();
//		BufferedImage image = new BufferedImage((int)rect.getWidth(), (int)rect.getHeight(), BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g = image.createGraphics();
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//		
//		float widthRadio = (float) (image.getWidth()/CanvasGC.getWidth());
//		float heightRadio = (float) (image.getHeight()/CanvasGC.getHeight());
//		AffineTransform at = new AffineTransform();
//		at.scale(widthRadio, heightRadio);
//		at.translate(-CanvasGC.getX(), -CanvasGC.getY());
//		g.transform(at);
//		
//		try {
//			for(GraphicComponent gc : slides.get(0)) {gc.bottumPaint(g);}
//			for(GraphicComponent gc : slides.get(0)) {if(!(gc.getAShape() instanceof pen)) {gc.paint(g);}}//shape
//			for(GraphicComponent gc : slides.get(0)) {if(gc.getAShape() instanceof HighlightShape) {gc.paint(g);}}//highlight
//			for(GraphicComponent gc : slides.get(0)) {if(!(gc.getAShape() instanceof HighlightShape)&&gc.getAShape() instanceof pen) {gc.paint(g);}}//pen
//		}catch(Exception e) {}
//		
//		if(imgID==-1) {imgID = ImgStorage.addImage(image);}
//		else {ImgStorage.changeImg(imgID, image);}
//	}
//	
//	long imgID = -1;
//	int hRadio = 15;
//	boolean mouseOnMe = false;
//	Color selectColor = new Color(68, 114, 196), normalColor = new Color(198,198,198);
//	
//	public void mouseMoved(MouseEvent e) {
//		if(master.getShape().contains(GlobalAT.transformPoint(e.getPoint()))) {mouseOnMe = true;}
//		else {mouseOnMe = false;}
//	}
//	
//}
