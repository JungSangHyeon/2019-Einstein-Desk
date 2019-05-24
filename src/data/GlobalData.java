package data;

import java.awt.Color;

import function_SelectAndEvent.HandTool;
import function_Stuff.ATool;
import moveAndZoom.DrawingPanelMoveAndZoom;
import onOff.ThickFollowScale;
import shape_Stuff.AShape;
import shape_Stuff.eShape;

public class GlobalData {

	//Tool
	private static ATool nowTool = new HandTool();
	public static ATool getNowTool() {return nowTool;}
	public static void setNowTool(ATool nowTool) {GlobalData.nowTool = nowTool;}
	
	//Shape
	private static AShape nowShapeMaker = eShape.rect.getAShape();//btn������ ������ ��ư��� ��������, ������ ���� ���� ���߳�.
	public static AShape getNowShapeMaker() {return nowShapeMaker;}
	public static void setNowShapeMaker(AShape shapeMaker) {GlobalData.nowShapeMaker = shapeMaker;}

	//Pen
	public static Color penColor = Color.BLACK;
	public static Color getPenColor() {return penColor;}
	public static void setPenColor(Color pc) {penColor = pc;}
	
	public static int penThick = 10;
	public static int getPenThick() {
		if (ThickFollowScale.isOn()) {return (int) (penThick/DrawingPanelMoveAndZoom.getScale());}
		else {return penThick;}
	}
	public static void setPenThick(int pt) {penThick = pt;}
	
	//HighLighter
	public static Color highlightColor = Color.YELLOW;
	public static Color getHighlightColor() {return highlightColor;}
	public static void setHighlightColor(Color hc) {highlightColor = hc;}
	
	public static int highlightThick = 10;
	public static int getHighlightThick() {
		if (ThickFollowScale.isOn()) {return (int) (highlightThick/DrawingPanelMoveAndZoom.getScale());}
		else {return highlightThick;}
	}
	public static void setHighlightThick(int ht) {highlightThick = ht;}
	
}
