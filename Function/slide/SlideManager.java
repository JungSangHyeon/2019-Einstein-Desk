package slide;

import java.util.Vector;

import redoUndo.RedoUndo;
import zStuff_Function.AFunction;
import zStuff_GraphicComponent.GCCreator;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class SlideManager {

	static int nowSlideNum = 0;
	static Vector<Vector<GraphicComponent>> slideVector = new Vector<Vector<GraphicComponent>>();
	public static void newSlide() {
		Vector<GraphicComponent> slide = new Vector<GraphicComponent>();
		slideVector.add(nowSlideNum, slide);
		clear();
		GCStorage_Normal.setGCStorage(slide);
		RedoUndo.setFirst();
		nowSlideNum++;
	}
	
	public static void loadSlide(int i) {
		clear();
		GCStorage_Normal.addAllToGC(slideVector.get(i));
		RedoUndo.setFirst();
	}
	
	public static void clear() {
		RedoUndo.clear();
//		GCStorage_Normal.clearGC();
		GCStorage_Selected.clearSelected();
	}
	
	public static Vector<GraphicComponent> getSlide(int slideNum) {
		return slideVector.get(slideNum);
	} 
	
	public static GraphicComponent getSlideGC(int slideNum) {
		AFunction[] functions = {new FSlide(slideNum)};
		return GCCreator.create(functions);
	}
}
