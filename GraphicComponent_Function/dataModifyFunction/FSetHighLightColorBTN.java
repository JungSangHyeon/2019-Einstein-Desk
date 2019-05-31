package dataModifyFunction;

import java.awt.Color;

import button_Stuff.FToggleButton;
import data.LineData;

public class FSetHighLightColorBTN extends FToggleButton{
	private static final long serialVersionUID = 5289256977245912590L;

	public FSetHighLightColorBTN(Color c, int r2) {super(c, r2);}

	@Override
	public void actionPerformed() {LineData.setHighlightColor(this.circleColor);}
}
