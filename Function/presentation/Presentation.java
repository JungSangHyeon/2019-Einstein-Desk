package presentation;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;

import canvas.CanvasGC;
import canvasMoveAndZoom.GlobalAT;
import deepClone.DeepClone;
import fText.FTextWrite;
import slide.SlidePanel;
import slidePanel.SlideOnPanel;
import toolPanel.ToolPanel;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Shape.eShape;

public class Presentation {

	static boolean presentationOn = false;
	public static void on() {presentationOn = true;}
	public static void off() {presentationOn = false;}
	public static boolean isOn() {return presentationOn;}
	
	public static Vector<Vector<GraphicComponent>> originSlide;
	public static Vector<Vector<GraphicComponent>> presentationSlide;
	public static Vector<GraphicComponent> goBack;
	public static AffineTransform originalAT;
	static int nowSlide = 0;
	
	@SuppressWarnings("unchecked")
	public static void startPresentation() {
		if(!Presentation.isOn()) {
			Presentation.on();
			goBack = GCStorage_Normal.getGCVector();
			originSlide = SlidePanel.getSlideForSave();
			presentationSlide =  (Vector<Vector<GraphicComponent>>) DeepClone.clone(originSlide);
			GCStorage_Normal.setGCStorage(presentationSlide.get(nowSlide));
			presentationSlide.add(getShowFinishedSlide());
			GCPanelStorage.getGCPanelVector().clear();
			originalAT = GlobalAT.getNowAT();
			
			AffineTransform changeAT = new AffineTransform();
			changeAT.setToTranslation(0,0);
			changeAT.scale(1920/CanvasGC.getWidth(), 1080/CanvasGC.getHeight());
			changeAT.translate(-CanvasGC.getX(), -CanvasGC.getY());
			
			GlobalAT.setAT(changeAT);
			GlobalAT.off();
		}
	}
	
	private static Vector<GraphicComponent> getShowFinishedSlide() {
		Vector<GraphicComponent> slide = new Vector<GraphicComponent>();
		GraphicComponent gc = new GraphicComponent();
		gc.addPoint(new Point2D.Float(0,0));
		gc.addPoint(new Point2D.Float(1920, 1080));
		gc.setAShape(eShape.rect.getAShape());
		gc.setShape(gc.getAShape().newShape(gc.getPoints()));
		gc.setFillColor(Color.BLACK);
		gc.setBorderPaint(false);
		gc.addFunction(new FTextWrite());
		gc.setText("(･ิω･ิ)  발표 끗!");
		slide.add(gc);
		return slide;
	}
	
	public static void nextSlide() {
		if (Presentation.isOn()) {
			nowSlide++;
			if (nowSlide == presentationSlide.size()) {endPresentation();}
			else {GCStorage_Normal.setGCStorage(presentationSlide.get(nowSlide));}
		}
	}
	public static void beforeSlide() {
		if(Presentation.isOn()&&nowSlide>0) {nowSlide--; GCStorage_Normal.setGCStorage(presentationSlide.get(nowSlide));}
	}
	
	public static void endPresentation() {
		if(Presentation.isOn()) {
			Presentation.off();
			nowSlide = 0;
			GCPanelStorage.add(new ToolPanel());
			GCPanelStorage.add(new SlideOnPanel());
			SlidePanel.setSlide(originSlide);
			GlobalAT.setAT(originalAT);
			GlobalAT.on();
		}
	}
	
}
