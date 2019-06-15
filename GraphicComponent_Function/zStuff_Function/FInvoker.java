package zStuff_Function;

import copyAndPaste.CopyAndPaste;
import group.GroupTool;
import zOrder.Z_Order;
import zOrder.Z_Order.SendTo;

public class FInvoker {
	
	public enum reservedMethod{COPY, PASTE, TOP, HELL, FRONT, BACK, GROUP, UNGROUP,}
	
	public static void invoke(reservedMethod id) {
		switch(id) {
		case COPY : CopyAndPaste.copy(); break;
		case PASTE : CopyAndPaste.paste(); break;
		case TOP : Z_Order.changeZOrder(SendTo.Top); break;
		case HELL : Z_Order.changeZOrder(SendTo.Hell); break;
		case FRONT : Z_Order.changeZOrder(SendTo.oneUP); break;
		case BACK : Z_Order.changeZOrder(SendTo.oneDown); break;
		case GROUP : GroupTool.grouping(); break;
		case UNGROUP : GroupTool.unGrouping(); break;
		}
	}
}
