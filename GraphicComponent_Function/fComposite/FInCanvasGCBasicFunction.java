package fComposite;

import fGCDataModify.FMove;
import fGCDataModify.FResize;
import fGCDataModify.FRotate;
import fText.FTextWrite;
import zStuff_Function.AFunction;

public class FInCanvasGCBasicFunction extends AFunction{
	private static final long serialVersionUID = 6285693958351703402L;

	public FInCanvasGCBasicFunction() {
		this.addAggregateFunction(new FResize());
		this.addAggregateFunction(new FMove());
		this.addAggregateFunction(new FRotate());
		this.addAggregateFunction(new FTextWrite());
	}
}
