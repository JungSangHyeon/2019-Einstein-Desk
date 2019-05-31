package time_Special;

import java.io.Serializable;

import calculation.AffineMath;
import component_Stuff.GraphicComponent;
import time_Stuff.FTimeFunction;

public class FAutoRotate extends FTimeFunction implements Serializable{
	private static final long serialVersionUID = -6743524881365403749L;
	
	double dAngle = 1;

	@Override
	public void timeAction() {
		AffineMath.rotateGC(dAngle, master.getCenter(), master);
		for(GraphicComponent aggreGC : master.getAllAggregateGCs()) {
			AffineMath.rotateGC(dAngle, master.getCenter(), aggreGC);
		}
	}
	
}
