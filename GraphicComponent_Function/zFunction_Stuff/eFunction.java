package zFunction_Stuff;

import function_Data.Data_ShapeSelect;

public enum eFunction {
	
//	eData_ShapeSelect(new Data_ShapeSelect()),
	;
	
	private AFunction function;
	private eFunction(AFunction function) {this.function=function;}
	public AFunction getFunction() {return this.function;}
}
