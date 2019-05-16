package shape;


import tool_Stuff.ATool;
import tool_Stuff.eTool;

public class HighlightShape extends pen{
	private static final long serialVersionUID = -1035475723319493551L;

	@Override
	public ATool getDrawer() {return eTool.eHighlightDrawTool.getTool();}
}
