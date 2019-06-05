package slide;

import java.awt.Color;
import java.util.Vector;

import fGCDataModify.FMove_Weak;
import redoUndo.RedoUndo;
import zStuff_Function.AFunction;
import zStuff_GraphicComponent.GCCreator;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class SlideManager {

	static int nowSlideNum = 0;
	public static int getNowSlideNum() {return nowSlideNum;}
	static Vector<Vector<GraphicComponent>> slideVector = new Vector<Vector<GraphicComponent>>();
	
	public static int getSlideNum() {
		return slideVector.size();
	}
	
	public static Vector<Vector<GraphicComponent>> getSlideVector() {
		return slideVector;
	}
	
	public static void newSlide() {
//		DrawingPanelMoveAndZoom.allZero();
		Vector<GraphicComponent> slide = new Vector<GraphicComponent>();
//		nowSlideNum = slideVector.size();
		slideVector.add(nowSlideNum, slide);
		nowSlideNum++;
		GCStorage_Normal.setGCStorage(slide);
		clear();
	}
	
	public static void loadSlide(int i) {
//		DrawingPanelMoveAndZoom.allZero();
		GCStorage_Normal.setGCStorage(slideVector.get(i));
		clear();
		nowSlideNum = i+1;
	}
	
	public static void clear() {
		RedoUndo.clear();
		GCStorage_Selected.clearSelected();
		RedoUndo.setFirst();
	}
	
	public static Vector<GraphicComponent> getSlide(int slideNum) {
		return slideVector.get(slideNum);
	} 
	
	public static GraphicComponent getSlideGC(int slideNum) {
		AFunction[] functions = {new FSlide(slideNum), new FMove_Weak(), new FShowSelectedByRect()};
		GraphicComponent slide = GCCreator.create(functions);
		slide.setBorderPaint(true);
		slide.setFillPaint(true);
		slide.setBorderColor(new Color(119,119,119));
		slide.setFillColor(Color.white);
		slide.setborderThick(1);
		return slide;
	}

	public static boolean isNowSlide(Vector<GraphicComponent> slide) {
		// TODO 자동 생성된 메소드 스텁
		return slideVector.get(nowSlideNum).equals(slide);
	}
}
