package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import GCPanel.OffGCPanel;
import GCPanel.ToolBTNGCPanel;
import GCPanel.ToolSelectGCPanel;
import PDR_NP_Shape.HighlightShape;
import PDR_NP_Shape.pen;
import data.GCPanelStorage;
import data.GCStorage_Normal;
import eventListener.DrawingPanelMouseHadler;
import eventListener.KeyDispatcher;
import global.GCCanvas;
import global.InjectEnums.eColor;
import moveAndZoom.DrawingPanelMoveAndZoom;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Text.FTextWrite_Stuff;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel implements Runnable {
	
	GCCanvas canvas;
	
	public DrawingPanel() {
		this.setBackground(eColor.DrawingPanelBackGroundColor.getVal());
		this.setLayout(null);
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	    manager.addKeyEventDispatcher(new KeyDispatcher(this));
	    
		this.addComponentListener(new componentHandler());
		DrawingPanelMouseHadler mouseHandler = new DrawingPanelMouseHadler(this);
		this.addMouseMotionListener(mouseHandler);
		this.addMouseWheelListener(mouseHandler);
		this.addMouseListener(mouseHandler);
		
		FTextWrite_Stuff.setting();
		this.add(FTextWrite_Stuff.getFocusArea());
		this.add(FTextWrite_Stuff.getTextEditArea());
		
		canvas = new GCCanvas(); 
		
		this.add(new ToolSelectGCPanel());
		this.add(new ToolBTNGCPanel());
		this.add(new OffGCPanel());
	}
	
	@Override
	public void run() {
		while(true) {
			repaint();
			try {Thread.sleep(20);}catch(Exception e) {}
		}
	}
	
	public void initialize() {
//		Thread th = new Thread(this); th.start();
		}
	private void add(GraphicComponent gc) {GCPanelStorage.add(gc);}
	public void ArrangeContainerLocation() {}
	
	public void paint(Graphics g) {
		GCStorage_Normal.killTargets();
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		super.paint(g2d);
		paintGC(g2d);
		paintGCPanel(g2d);
	}

	private void paintGCPanel(Graphics2D g2d) {for(GraphicComponent GCPanel : GCPanelStorage.getGCPanelVector()) {GCPanel.paint(g2d);}}
	private void paintGC(Graphics2D g2d) {
		g2d.setTransform(DrawingPanelMoveAndZoom.getAT());
		GCCanvas.paint(g2d);
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {gc.bottumPaint(g2d);}
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {if(!(gc.getAShape() instanceof pen)) {gc.paint(g2d);}}//shape
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {if(gc.getAShape() instanceof HighlightShape) {gc.paint(g2d);}}//highlight
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {if(!(gc.getAShape() instanceof HighlightShape)&&gc.getAShape() instanceof pen) {gc.paint(g2d);}}//pen
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {gc.topPaint(g2d);}
		GCCanvas.topPaint(g2d);
		g2d.setTransform(new AffineTransform());		
	}
	
	public class componentHandler extends ComponentAdapter{
		public void componentResized(ComponentEvent e) {ArrangeContainerLocation();}
	}

}
