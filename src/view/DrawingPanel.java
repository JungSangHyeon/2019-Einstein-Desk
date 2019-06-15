package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JPanel;

import PDR_NP_Shape.HighlightShape;
import PDR_NP_Shape.pen;
import SAL.ProjectManager;
import canvas.CanvasGC;
import canvasMoveAndZoom.GlobalAT;
import eventListener.DrawingPanelMouseHadler;
import eventListener.KeyDispatcher;
import global.InjectEnums.eColor;
import onOff.Debug;
import toolPanel.ToolPanel;
import zStuff_Data.ToolData;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GraphicComponent.GCStorage_KillTarget;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Text.FTextWrite_Stuff;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel implements Runnable {
	
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

		new CanvasGC(); 
		ProjectManager.init();
		GCPanelStorage.add(new ToolPanel());
	}
	
	@Override
	public void run() {
//		while(true) {
//			repaint();
//			try {Thread.sleep(20);}catch(Exception e) {}
//		}
	}
	
	public void initialize() {
//		Thread th = new Thread(this); th.start();//pause
	}
	
	public void ArrangeContainerLocation() {}
	
	public void paint(Graphics g) {
		GCStorage_KillTarget.killTargets();
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		super.paint(g2d);
		paintGC(g2d, GCStorage_Normal.getGCVector());
		ToolData.getNowTool().toolPaint(g2d);
		paintGCPanel(g2d);
	}

	private static void paintGCPanel(Graphics2D g2d) {
		for(GraphicComponent GCPanel : GCPanelStorage.getGCPanelVector()) {GCPanel.bottumPaint(g2d);}
		for(GraphicComponent GCPanel : GCPanelStorage.getGCPanelVector()) {GCPanel.paint(g2d);}
	}
	
	private static void paintGC(Graphics2D g2d, Vector<GraphicComponent> targets) {
		g2d.setTransform(GlobalAT.getNowAT());
		CanvasGC.paint(g2d);
		
		//Filter 1 : Zoom ¸¹ÀÌ ÇßÀ»‹š »¡¶óÁü.
		Shape rect = new Rectangle2D.Double(0,0,1920,1080);
		if(Debug.isOn()) {rect = new Rectangle2D.Double(0,0,1000,1080);}
		
		try {rect = GlobalAT.getNowAT().createInverse().createTransformedShape(rect);}
		catch (NoninvertibleTransformException e) {e.printStackTrace();}
		g2d.setClip(rect);
		
		Vector<GraphicComponent> drawTarget = new Vector<GraphicComponent>();
		for(GraphicComponent gc2 : targets) {
			if(gc2.getShape().intersects(rect.getBounds())) {drawTarget.add(gc2);}
		}
		
		for(GraphicComponent gc : drawTarget) {gc.bottumPaint(g2d);}
		for(GraphicComponent gc : drawTarget) {if(!(gc.getAShape() instanceof pen)) {gc.paint(g2d);}}//shape
		for(GraphicComponent gc : drawTarget) {if(gc.getAShape() instanceof HighlightShape) {gc.paint(g2d);}}//highlight
		for(GraphicComponent gc : drawTarget) {if(!(gc.getAShape() instanceof HighlightShape)&&gc.getAShape() instanceof pen) {gc.paint(g2d);}}//pen
		for(GraphicComponent gc : drawTarget) {gc.topPaint(g2d);}
		CanvasGC.topPaint(g2d);
		g2d.setTransform(new AffineTransform());	
	}
	
	public class componentHandler extends ComponentAdapter{
		public void componentResized(ComponentEvent e) {ArrangeContainerLocation();}
	}
}
