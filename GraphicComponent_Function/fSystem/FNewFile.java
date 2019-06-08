package fSystem;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import SAL.ProjectManager;
import zStuff_Function.AFunction;

public class FNewFile extends AFunction implements Serializable{
	private static final long serialVersionUID = -2030130460706095868L;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {
			ProjectManager.newFile();
		}
	}
}
