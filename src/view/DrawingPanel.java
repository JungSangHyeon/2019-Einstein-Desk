package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

import javax.swing.JPanel;

import container.ToolBar;
import data.GCStorage;
import deepClone.DeepClone;
import dragAndDrop.DragAndDropManager;
import eventListener.DrawingPanelMouseHadler;
import global.InjectEnums.eColor;
import moveAndZoom.MoveAndZoom;
import stuff_Component.GraphicComponent;
import stuff_Container.AContainer;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel{
	
	ToolBar toolBar, toolBar2;
	DrawingPanelMouseHadler mouseHandler;
	
	public DrawingPanel() {
		this.setBackground(eColor.DrawingPanelBackGroundColor.getVal());
		
		mouseHandler = new DrawingPanelMouseHadler(this);
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.addMouseWheelListener(mouseHandler);
		this.addComponentListener(new componentHandler());
		this.setLayout(null);

		toolBar = new ToolBar();
		toolBar.setLocation(100, 100);
		toolBar.addMaster(this);
		this.add(toolBar);
		
		toolBar2 = new ToolBar();
		toolBar2.addMaster(this);
		this.add(toolBar2);
		
		Arrange();
	}
	
	public void initialize() {
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		Iterator<GraphicComponent> shapes = GCStorage.getGCVectorIterator();
		
		g2d.setTransform(MoveAndZoom.getAT());
		
		while(shapes.hasNext()){
			GraphicComponent shapeData = shapes.next();
			shapeData.paint(g2d);
		}
		g2d.setTransform(new AffineTransform());
		
		toolBar.myPaint(g2d);
		toolBar2.myPaint(g2d);
		
		aContainerDragComponentPaint(toolBar, g2d);
		aContainerDragComponentPaint(toolBar2, g2d);
		
		g2d.setTransform(MoveAndZoom.getAT());
		if(DragAndDropManager.getComponentMasterPanel()==this&&DragAndDropManager.getDraggingComponent()!=null) {//drag & dropÀ» À§ÇØ!
			DragAndDropManager.getDraggingComponent().paint(g2d);
		}
		g2d.setTransform(new AffineTransform());
	}
	
	private void aContainerDragComponentPaint(AContainer ac, Graphics2D g2d) {
		if(ac.getCopy()!=null) {//drag & dropÀ» À§ÇØ!
			GraphicComponent nowGC = (GraphicComponent) DeepClone.clone(ac.getCopy().getGraphicComponent());
			Rectangle r = nowGC.getShape().getBounds();
			nowGC.setShape(new Rectangle(r.x+ac.getLocation().x, r.y+ac.getLocation().y+ac.getNowDeep(), r.width, r.height));
			nowGC.paint(g2d);
		}
	}
	
	public class componentHandler implements ComponentListener{
		public void componentResized(ComponentEvent e) {Arrange();}
		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
	}
	
	public void Arrange() {
//		toolBar.setLocation(0, this.getHeight()-toolBar.getHeight());//¾ê´« Àá±ñ ¸·¾Æ³ð
		toolBar2.setLocation(this.getWidth()-toolBar2.getWidth(), 0);
		repaint();
	}
	
}
