package fSystem;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import SAL.ProjectManager;
import zStuff_Function.AFunction;

public class FNewProject extends AFunction implements Serializable{
	private static final long serialVersionUID = 8136311921467721370L;

	public void mouseReleased(MouseEvent e){
		if(master.getShape().contains(e.getPoint())) {
			ProjectManager.newFile();
		}
	}
	
}
