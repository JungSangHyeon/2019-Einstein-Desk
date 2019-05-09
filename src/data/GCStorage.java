package data;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Vector;

import stuff_Component.GraphicComponent;

public class GCStorage {//drawing panel에 표시되는 것들

	private static Vector<GraphicComponent> GCVector = new Vector<GraphicComponent>();

	public static void addNewGC(GraphicComponent shapeData) {
		GCVector.add(shapeData);
	}
	
	public static Iterator<GraphicComponent> getGCVectorIterator() {return GCVector.iterator();}
	public static GraphicComponent getGC(int i) {return GCVector.get(i);}
	public static GraphicComponent getLastGC() {return GCVector.lastElement();}
	public static void removeLastGC() {GCVector.remove(GCVector.size()-1);}
	public static void removeGC(GraphicComponent gc) {GCVector.remove(gc);}
	
	public static GraphicComponent getSelectedComponent(Point2D.Float p) {
		for(int i=GCVector.size()-1; i>-1; i--) {
			if(GCVector.get(i).getShape().contains(p)) {
				return GCVector.get(i);
			}
		}
		return null;
	}
	public static boolean have(GraphicComponent gc) {
		return GCVector.contains(gc);
	}
}
