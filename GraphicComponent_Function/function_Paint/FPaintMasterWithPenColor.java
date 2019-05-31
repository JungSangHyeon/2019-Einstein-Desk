package function_Paint;

import java.awt.Graphics2D;

import data.LineData;
import zFunction_Stuff.AFunction;

public class FPaintMasterWithPenColor extends AFunction{
	private static final long serialVersionUID = 5046938002818853280L;

	public void realPaint(Graphics2D g) {
		g.setColor(LineData.getPenColor());
		g.fill(master.getShape());
	}
}
