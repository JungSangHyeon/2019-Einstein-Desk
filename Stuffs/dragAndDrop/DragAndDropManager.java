package dragAndDrop;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import function_Shape.Shape_Mover;
import function_Shape.Shape_MoverWeak;
import function_Shape.Shape_Resizer;
import function_Shape.Shape_Rotator;
import moveAndZoom.DrawingPanelMoveAndZoom;
import view.DrawingPanel;

public class DragAndDropManager {

	private static JPanel nowMouseOnPanel, componentMasterPanel;
	private static GraphicComponent draggingComponent=null;
	
	private static boolean DADOn = false;
	public static boolean isDADOn() {return DADOn;}
	public static void setDADOn(boolean dADOn) {DADOn = dADOn;}

	public static void drop() {
		if (componentMasterPanel != nowMouseOnPanel && draggingComponent != null && nowMouseOnPanel != null && componentMasterPanel != null) {
			if (nowMouseOnPanel instanceof DrawingPanel) {// AContainer -> Drawing Panel
				applyTransformToDraggingComponent();
				GCStorage.addNewGC(draggingComponent);
				draggingComponent.addFunction(new Shape_Mover());
				draggingComponent.addFunction(new Shape_Rotator());
				draggingComponent.addFunction(new Shape_Resizer());
				draggingComponent.setSelected(false);
			} else {// Drawing Panel -> AContainer
				if(GCStorage.getSelectedGCVector().size()<2) {//accept only one drag
					GCStorage.removeGC(draggingComponent);// 이건 상황에 따라 다르게 해야 겠는데.
					GCStorage.removeSelectedGC(draggingComponent);
					
//					((AContainer) nowMouseOnPanel).addItem(GCToItem(draggingComponent));
				}
			}
		}
		reset();
	}

	private static GraphicComponent GCToItem(GraphicComponent draggingComponent) {
		draggingComponent.removeFunction(new Shape_Mover());
		draggingComponent.removeFunction(new Shape_Rotator());
		draggingComponent.removeFunction(new Shape_Resizer());
		draggingComponent.addAngle(-draggingComponent.getAngle());//angle -> 0
		if(draggingComponent.getUpsideDown()) {draggingComponent.reverseUpsideDown();}
		
		draggingComponent.addFunction(new Shape_MoverWeak());
		return draggingComponent;
	}
	
	public static void reset() {
		componentMasterPanel=null;//reset
		draggingComponent=null;
		nowMouseOnPanel=null;		
	}

	private static void applyTransformToDraggingComponent() {//AContainer -> Drawing Panel에서 DP의 Affine Transform 에 맞게 크기를 조절 해줌.
		Rectangle r = draggingComponent.getShape().getBounds();
		Point masterPoint = componentMasterPanel.getLocation();
//		Point2D.Float point = DrawingPanelMoveAndZoom.transformPoint(new Point(r.x+masterPoint.x, r.y+masterPoint.y+((AContainer)componentMasterPanel).getNowDeep()));
		float scale = DrawingPanelMoveAndZoom.getScale();
//		draggingComponent.setShape(new Rectangle2D.Float(point.x, point.y, r.width/scale, r.height/scale));//넣은 도형을 꺼내게 바꾸자.
		
//		Rectangle r = draggingComponent.getShape().getBounds();
//		Point masterPoint = componentMasterPanel.getLocation();
//		Point2D.Float point = DrawingPanelMoveAndZoom.transformPoint(new Point(r.x+masterPoint.x, r.y+masterPoint.y+((AContainer)componentMasterPanel).getNowDeep()));
//		float scale = DrawingPanelMoveAndZoom.getScale();
//		draggingComponent.setShape(new Rectangle2D.Float(point.x, point.y, r.width/scale, r.height/scale));//넣은 도형을 꺼내게 바꾸자.
	}

	public static void setComponentMasterPanel(JPanel p) {if(draggingComponent!=null) {componentMasterPanel=p;}}
	public static void setDraggingComponent(GraphicComponent gc) {draggingComponent=gc;}
	public static void setNowMouseOnPanel(JPanel p) {nowMouseOnPanel=p;}
	
	public static GraphicComponent getDraggingComponent() {return draggingComponent;}
	public static JPanel getComponentMasterPanel() {return componentMasterPanel;}
	
}
