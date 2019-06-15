package fSystem;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import SAL.SaveAndLoad;
import zStuff_Function.AFunction;

public class FKillSystem extends AFunction implements Serializable{
	private static final long serialVersionUID = -2030130460706095868L;
	
	public void mouseReleased(MouseEvent e) {
		if(master.getShape().contains(e.getPoint())){
			SaveAndLoad.save(); 
			System.exit(0);
		}
	}
}
