package slide;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import calculation.AffineMath;
import fGCDataModify.FMove_Weak;
import redoUndo.RedoUndo;
import zStuff_Function.AFunction;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel;
import zStuff_GCPanel_LayoutPixel.Item;
import zStuff_GCPanel_LayoutPixel.Pixel;
import zStuff_GraphicComponent.GCCreator;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

@SuppressWarnings("serial")
public class SlidePanel extends GCPanel_LayoutPixel {

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
		if(needUpdate.size()>0) {
			for(GraphicComponent gc : needUpdate) {this.add(gc);}
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
		AFunction[] functions = {new FSlide(i), new FMove_Weak(), new FShowSelectedByRect()};
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
	
	public static Vector<Vector<GraphicComponent>> getSlides(){return slides; }
	public static void newSlide() {
		Vector<GraphicComponent> slide = new Vector<GraphicComponent>();
		slides.add(slide);
		GCStorage_Normal.setGCStorage(slide);
		needUpdate.add(getSlideGC(slides.size()-1));
		resetGCStorage_Normal();
	}
	
	public static void loadSlide(int i) {
		for(Vector<GraphicComponent> slide : SlidePanel.getSlides()) {
			if(GCStorage_Normal.getGCVector()!=slide) {
				for(GraphicComponent gc : slide) {
					AffineMath.applyAffineTransformToGC(forFriendAT, gc);
					gc.setborderThick(gc.getBorderThick()*(float)(forFriendAT.getScaleX()));
					for(GraphicComponent aggreGC : gc.getAllAggregateGCs()) {
						AffineMath.applyAffineTransformToGC(forFriendAT, aggreGC);
						aggreGC.setborderThick(aggreGC.getBorderThick()*(float)(forFriendAT.getScaleX()));
					}
				}
			}
		}
		GCStorage_Normal.setGCStorage(slides.get(i));
		resetGCStorage_Normal();
		forFriendAT = new AffineTransform();
	}
	public static Vector<GraphicComponent> getSlide(int i) {
		return slides.get(i);
	}
	public static void resetGCStorage_Normal() {
		RedoUndo.clear();
		GCStorage_Selected.clearSelected();
		RedoUndo.setFirst();
	}
	static AffineTransform forFriendAT = new AffineTransform();
	public static void applyAffine(AffineTransform at) {
//		forFriendAT.concatenate(at);
		forFriendAT.preConcatenate(at);
	}
	public static void removeAT() {
		forFriendAT = new AffineTransform();
	}
}
