package fGlobalDataModify;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import redoUndo.RedoUndo;
import zStuff_Function.AFunction;

public class FRedo extends AFunction implements Serializable{
	private static final long serialVersionUID = -6743524881365403749L;
	
	@Override
	public void mousePressed(MouseEvent e) {RedoUndo.redo();}
}
