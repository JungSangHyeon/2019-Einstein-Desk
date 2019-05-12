package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import component_Stuff.GraphicComponent;
import container.ToolSelectContainer;
import container_Stuff.AContainer;
import data.GCStorage;
import deepClone.DeepClone;
import dragAndDrop.DragAndDropManager;
import eventListener.DrawingPanelMouseHadler;
import global.InjectEnums.eColor;
import moveAndZoom.DrawingPanelMoveAndZoom;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel{
	
	ToolSelectContainer toolBar, toolBar2;
	
	public DrawingPanel() {
		this.setBackground(eColor.DrawingPanelBackGroundColor.getVal());
		
		DrawingPanelMouseHadler mouseHandler = new DrawingPanelMouseHadler(this);
		this.addMouseMotionListener(mouseHandler);
		this.addMouseWheelListener(mouseHandler);
		this.addMouseListener(mouseHandler);
		
		this.addComponentListener(new componentHandler());
		
		this.setLayout(null);

		toolBar = new ToolSelectContainer();
		toolBar.setLocation(100, 100);//Test
		toolBar.addMaster(this);
		this.add(toolBar);
		
		toolBar2 = new ToolSelectContainer();
		toolBar2.addMaster(this);
		this.add(toolBar2);
		
		ArrangeComponentLocation();
	}
	
	public void initialize() {}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		allGCPaint(g2d);
		aContainerPaint(g2d);
		AContainerDraggingComponentPaint(g2d);
		DrawingPanelDraggingComponentPaint(g2d);
	}
	
	public class componentHandler implements ComponentListener{
		public void componentResized(ComponentEvent e) {ArrangeComponentLocation();}
		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
	}
	
	public void ArrangeComponentLocation() {
//		toolBar.setLocation(0, this.getHeight()-toolBar.getHeight());//¾ê´« Àá±ñ ¸·¾Æ³ð
		toolBar2.setLocation(this.getWidth()-toolBar2.getWidth(), 0);
		repaint();
	}
	
	private void allGCPaint(Graphics2D g2d) {
		g2d.setTransform(DrawingPanelMoveAndZoom.getAT());
		for(GraphicComponent gc : GCStorage.getGCVector()) {gc.paint(g2d);}
		g2d.setTransform(new AffineTransform());		
	}
	
	private void aContainerPaint(Graphics2D g2d) {
		toolBar.myPaint(g2d);//draw AContainer
		toolBar2.myPaint(g2d);
	}
	
	private void AContainerDraggingComponentPaint(Graphics2D g2d) {
		DraggingComponentPaint(toolBar, g2d);//draw AContainer Drag Component
		DraggingComponentPaint(toolBar2, g2d);
	}

	private void DrawingPanelDraggingComponentPaint(Graphics2D g2d) {
		g2d.setTransform(DrawingPanelMoveAndZoom.getAT());
		if(DragAndDropManager.getComponentMasterPanel()==this&&DragAndDropManager.getDraggingComponent()!=null) {//show GC of drawPanel -> container
			DragAndDropManager.getDraggingComponent().paint(g2d);
		}
//		for(GraphicComponent gc : GCStorage.getSelectedGCVector()) {//select Test
//			gc.setFillColor(Color.red);
//			gc.paint(g2d);
//			gc.setFillColor(eColor.ShapeBasicFillColor.getVal());
//		}
		g2d.setTransform(new AffineTransform());
	}

	private void DraggingComponentPaint(AContainer ac, Graphics2D g2d) {//draw AContainer Drag Component
		if(ac.getCopy()!=null) {
			GraphicComponent nowGC = (GraphicComponent) DeepClone.clone(ac.getCopy().getGraphicComponent());
			Rectangle r = nowGC.getShape().getBounds();
			nowGC.setShape(new Rectangle(r.x+ac.getLocation().x, r.y+ac.getLocation().y+ac.getNowDeep(), r.width, r.height));
			nowGC.paint(g2d);
		}
	}
	
}
