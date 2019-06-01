package data;

import java.util.Vector;

import zStuff_GraphicComponent.GraphicComponent;

public class GCStorage_Selected {
	
	private static Vector<GraphicComponent> selectedComponents = new Vector <GraphicComponent>();
	
	//Add
	public static void addSelectedGC(GraphicComponent shapeData) {shapeData.setSelected(true);selectedComponents.add(shapeData);}
	public static void addAllSelectedGC(Vector<GraphicComponent> shapeDatas) {
		for(GraphicComponent gc : shapeDatas) {
			gc.setSelected(true);
			selectedComponents.add(gc);
		}
	}
	
	//Remove
	public static void removeSelectedGC(GraphicComponent gc) {gc.setSelected(false);selectedComponents.remove(gc);}
	public static void clearSelected() {
		for(GraphicComponent gc : selectedComponents) {gc.setSelected(false);}
		selectedComponents.clear();
	}
	
	//Get
	public static Vector<GraphicComponent> getSelectedGCVector() {return selectedComponents;}
	public static boolean have(GraphicComponent master) {
		return selectedComponents.contains(master);
	}
}
