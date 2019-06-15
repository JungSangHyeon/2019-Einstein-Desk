package fGCDataModify;

import java.awt.Color;
import java.awt.event.MouseEvent;

import zStuff_Button.AColorBtn;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class FSetShapeFillColorBTN extends AColorBtn{
	private static final long serialVersionUID = 5289256977245912590L;

	public FSetShapeFillColorBTN(Color c) {super(c);}

	@Override
	public void mouseMoved(MouseEvent e){
		if(master.getShape().contains(e.getPoint())) {
			for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
				gc.setTempFillColor(mainColor);
			}
		}else if(master.getTempFillColor()==mainColor){
			for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
				gc.setTempFillColor(null);
			}
		}
	}
	
	@Override
	public void actionPerformed() {
		for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
			gc.setFillColor(mainColor);
		}
	}
}
