package fTime;

import java.io.Serializable;

import calculation.AffineMath;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Time.ATimeFunction;

public class FAutoRotate extends ATimeFunction implements Serializable{
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
