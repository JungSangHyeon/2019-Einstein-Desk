package dragAndDrop;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import data.GCStorage;
import moveAndZoom.MoveAndZoom;
import stuff_Component.GraphicComponent;
import stuff_Container.AContainer;
import view.DrawingPanel;

public class DragAndDropManager {

	static JPanel nowMouseOnPanel;
	static JPanel componentMasterPanel;
	private static GraphicComponent draggingComponent=null;
	
	public static void drop() {
		if(componentMasterPanel!=nowMouseOnPanel&&draggingComponent!=null&&nowMouseOnPanel!=null&&componentMasterPanel!=null) {
			if(nowMouseOnPanel instanceof DrawingPanel) {//Drawing Panel
				Rectangle r = draggingComponent.getShape().getBounds();
				Point2D.Float point = MoveAndZoom.transformPoint(new Point(r.x+componentMasterPanel.getLocation().x, r.y+componentMasterPanel.getLocation().y+((AContainer)componentMasterPanel).getNowDeep()));
				float scale = MoveAndZoom.getScale();
				draggingComponent.setShape(new Rectangle2D.Float(point.x, point.y, r.width/scale, r.height/scale));
				GCStorage.addNewGC(draggingComponent);
			}else {
				((AContainer)nowMouseOnPanel).addItem(draggingComponent);
				GCStorage.removeGC(draggingComponent);
			}
			reset();
		}
	}

	public static void reset() {
		draggingComponent=null;
		nowMouseOnPanel=null;
		componentMasterPanel=null;
	}
	
	public static void setNowMouseOnPanel(JPanel p) {nowMouseOnPanel=p;}
	public static void setDraggingComponent(GraphicComponent gc) {draggingComponent=gc;}
	public static void setComponentMasterPanel(JPanel p) {if(draggingComponent!=null) {componentMasterPanel=p;}}
	
	public static GraphicComponent getDraggingComponent() {return draggingComponent;}
	public static JPanel getComponentMasterPanel() {return componentMasterPanel;}
	
}
