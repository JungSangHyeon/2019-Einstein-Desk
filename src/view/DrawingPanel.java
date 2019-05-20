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
import container.HighlightSettingPanel;
import container.PenSettingPanel;
import container.ShapeSelectContainer;
import container.ToolSelectContainer;
import container_Stuff.AContainer;
import data.GCStorage;
import deepClone.DeepClone;
import dragAndDrop.DragAndDropManager;
import eventListener.DrawingPanelMouseHadler;
import global.InjectEnums.eColor;
import moveAndZoom.DrawingPanelMoveAndZoom;
import shape.HighlightShape;
import shape.pen;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel {
	
	Vector<AContainer> containers;
	ToolSelectContainer toolSelector;
	ShapeSelectContainer shapeSelector;
	
	public DrawingPanel() {
		this.setBackground(eColor.DrawingPanelBackGroundColor.getVal());
		this.setLayout(null);
		
		this.addComponentListener(new componentHandler());
		DrawingPanelMouseHadler mouseHandler = new DrawingPanelMouseHadler(this);
		this.addMouseMotionListener(mouseHandler);
		this.addMouseWheelListener(mouseHandler);
		this.addMouseListener(mouseHandler);
		
		toolSelector = new ToolSelectContainer();
		shapeSelector = new ShapeSelectContainer();
		containers = new Vector<AContainer>();
		containers.add(toolSelector);
		containers.add(shapeSelector);
		
		for(AContainer container : containers) {this.add(container);}
		
		psp = new PenSettingPanel();
		psp.setLocation(1351,50);
		this.add(psp);
		
		hsp = new HighlightSettingPanel();
		hsp.setLocation(1399,50);//ȣȣȣ
		this.add(hsp);
		
	}
	
	PenSettingPanel psp;
	HighlightSettingPanel hsp;
	
	public void initialize() {
		for(AContainer container : containers) {container.addMaster(this);}
	}
	
	public void ArrangeContainerLocation() {
		toolSelector.setLocation(this.getWidth()-toolSelector.getWidth(), 0);//R U
		shapeSelector.setLocation(0, 200);//L U
		repaint();
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		super.paint(g2d);
		paintGC(g2d);
		
		for(AContainer container : containers) {container.paintImg(g2d);}
		psp.paint(g2d);
		hsp.paint(g2d);
		
		if(DragAndDropManager.isDADOn()) {
			drawDrawingPanelToContainer(g2d);
			drawContainerToDrawingPanel(g2d);
		}
	}

	private void paintGC(Graphics2D g2d) {
		g2d.setTransform(DrawingPanelMoveAndZoom.getAT());
		for(GraphicComponent gc : GCStorage.getGCVector()) {
			if(!(gc.getAShape() instanceof pen)) {gc.paint(g2d);}//shape
		}
		for(GraphicComponent gc : GCStorage.getGCVector()) {
			if(gc.getAShape() instanceof HighlightShape) {gc.paint(g2d);}//highlight
		}
		for(GraphicComponent gc : GCStorage.getGCVector()) {
			if(!(gc.getAShape() instanceof HighlightShape)&&gc.getAShape() instanceof pen) {gc.paint(g2d);}//pen
		}
		g2d.setTransform(new AffineTransform());		
	}

	private void drawDrawingPanelToContainer(Graphics2D g2d) {
		if(DragAndDropManager.getComponentMasterPanel()==this&&DragAndDropManager.getDraggingComponent()!=null) {
			g2d.setTransform(DrawingPanelMoveAndZoom.getAT());
			DragAndDropManager.getDraggingComponent().paint(g2d);
			g2d.setTransform(new AffineTransform());
		}
	}

	private void drawContainerToDrawingPanel(Graphics2D g2d) {
		if(DragAndDropManager.getComponentMasterPanel()!=null&&DragAndDropManager.getComponentMasterPanel()!=this&&DragAndDropManager.getDraggingComponent()!=null) {
			AContainer nowDCM = (AContainer)DragAndDropManager.getComponentMasterPanel();
			GraphicComponent nowGC = (GraphicComponent) DeepClone.clone(DragAndDropManager.getDraggingComponent());
			Rectangle r = nowGC.getShape().getBounds();
			nowGC.setShape(new Rectangle(r.x+nowDCM.getLocation().x, r.y+nowDCM.getLocation().y+nowDCM.getNowDeep(), r.width, r.height));
			nowGC.paint(g2d);
		}
	}
	
	public class componentHandler implements ComponentListener{
		public void componentResized(ComponentEvent e) {ArrangeContainerLocation();}
		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
	}

}
