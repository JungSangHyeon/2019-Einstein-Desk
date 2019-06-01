package zStuff_GCPanel_LineSetting;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

import zStuff_Data.LineData;

public class PenSizeBarGCPanel extends SizeBarAndLine{
	private static final long serialVersionUID = -1788737516548534624L;

	public void setThick(int thick) {LineData.setPenThick(thick/10);}
	public Color getColor() {return LineData.getPenColor();}
	public Stroke getTargetStroke() {
		return new BasicStroke(LineData.getPenThick(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	}
}
