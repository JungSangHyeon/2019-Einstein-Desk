package graphicComponent;

import fGlobalDataModify.FSetShape;
import fGlobalDataModify.FSetTool;
import zStuff_Function.AFunction;
import zStuff_Shape.eShape;

public class ShapeSelectGC {
	public static AFunction[] //add Functions Here
			rectSelector = {
					new FSetTool(eShape.rect.getAShape().getDrawer()),
					new FSetShape(eShape.rect.getAShape())
			},
			ellipseSelector = {
					new FSetTool(eShape.ellipse.getAShape().getDrawer()),
					new FSetShape(eShape.ellipse.getAShape())
			},
			speechSelector = {
					new FSetTool(eShape.speech.getAShape().getDrawer()),
					new FSetShape(eShape.speech.getAShape())
			},
			starNSelector = {
					new FSetTool(eShape.starN.getAShape().getDrawer()),
					new FSetShape(eShape.starN.getAShape())
			},
			star4Selector = {
					new FSetTool(eShape.star4.getAShape().getDrawer()),
					new FSetShape(eShape.star4.getAShape())
			},
			triangleSelector = {
					new FSetTool(eShape.triangle.getAShape().getDrawer()),
					new FSetShape(eShape.triangle.getAShape())
			},
			straightLineSelector = {
					new FSetTool(eShape.straightLine.getAShape().getDrawer()),
					new FSetShape(eShape.straightLine.getAShape())
			},
			polygonSelector = {
					new FSetTool(eShape.polygon.getAShape().getDrawer()),
					new FSetShape(eShape.polygon.getAShape())
			},
			rombusSelector = {
					new FSetTool(eShape.rombus.getAShape().getDrawer()),
					new FSetShape(eShape.rombus.getAShape())
	        }
	;
}
