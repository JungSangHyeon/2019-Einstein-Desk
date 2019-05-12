package dragAndDrop;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JPanel;

import data.GCStorage;
import moveAndZoom.DrawingPanelMoveAndZoom;
import stuff_Component.GraphicComponent;
import stuff_Container.AContainer;
import view.DrawingPanel;

public class DragAndDropManager {

	static JPanel nowMouseOnPanel;
	static JPanel componentMasterPanel;
	private static GraphicComponent draggingComponent=null;
//	private static Vector<GraphicComponent> draggingComponents=new Vector<GraphicComponent>();
	
	public static void drop() {
		if (componentMasterPanel != nowMouseOnPanel && draggingComponent != null && nowMouseOnPanel != null && componentMasterPanel != null) {
			if (nowMouseOnPanel instanceof DrawingPanel) {// Dragged To Drawing Panel
				applyTransformToDraggingComponent();
				GCStorage.addNewGC(draggingComponent);
			} else {// Dragged To AContainer
				((AContainer) nowMouseOnPanel).addItem(draggingComponent);
				GCStorage.removeGC(draggingComponent);// 이건 상황에 따라 다르게 해야 겠는데.
			}
			reset();
		}
	}

	private static void applyTransformToDraggingComponent() {
		Rectangle r = draggingComponent.getShape().getBounds();
		Point masterPoint = componentMasterPanel.getLocation();
		Point2D.Float point = DrawingPanelMoveAndZoom.transformPoint(new Point(r.x+masterPoint.x, r.y+masterPoint.y+((AContainer)componentMasterPanel).getNowDeep()));
		float scale = DrawingPanelMoveAndZoom.getScale();
		draggingComponent.setShape(new Rectangle2D.Float(point.x, point.y, r.width/scale, r.height/scale));
	}

	public static void reset() {
		componentMasterPanel=null;
		draggingComponent=null;
		nowMouseOnPanel=null;
	}
	
	public static void setComponentMasterPanel(JPanel p) {if(draggingComponent!=null) {componentMasterPanel=p;}}
	public static void setDraggingComponent(GraphicComponent gc) {draggingComponent=gc;}
	public static void setNowMouseOnPanel(JPanel p) {nowMouseOnPanel=p;}
	
	public static GraphicComponent getDraggingComponent() {return draggingComponent;}
	public static JPanel getComponentMasterPanel() {return componentMasterPanel;}
	
}
