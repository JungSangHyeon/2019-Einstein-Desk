package fGCDataModify;

import java.awt.Color;

import zStuff_Button.ColorBtn;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class FSetShapeFillColorBTN extends ColorBtn{
	private static final long serialVersionUID = 5289256977245912590L;

	public FSetShapeFillColorBTN(Color c) {super(c);}

	@Override
	public void actionPerformed() {
		for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
			gc.setFillColor(mainColor);
		}
	}
}
