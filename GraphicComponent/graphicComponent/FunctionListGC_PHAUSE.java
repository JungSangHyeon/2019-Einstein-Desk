package graphicComponent;

import fPaint.FPaintMasterWithHighLightColor;
import fPaint.FShadow;
import fTime.FAutoRotate;
import zStuff_Function.AFunction;

public class FunctionListGC_PHAUSE {
	public static AFunction[] //add Functions Here
			PaintFunctions = {
					new FShadow(),
					new FPaintMasterWithHighLightColor(),
			},
		    TimeFunctions = {
		    		new FAutoRotate(),
			}
	;
}
