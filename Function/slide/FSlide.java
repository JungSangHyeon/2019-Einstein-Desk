package slide;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import zStuff_Function.AFunction;
import zStuff_GraphicComponent.GraphicComponent;

public class FSlide extends AFunction{
	private static final long serialVersionUID = -6960217079754403253L;

	int slideNum = -1;
	
	public FSlide(int slideNum) {
		this.slideNum=slideNum;
	}
	
	public void realPaint(Graphics2D g) {
		//Show Normal
		Rectangle rect = master.getShape().getBounds();
		g.drawString(slideNum+"", (int)rect.getX(), (int)rect.getY());
		g.fill(master.getShape());
		
		g.setClip(master.getShape());
		for(GraphicComponent gc : SlideManager.getSlide(slideNum)) {
			gc.paint(g);
		}
		g.setClip(null);
	}
	
	public void mouseReleased(MouseEvent e){
		if(master.getShape().contains(e.getPoint())) {
			SlideManager.loadSlide(slideNum);
		}
	}
	
}
