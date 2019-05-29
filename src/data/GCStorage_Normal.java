package data;

import java.util.Vector;

import component_Stuff.GraphicComponent;

public class GCStorage_Normal {
	
	private static Vector<GraphicComponent> GCVector = new Vector<GraphicComponent>();
	
	//Add
	public static void addNewGC(GraphicComponent shapeData) {GCVector.add(shapeData);}
	public static void addAllToGC(Vector<GraphicComponent> vector) {GCVector.addAll(vector);}
	
	//Remove
	public static void removeGC(GraphicComponent gc) {GCVector.remove(gc);}
	public static void removeLastGC() {GCVector.remove(GCVector.size()-1);}
	public static void clearGC() {GCVector.clear();}
	
	//Get
	public static Vector<GraphicComponent> getGCVector() {return GCVector;}
	public static GraphicComponent getLastGC() {return GCVector.lastElement();}
	public static GraphicComponent getGC(int i) {return GCVector.get(i);}
	
	//Ask?
	public static boolean have(GraphicComponent gc) {return GCVector.contains(gc);}
	
	private static Vector<GraphicComponent> killGCVector = new Vector<GraphicComponent>();
	public static void killGCAtNextPaint(GraphicComponent target) {killGCVector.add(target);}
	public static void killTargets() {
		for(GraphicComponent target : killGCVector) {
			GCVector.remove(target);
		}
	}
}
