package fGlobalDataModify;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import data.ToolData;
import function_Stuff.ATool;
import zStuff_Function.AFunction;

public class FSetTool extends AFunction implements Serializable{
	private static final long serialVersionUID = -6743524881365403749L;
	
	ATool tool;
	public FSetTool(ATool tool) {this.tool=tool;}
	
	@Override
	public void mousePressed(MouseEvent e) {ToolData.setNowTool(tool);}
}
