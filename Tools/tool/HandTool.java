package tool;

import java.awt.event.MouseEvent;

import data.GCStorage;
import dragAndDrop.DragAndDropManager;
import moveAndZoom.MoveAndZoom;
import toolStuff.ATool;

public class HandTool extends ATool{//���� ����, �׳ɼ��� �� ����, ����, ��������, ������Ʈ.
	private static final long serialVersionUID = -7463646428712999248L;//���⼭ �̺�Ʈ ������ ���������� ��.
	
//	Point dragStart; 
//	GraphicComponent masterComponent;
	boolean firstDrag=true;
	
	public void mousePressed(MouseEvent e) {//�׷� ����Ʈ�� ���Ϳ� �ְ� �����ϸ� �ɵ�.
		master = GCStorage.getSelectedComponent(MoveAndZoom.transformPoint(e.getPoint()));
		//��Ŀ���� Ŭ���ϸ�~
//		MoveAndZoom.transformPoint(e.getPoint())
		
		
		if(master==null) {//�ۿ� Ŭ���ϸ�~
			if (e.getButton() == MouseEvent.BUTTON1) {
				
			}else if (e.getButton() == MouseEvent.BUTTON3) {
				
			}
		}else {//shape���� Ŭ���ϸ�~ ��Ŀ���� Ŭ���ϸ�~
			
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
	
//	public void mousePressed(MouseEvent e) {//�׸� ����� ����
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
