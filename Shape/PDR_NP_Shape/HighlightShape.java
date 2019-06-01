package PDR_NP_Shape;


import zStuff_Tool.ATool;
import zStuff_Tool.eTool;

public class HighlightShape extends pen{
	private static final long serialVersionUID = -1035475723319493551L;

	@Override
	public ATool getDrawer() {return eTool.eHighlightDrawTool.getATool();}
}
