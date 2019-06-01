package fGlobalDataModify;

import java.awt.Color;

import data.LineData;
import zStuff_Button.AFToggleButton;

public class FSetHighLightColorBTN extends AFToggleButton{
	private static final long serialVersionUID = 5289256977245912590L;

	public FSetHighLightColorBTN(Color c, int r2) {super(c, r2);}

	@Override
	public void actionPerformed() {LineData.setHighlightColor(this.circleColor);}
}
