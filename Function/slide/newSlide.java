package slide;

import java.awt.event.MouseEvent;

import zStuff_Function.AFunction;

public class newSlide extends AFunction{
	private static final long serialVersionUID = -6960217079754403253L;

	public void mousePressed(MouseEvent e){
		System.out.println("new Slide");
		if(master.getShape().contains(e.getPoint())) {SlideManager.newSlide();}
	}
}
