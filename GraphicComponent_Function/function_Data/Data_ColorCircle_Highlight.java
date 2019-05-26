package function_Data;

import java.awt.Color;

import data.LineData;

public class Data_ColorCircle_Highlight extends Data_ColorCircle_A{
	private static final long serialVersionUID = 5289256977245912590L;

	public Data_ColorCircle_Highlight(Color c, int r2) {super(c, r2);}

	@Override
	public void setLineColor() {
		LineData.setHighlightColor(this.circleColor);
	}
}
