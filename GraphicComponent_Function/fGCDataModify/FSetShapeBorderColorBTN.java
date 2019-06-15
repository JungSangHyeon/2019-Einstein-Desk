package fGCDataModify;

import java.awt.Color;
import java.awt.event.MouseEvent;

import zStuff_Button.AColorBtn;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class FSetShapeBorderColorBTN extends AColorBtn{
	private static final long serialVersionUID = 5289256977245912590L;

	public FSetShapeBorderColorBTN(Color c) {super(c);}

	@Override
	public void mouseMoved(MouseEvent e){
		if(master.getShape().contains(e.getPoint())) {
			for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
				gc.setTempBorderColor(mainColor);
			}
		}else if(master.getTempBorderColor()==mainColor){
			for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
				gc.setTempBorderColor(null);
			}
		}
	}
	
	@Override
	public void actionPerformed() {
		for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
			gc.setBorderColor(mainColor);
		}
	}
}
