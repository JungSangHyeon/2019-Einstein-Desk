package copyAndPaste;


import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import deepClone.DeepClone;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class CopyAndPaste {

	static Vector<GraphicComponent> copyTemp = new Vector<GraphicComponent>();
	static int copyInterval = 10;
	
	public static void copy() {
		copyTemp.clear();
		for (GraphicComponent c : GCStorage.getSelectedGCVector()) {
			GraphicComponent copyItem = (GraphicComponent)DeepClone.clone(c);
			copyItem.setSelected(false);
			copyTemp.add(copyItem);
		}
	}

	public static void paste() {
		GCStorage.clearSelected();
		for (GraphicComponent c : copyTemp) {
			float interval = copyInterval/ DrawingPanelMoveAndZoom.getScale();
			
			AffineTransform at = new AffineTransform();
			at.translate(interval, interval);
			
			for(Point2D.Float point : c.getPoints()) {point.setLocation(point.x+interval, point.y+interval);}
			c.setCenter(new Point2D.Float(c.getCenter().x+interval, c.getCenter().y+interval));
			c.setShape(at.createTransformedShape(c.getShape()));
			
			GraphicComponent copyItem = (GraphicComponent)DeepClone.clone(c);
			GCStorage.addNewGC(copyItem);
			GCStorage.addSelectedGC(copyItem);
			copyItem.setSelected(true);
		}
	}

}
