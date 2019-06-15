package copyAndPaste;


import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;

import deepClone.DeepClone;
import redoUndo.RedoUndo;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class CopyAndPaste {

	static Vector<GraphicComponent> copyTemp = new Vector<GraphicComponent>();
	static int copyInterval = 10;
	
	public static void copy() {
		copyTemp.clear();
		for (GraphicComponent c : GCStorage_Selected.getSelectedGCVector()) {copyTemp.add(c);}
	}
	
	public static void cut() {
		copyTemp.clear();
		for (GraphicComponent c : GCStorage_Selected.getSelectedGCVector()) {copyTemp.add(c);}
		for (GraphicComponent c : GCStorage_Selected.getSelectedGCVector()) {
			GCStorage_Normal.removeGC(c);
		}
		GCStorage_Selected.clearSelected();
	}

	public static void paste() {//Move And Zoom을 그래픽 -> 데이터 로 바꿔서, 여기 몰림.
		GCStorage_Selected.clearSelected();
		
		Vector<GraphicComponent> temp = new Vector<GraphicComponent>();
		for (GraphicComponent c : copyTemp) {
			float interval = copyInterval;
			
			boolean restartTime = false;
			if(c.isTimeMoving()) {c.moveTime(false); restartTime = true;}
			GraphicComponent copyItem = (GraphicComponent)DeepClone.clone(c);
			copyItem.setSelected(false);
			if(restartTime) {c.moveTime(true);}
			
			AffineTransform at = new AffineTransform();
			at.translate(interval, interval);
			
			for(Point2D.Float point : copyItem.getPoints()) {point.setLocation(point.x+interval, point.y+interval);}
			copyItem.setCenter(new Point2D.Float(copyItem.getCenter().x+interval, copyItem.getCenter().y+interval));
			copyItem.setShape(at.createTransformedShape(copyItem.getShape()));
			
			for(GraphicComponent gc : copyItem.getAllAggregateGCs()) {
				for(Point2D.Float point : gc.getPoints()) {point.setLocation(point.x+interval, point.y+interval);}
				gc.setCenter(new Point2D.Float(gc.getCenter().x+interval, gc.getCenter().y+interval));
				gc.setShape(at.createTransformedShape(gc.getShape()));
			}
			
			GCStorage_Normal.addNewGC(copyItem);
			GCStorage_Selected.addSelectedGC(copyItem);
			copyItem.setSelected(true);
			temp.add(copyItem);
		}
		copyTemp.clear();
		copyTemp.addAll(temp);
		
		RedoUndo.saveNowInHistory();
	}

}
