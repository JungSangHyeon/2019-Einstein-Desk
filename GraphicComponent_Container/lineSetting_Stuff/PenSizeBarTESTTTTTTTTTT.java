package lineSetting_Stuff;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

import data.GlobalData;

public class PenSizeBarTESTTTTTTTTTT extends SizeBarAndLineTesttttttt{
	private static final long serialVersionUID = -1788737516548534624L;

	public void setThick(int thick) {
		GlobalData.setHighlightThick(thick/7);
	}

	public Stroke getTargetStroke() {
		return new BasicStroke(GlobalData.getHighlightThick(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
	}

	public Color getColor() {
		return GlobalData.getHighlightColor();
	}

}
