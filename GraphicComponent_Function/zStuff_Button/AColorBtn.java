package zStuff_Button;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import zStuff_Function.AFunction;

public abstract class AColorBtn extends AFunction{
	private static final long serialVersionUID = 5289256977245912590L;

	protected Color mainColor;
	public AColorBtn(Color c) {mainColor = c;}

	public void mouseReleased(MouseEvent e){
		if(master.getShape().contains(e.getPoint())) {actionPerformed();}
	}
	
	public void realPaint(Graphics2D g) {
		g.setColor(mainColor);
		g.fill(master.getShape());
	}
	
	public abstract void actionPerformed();
}
