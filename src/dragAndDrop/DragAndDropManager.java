package dragAndDrop;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import component_Stuff.GraphicComponent;
import container_Stuff.AContainer;
import data.GCStorage;
import moveAndZoom.DrawingPanelMoveAndZoom;
import view.DrawingPanel;

public class DragAndDropManager {

	private static JPanel nowMouseOnPanel, componentMasterPanel;
	private static GraphicComponent draggingComponent=null;
	
	public static void drop() {
		if (componentMasterPanel != nowMouseOnPanel && draggingComponent != null && nowMouseOnPanel != null && componentMasterPanel != null) {
			if (nowMouseOnPanel instanceof DrawingPanel) {// AContainer -> Drawing Panel
				applyTransformToDraggingComponent();
				GCStorage.addNewGC(draggingComponent);
			} else {// Drawing Panel -> AContainer
				if(GCStorage.getSelectedGCVector().size()<2) {//accept only one drag
					((AContainer) nowMouseOnPanel).addItem(draggingComponent);
					GCStorage.removeGC(draggingComponent);// 이건 상황에 따라 다르게 해야 겠는데.
					GCStorage.removeSelectedGC(draggingComponent);
				}
			}
			componentMasterPanel=null;//reset
			draggingComponent=null;
			nowMouseOnPanel=null;
		}
	}

	private static void applyTransformToDraggingComponent() {//AContainer -> Drawing Panel에서 DP의 Affine Transform 에 맞게 크기를 조절 해줌.
		Rectangle r = draggingComponent.getShape().getBounds();
		Point masterPoint = componentMasterPanel.getLocation();
		Point2D.Float point = DrawingPanelMoveAndZoom.transformPoint(new Point(r.x+masterPoint.x, r.y+masterPoint.y+((AContainer)componentMasterPanel).getNowDeep()));
		float scale = DrawingPanelMoveAndZoom.getScale();
		draggingComponent.setShape(new Rectangle2D.Float(point.x, point.y, r.width/scale, r.height/scale));//넣은 도형을 꺼내게 바꾸자.
	}

	public static void setComponentMasterPanel(JPanel p) {if(draggingComponent!=null) {componentMasterPanel=p;}}
	public static void setDraggingComponent(GraphicComponent gc) {draggingComponent=gc;}
	public static void setNowMouseOnPanel(JPanel p) {nowMouseOnPanel=p;}
	
	public static GraphicComponent getDraggingComponent() {return draggingComponent;}
	public static JPanel getComponentMasterPanel() {return componentMasterPanel;}
	
}
