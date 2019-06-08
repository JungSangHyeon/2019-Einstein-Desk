package zStuff_Data;

import java.awt.Color;

import canvasMoveAndZoom.GlobalAT;
import onOff.ThickFollowScale;

public class LineData {

	//Pen
	public static Color penColor = Color.BLACK;
	public static Color getPenColor() {return penColor;}
	public static void setPenColor(Color pc) {penColor = pc;}
	
	public static float penThick = 10;
	public static float getPenThick() {
		float thick;
		if (ThickFollowScale.isOn()) {thick = penThick/GlobalAT.getZoom();}//need to change
		else {thick = penThick;}
		if(thick==0) {thick=1;}
		return thick;
	}
	public static void setPenThick(int pt) {penThick = pt;}
	
	//HighLighter
	public static Color highlightColor = Color.YELLOW;
	public static Color getHighlightColor() {return highlightColor;}
	public static void setHighlightColor(Color hc) {highlightColor = hc;}
	
	public static int highlightThick = 10;
	public static int getHighlightThick() {
		int thick;
		if (ThickFollowScale.isOn()) {thick = highlightThick;}//need to change
		else {thick = highlightThick;}
		if(thick==0) {thick=1;}
		return thick;
	}
	public static void setHighlightThick(int ht) {highlightThick = ht;}
	
}
