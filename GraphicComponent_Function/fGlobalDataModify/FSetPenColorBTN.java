package fGlobalDataModify;

import java.awt.Color;

import zStuff_Button.AFToggleButton;
import zStuff_Data.LineData;

public class FSetPenColorBTN extends AFToggleButton{
	private static final long serialVersionUID = 5289256977245912590L;

	public FSetPenColorBTN(Color c, int r2) {super(c, r2, eMode.ELLIPSE);}

	@Override
	public void actionPerformed() {LineData.setPenColor(this.btnColor);}
}
