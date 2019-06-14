package zStuff_GCPanel_LayoutPixel;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import deepClone.DeepClone;
import zStuff_GraphicComponent.GraphicComponent;

public class Item  implements Serializable{
	private static final long serialVersionUID = 6702642196441912004L;
	
	boolean bornInContainer = true;
	GraphicComponent realGC, inContainerGC;
	Pixel ownPT;
	
	public Item(GraphicComponent gc) {
		this.realGC= gc;
		this.inContainerGC=(GraphicComponent) DeepClone.clone(gc);
	}
	
	public void setOwnPixel(Pixel pt) {ownPT = pt;}
	public Pixel getOwnPixel() {return ownPT;}
	public void processEvent(MouseEvent e) {inContainerGC.processEvent(e);}
	public GraphicComponent getInContainerGC() {return inContainerGC;}
	public Shape getShape() {return inContainerGC.getShape();}
	public GraphicComponent getRealGC() {return realGC;}
	
	public void paint(Graphics2D g2) {
		inContainerGC.setFillPaint(false);
		inContainerGC.paint(g2);
		
		if(realGC!=null&&realGC.getShape()!=null){
			Rectangle2D real = realGC.getShape().getBounds2D();
			Rectangle2D inContainer = inContainerGC.getShape().getBounds2D();
			
			AffineTransform at = new AffineTransform();
			double radio = Math.min(inContainer.getWidth()/real.getWidth(), inContainer.getHeight()/real.getHeight());
			at.translate(- real.getX(), - real.getY());
			at.translate(- real.getWidth()/2 + inContainer.getWidth()/2, - real.getHeight()/2 + inContainer.getHeight()/2);
			at.translate(inContainer.getX(), inContainer.getY());
//			at.setToTranslation(inContainer.getCenterX(), inContainer.getCenterY());
//			at.setToTranslation(inContainer.getX(), inContainer.getY());
//			at.scale(radio, radio);
//			Rectangle2D temp = at.createTransformedShape(real).getBounds2D();
//			at.translate(-(temp.getX() - inContainer.getX()), -(temp.getY() - inContainer.getY()));
//			at.translate(inContainer.getX() - real.getX(), inContainer.getY() - real.getY());
//			at.setToTranslation(inContainer.getX(), inContainer.getY());
			g2.setTransform(at);
			
			realGC.paint(g2);
			g2.setTransform(new AffineTransform());
			
			
//			AffineTransform at = new AffineTransform();//정배율 가운데
//			at.translate(- real.getX(), - real.getY());
//			at.translate(- real.getWidth()/2 + inContainer.getWidth()/2, - real.getHeight()/2 + inContainer.getHeight()/2);
//			at.translate(inContainer.getX(), inContainer.getY());
			
		}
	}
	
	public void setShape(Shape shape) {inContainerGC.setShape(shape);}
}
