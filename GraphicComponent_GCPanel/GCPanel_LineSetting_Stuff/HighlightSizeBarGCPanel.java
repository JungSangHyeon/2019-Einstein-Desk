package GCPanel_LineSetting_Stuff;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

import data.LineData;

public class HighlightSizeBarGCPanel extends SizeBarAndLine{
	private static final long serialVersionUID = -1788737516548534624L;

	public void setThick(int thick) {LineData.setHighlightThick(thick/7);}
	public Color getColor() {return LineData.getHighlightColor();}
	public Stroke getTargetStroke() {
		return new BasicStroke(LineData.getHighlightThick(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
	}
}
