package data;

import java.util.Vector;

import component_Stuff.GraphicComponent;

public class GCStorage {

	//GC
	private static Vector<GraphicComponent> GCVector = new Vector<GraphicComponent>();
	
	public static void addNewGC(GraphicComponent shapeData) {GCVector.add(shapeData);}
	public static void removeGC(GraphicComponent gc) {GCVector.remove(gc);}
	public static void removeLastGC() {GCVector.remove(GCVector.size()-1);}
	public static boolean have(GraphicComponent gc) {return GCVector.contains(gc);}
	public static GraphicComponent getLastGC() {return GCVector.lastElement();}
	public static GraphicComponent getGC(int i) {return GCVector.get(i);}
	public static Vector<GraphicComponent> getGCVector() {return GCVector;}
	
	//Selected GC
	private static Vector<GraphicComponent> selectedComponents = new Vector <GraphicComponent>();
	
	public static void addSelectedGC(GraphicComponent shapeData) {selectedComponents.add(shapeData);}
	public static void removeSelectedGC(GraphicComponent gc) {selectedComponents.remove(gc);}
	public static void clearSelected() {selectedComponents.clear();}
	public static boolean isSelected(GraphicComponent gc) {return selectedComponents.contains(gc);}
	public static Vector<GraphicComponent> getSelectedGCVector() {return selectedComponents;}
	
}
