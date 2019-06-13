package slide;

import java.awt.event.MouseEvent;

import zStuff_Function.AFunction;

public class newSlide extends AFunction{
	private static final long serialVersionUID = -6960217079754403253L;
	
	public void mouseReleased(MouseEvent e){
		if(SlidePanel.slides.size()>0) {
			if(master.getShape().contains(e.getPoint())) {SlidePanel.newSlide();}
		}
	}
}
