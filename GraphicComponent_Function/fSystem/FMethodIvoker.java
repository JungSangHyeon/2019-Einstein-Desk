package fSystem;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import zStuff_Function.AFunction;
import zStuff_Function.FInvoker;
import zStuff_Function.FInvoker.reservedMethod;

public class FMethodIvoker extends AFunction implements Serializable{
	private static final long serialVersionUID = 8136311921467721370L;

	reservedMethod invokeId;
	public FMethodIvoker(reservedMethod invokeId) {this.invokeId=invokeId;}
	public void mouseReleased(MouseEvent e){
		if(master.getShape().contains(e.getPoint())) {
			FInvoker.invoke(invokeId);
		}
	}
	
}
