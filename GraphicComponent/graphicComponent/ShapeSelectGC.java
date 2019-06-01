package graphicComponent;

import fGlobalDataModify.FSetShape;
import fGlobalDataModify.FSetTool;
import zStuff_Function.AFunction;
import zStuff_Shape.eShape;

public class ShapeSelectGC {
	public static AFunction[] //add Functions Here
			rectFunctions = {
					new FSetTool(eShape.rect.getAShape().getDrawer()),
					new FSetShape(eShape.rect.getAShape())
			},
			ellipseFunctions = {
					new FSetTool(eShape.ellipse.getAShape().getDrawer()),
					new FSetShape(eShape.ellipse.getAShape())
			},
			speechFunctions = {
					new FSetTool(eShape.speech.getAShape().getDrawer()),
					new FSetShape(eShape.speech.getAShape())
			},
			starNFunctions = {
					new FSetTool(eShape.starN.getAShape().getDrawer()),
					new FSetShape(eShape.starN.getAShape())
			},
			star4Functions = {
					new FSetTool(eShape.star4.getAShape().getDrawer()),
					new FSetShape(eShape.star4.getAShape())
			},
			triangleFunctions = {
					new FSetTool(eShape.triangle.getAShape().getDrawer()),
					new FSetShape(eShape.triangle.getAShape())
			},
			straightLineFunctions = {
					new FSetTool(eShape.straightLine.getAShape().getDrawer()),
					new FSetShape(eShape.straightLine.getAShape())
			},
			polygonFunctions = {
					new FSetTool(eShape.polygon.getAShape().getDrawer()),
					new FSetShape(eShape.polygon.getAShape())
			}
	;
}
