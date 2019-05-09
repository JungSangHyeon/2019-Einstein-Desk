package toolStuff;

import tool.Make2PointShapeTool;

public class ToolEnum {

	public enum eTool{
		rect(new Make2PointShapeTool()), 
		;
		
		ATool tool;
		private eTool(ATool tool) {this.tool = tool;}
		public ATool getShapeMaker() {return tool;}
	}
}
