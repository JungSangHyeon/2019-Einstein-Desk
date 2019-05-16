package lineSetting_Stuff;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

import data.GlobalData;

public class PenSizeBar extends SizeBarAndLine{
	private static final long serialVersionUID = -1788737516548534624L;

	public void setThick(int thick) {
		GlobalData.setPenThick(thick/10);
	}

	public Stroke getTargetStroke() {
		return new BasicStroke(GlobalData.getPenThick(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	}

	public Color getColor() {
		return GlobalData.getPenColor();
	}

}
