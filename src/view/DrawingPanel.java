package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.JPanel;

import component_Stuff.GraphicComponent;
import container.ShapeSelectContainer;
import container.ToolSelectContainer;
import container_Stuff.AContainer;
import data.GCStorage;
import deepClone.DeepClone;
import dragAndDrop.DragAndDropManager;
import eventListener.DrawingPanelMouseHadler;
import global.InjectEnums.eColor;
import moveAndZoom.DrawingPanelMoveAndZoom;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel {
	
	ToolSelectContainer toolSelector;
	ShapeSelectContainer shapeSelector;
	Vector<AContainer> containers;
	
	public DrawingPanel() {
		this.setBackground(eColor.DrawingPanelBackGroundColor.getVal());
		DrawingPanelMouseHadler mouseHandler = new DrawingPanelMouseHadler(this);
		this.addMouseMotionListener(mouseHandler);
		this.addMouseWheelListener(mouseHandler);
		this.addMouseListener(mouseHandler);
		this.addComponentListener(new componentHandler());
		this.setLayout(null);

		containers = new Vector<AContainer>();
		toolSelector = new ToolSelectContainer();
		shapeSelector = new ShapeSelectContainer();
		
		containers.add(toolSelector);
		containers.add(shapeSelector);

		for(AContainer container : containers) {
			container.setLocation(100, 100);
			container.addMaster(this);
			this.add(container);
		}
//		ArrangeContainerLocation();
	}
	
	public void initialize() {}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		allGCPaint(g2d);
		aContainerPaint(g2d);
		
		AContainerDraggingComponentPaint(g2d);
		DrawingPanelDraggingComponentPaint(g2d);
		System.out.println("repaint");
	}
	
	public class componentHandler implements ComponentListener{
		public void componentResized(ComponentEvent e) {ArrangeContainerLocation();}
		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
	}
	
	public void ArrangeContainerLocation() {
		toolSelector.setLocation(this.getWidth()-toolSelector.getWidth(), 0);//R U
		shapeSelector.setLocation(0, 0);//L U
		repaint();
	}
	
	private void allGCPaint(Graphics2D g2d) {
		g2d.setTransform(DrawingPanelMoveAndZoom.getAT());
		for(GraphicComponent gc : GCStorage.getGCVector()) {gc.paint(g2d);}
		g2d.setTransform(new AffineTransform());
	}
	
	private void aContainerPaint(Graphics2D g2d) {//draw AContainer
		for(AContainer container : containers) {container.myPaint(g2d);}
	}
	
	private void AContainerDraggingComponentPaint(Graphics2D g2d) {//draw AContainer Drag Component
		for(AContainer container : containers) {DraggingComponentPaint(container, g2d);}
	}

	private void DrawingPanelDraggingComponentPaint(Graphics2D g2d) {//show GC of drawPanel -> container
		g2d.setTransform(DrawingPanelMoveAndZoom.getAT());
		if(DragAndDropManager.getComponentMasterPanel()==this&&DragAndDropManager.getDraggingComponent()!=null) {
			DragAndDropManager.getDraggingComponent().paint(g2d);
		}
		g2d.setTransform(new AffineTransform());
	}

	public void DraggingComponentPaint(AContainer ac, Graphics2D g2d) {//draw AContainer Drag Component
		if(ac.getCopy()!=null) {
			GraphicComponent nowGC = (GraphicComponent) DeepClone.clone(ac.getCopy().getGraphicComponent());
			Rectangle r = nowGC.getShape().getBounds();
			nowGC.setShape(new Rectangle(r.x+ac.getLocation().x, r.y+ac.getLocation().y+ac.getNowDeep(), r.width, r.height));
			nowGC.paint(g2d);
		}
	}

}
