package shape;


import function_Stuff.ATool;
import function_Stuff.eTool;

public class HighlightShape extends pen{
	private static final long serialVersionUID = -1035475723319493551L;

	@Override
	public ATool getDrawer() {return eTool.eHighlightDrawTool.getATool();}
}
