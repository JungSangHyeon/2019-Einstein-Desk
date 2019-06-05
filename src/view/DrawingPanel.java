package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import canvas.CanvasGC;
import eventListener.DrawingPanelMouseHadler;
import eventListener.KeyDispatcher;
import global.InjectEnums.eColor;
import slide.SlideManager;
import slidePanel.SlideOnPanel;
import toolPanel.ToolPanel;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Text.FTextWrite_Stuff;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel implements Runnable {
	
	CanvasGC canvas;
	
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
		
		canvas = new CanvasGC(); 
		
//		this.add(new ToolSelectGCPanel());
//		this.add(new ToolBTNGCPanel());
//		this.add(new OffGCPanel());
		this.add(new ToolPanel());
		this.add(new SlideOnPanel());
//		this.add(new TestPanel());
		
		SlideManager.newSlide();
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
//		g2d.setTransform(DrawingPanelMoveAndZoom.getAT());
		paintGC(g2d);
		paintGCPanel(g2d);
	}

	private static void paintGCPanel(Graphics2D g2d) {
		for(GraphicComponent GCPanel : GCPanelStorage.getGCPanelVector()) {GCPanel.bottumPaint(g2d);}
		for(GraphicComponent GCPanel : GCPanelStorage.getGCPanelVector()) {GCPanel.paint(g2d);}
	}
	private static void paintGC(Graphics2D g2d) {
		CanvasGC.paint(g2d);
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {gc.bottumPaint(g2d);}
		//Normal Paint
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {gc.paint(g2d);}
		//Index Paint
//		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {if(!(gc.getAShape() instanceof pen)) {gc.paint(g2d);}}//shape
//		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {if(gc.getAShape() instanceof HighlightShape) {gc.paint(g2d);}}//highlight
//		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {if(!(gc.getAShape() instanceof HighlightShape)&&gc.getAShape() instanceof pen) {gc.paint(g2d);}}//pen
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {gc.topPaint(g2d);}
		CanvasGC.topPaint(g2d);
		g2d.setTransform(new AffineTransform());		
	}
	
	public class componentHandler extends ComponentAdapter{
		public void componentResized(ComponentEvent e) {ArrangeContainerLocation();}
	}
}
