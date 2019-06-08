package slide;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Vector;

import calculation.SuperVector;
import fGCDataModify.FMove_Item;
import redoUndo.RedoUndo;
import zStuff_Function.AFunction;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GCPanel_LayoutPixel.Item;
import zStuff_GraphicComponent.GCCreator;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

@SuppressWarnings("serial")
public class SlidePanel extends GCPanel_LayoutPixel_Y {

	public SlidePanel() {
		this.setPixelSize(254, 144);
		this.setPixelGap(21, 18);
		this.setSize(1, 6);
		this.setGCLocation(0, 48);
		this.saveClip();
		
		this.setBackgroundColor(new Color(230,230,230));
		this.setBorderColor(new Color(191,191,191));
		this.setBorderPaint(true);
		
		this.setSeatNoticeColor(new Color(230,230,230));
		this.setShadow(false);
		this.setPush(true);
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		
		if(needReset) {
			itemVector.clear();
			needReset = false;
		}
		if(needUpdate.size()>0) {
			for(GraphicComponent gc : needUpdate) {
				this.add(gc);
				}
			needUpdate.clear();
		}
		Item currentItem = this.getCurrentItem();
		if(currentItem!=null) {currentItem.getInContainerGC().setBorderPaint(false);}
		super.paint(g2d);
		if(currentItem!=null) {currentItem.getInContainerGC().setBorderPaint(true);}
		
		currentItem = this.getCurrentItem();//스태틱 억지로 쓰느라.
		if(currentItem!=null) {currentItem.getInContainerGC().setBorderPaint(false);}
		super.paint(g2d);
		if(currentItem!=null) {currentItem.getInContainerGC().setBorderPaint(true);}
	}

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	public static GraphicComponent getSlideGC(int i) {
		AFunction[] functions = {new FSlide(i), new FMove_Item()};
		GraphicComponent slide = GCCreator.create(functions);
		slide.setBorderPaint(true);
		slide.setFillPaint(true);
		slide.setBorderColor(new Color(198,198,198));
		slide.setFillColor(Color.white);
		slide.setborderThick(1);
		return slide;
	}
	
	static Vector<GraphicComponent> needUpdate = new Vector<GraphicComponent>();
	static Vector<Vector<GraphicComponent>> slides = new Vector<Vector<GraphicComponent>>();
	static Vector<Vector<GraphicComponent>> slidesForSave = new Vector<Vector<GraphicComponent>>();
	
//	public static Vector<Vector<GraphicComponent>> getSlideForSave(){return slidesForSave;}
	public static Vector<Vector<GraphicComponent>> getSlideForSave(){return slidesForSave;}
	
	public static Vector<Vector<GraphicComponent>> getSlides(){return slides;}
	
	@Override
	public Point changeSeat() {
		Point showChange = super.changeSeat();
		if(showChange!=null) {
			SuperVector.change(slidesForSave, showChange.x, showChange.y);
		}
		System.out.println("@@@@@@@@@@@");
		for(Vector<GraphicComponent> slide : slidesForSave) {
			System.out.println(slide.size());
		}
		return null;
	}
	
	public static void newSlide() {
		Vector<GraphicComponent> slide = new Vector<GraphicComponent>();
		slides.add(slide);
		slidesForSave.add(slide);
		GCStorage_Normal.setGCStorage(slide);
		needUpdate.add(getSlideGC(slides.size()-1));
		resetGCStorage_Normal();
	}
	
	public static void loadSlide(int i) {
		GCStorage_Normal.setGCStorage(slides.get(i));
		resetGCStorage_Normal();
	}
	public static Vector<GraphicComponent> getSlide(int i) {
		return slides.get(i);
	}
	public static void resetGCStorage_Normal() {
		RedoUndo.clear();
		GCStorage_Selected.clearSelected();
		RedoUndo.setFirst();
	}
	static boolean needReset = false;
	public static void setSlide(Vector<Vector<GraphicComponent>> data) {
		needReset = true;
		slides.clear();
		slides.addAll(data);
		slidesForSave = data;
		for(int i=0; i<slides.size(); i++) {
			needUpdate.add(getSlideGC(i));
		}
		GCStorage_Normal.setGCStorage(data.get(0));
		resetGCStorage_Normal();
	}
}
