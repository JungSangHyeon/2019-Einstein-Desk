package zStuff_GraphicComponent;

import zStuff_Function.AFunction;
import zStuff_Shape.eShape;

public class GCCreator {

	public static GraphicComponent create(AFunction[] functions) {
		GraphicComponent GC = new GraphicComponent();
		GC.setAShape(eShape.rect.getAShape());
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		for(AFunction function : functions) {GC.addFunction(function);}
		return GC;
	}
}
