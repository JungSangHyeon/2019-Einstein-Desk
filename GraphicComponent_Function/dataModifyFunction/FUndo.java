package dataModifyFunction;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import doUndo.RedoUndo;
import zFunction_Stuff.AFunction;

public class FUndo extends AFunction implements Serializable{
	private static final long serialVersionUID = -6743524881365403749L;
	
	@Override
	public void mousePressed(MouseEvent e) {RedoUndo.undo();}
}
