package tool;

import java.awt.event.MouseEvent;

import data.GCStorage;
import dragAndDrop.DragAndDropManager;
import moveAndZoom.MoveAndZoom;
import toolStuff.ATool;

public class HandTool extends ATool{//범위 선택, 그냥선택 을 통해, 무브, 리사이즈, 로테이트.
	private static final long serialVersionUID = -7463646428712999248L;//여기서 이벤트 받으면 쉐이프한테 줌.
	
//	Point dragStart; 
//	GraphicComponent masterComponent;
	boolean firstDrag=true;
	
	public void mousePressed(MouseEvent e) {//그룹 셀렉트는 벡터에 넣고 삭제하면 될듯.
		master = GCStorage.getSelectedComponent(MoveAndZoom.transformPoint(e.getPoint()));
		//앵커위에 클릭하면~
//		MoveAndZoom.transformPoint(e.getPoint())
		
		
		if(master==null) {//밖에 클릭하면~
			if (e.getButton() == MouseEvent.BUTTON1) {
				
			}else if (e.getButton() == MouseEvent.BUTTON3) {
				
			}
		}else {//shape위에 클릭하면~ 앵커위에 클릭하면~
			
		}
		basicAction(e);
	}

	public void mouseReleased(MouseEvent e) {
		basicAction(e);
		reset();
	}
	
	public void mouseDragged(MouseEvent e) {
		basicAction(e);
		
		if(firstDrag) {
			DragAndDropManager.setDraggingComponent(master);
			firstDrag = false;
		}
	}
	
//	public void mousePressed(MouseEvent e) {//네모 만드는 과정
//		GraphicComponent GCData = new GraphicComponent();
//		GCData.addPoint(e.getPoint());
//		GCData.addPoint(e.getPoint());
//		setShape(GCData);
//		GCStorage.addNewGC(GCData);
//	}
//
//	public void mouseDragged(MouseEvent e) {
//		GCStorage.getLastGC().setPoint(1, e.getPoint());
//		setShape(GCStorage.getLastGC());
//	}
//
//	private void setShape(GraphicComponent shapeData) {
//		Point p1 = shapeData.getPoints().get(0);
//		Point p2 = shapeData.getPoints().get(1);
//		shapeData.setShape(GlobalData.nowGC.getShapeMaker().newShape(p1, p2));
//	}
	
//	private void move(MouseEvent e) {
//		Point nowPoint = new Point(e.getXOnScreen(), e.getYOnScreen());
//		AffineTransform at = new AffineTransform();
//		at.translate(nowPoint.x-dragStart.x, nowPoint.y-dragStart.y);
//		master.setShape(at.createTransformedShape(master.getShape()));
//		dragStart = nowPoint;
//	}

	private void reset() {
		master = null;
		DragAndDropManager.drop();
		DragAndDropManager.reset();
		firstDrag = true;
	}
	private void basicAction(MouseEvent e) {
		if(master!=null) {master.processEvent(e);}		
	}
	public void mouseClicked(MouseEvent e) {basicAction(e);}
	public void mouseMoved(MouseEvent e) {basicAction(e);}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
