package function_System;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import zFunction_Stuff.AFunction;

public class FKillSystem extends AFunction implements Serializable{
	private static final long serialVersionUID = -2030130460706095868L;
	
	public void mouseReleased(MouseEvent e) {// SAL Ãß°¡!
		if(master.getShape().contains(e.getPoint())){System.exit(0);}
	}
}
