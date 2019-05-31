package dataModifyFunction;

import java.awt.Color;

import button_Stuff.FToggleButton;
import data.LineData;

public class FSetPenColorBTN extends FToggleButton{
	private static final long serialVersionUID = 5289256977245912590L;

	public FSetPenColorBTN(Color c, int r2) {super(c, r2);}

	@Override
	public void actionPerformed() {LineData.setPenColor(this.circleColor);}
}
