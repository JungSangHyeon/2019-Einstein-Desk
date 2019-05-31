package component;

import GCPanel.HighlightSettingGCPanel;
import GCPanel.PenSettingGCPanel;
import component_Stuff.GraphicComponent;
import dataModifyFunction.FSetShape;
import dataModifyFunction.FSetTool;
import function_Paint.FGCpeekaboo;
import function_Paint.FPaintMasterWithHighLightColor;
import function_Paint.Paint_NormalIMG;
import function_Paint.FPaintMasterWithPenColor;
import function_Paint.FPaintMasterIfMouseOn;
import function_Paint.FShowSelected;
import function_Shape.Shape_MoverWeak;
import shape_Stuff.AShape;
import shape_Stuff.eShape;
import zFunction_Stuff.AFunction;


public class ShapeSelectItems {

	public enum eShapeSelectItem{
		eRectItem(eShape.rect.getAShape(), new Paint_NormalIMG("rect", "Icons/jake_22X22.txt")),
		eEllipseItem(eShape.ellipse.getAShape(), new Paint_NormalIMG("ellipse", "Icons/jake_22X22.txt")),
		eSpeechItem(eShape.speech.getAShape(), new Paint_NormalIMG("", "Icons/jake_22X22.txt")),
		eStar4Item(eShape.star4.getAShape(), new Paint_NormalIMG("", "Icons/jake_22X22.txt")),
		eTriangleItem(eShape.triangle.getAShape(), new Paint_NormalIMG("", "Icons/jake_22X22.txt")),
		eStraightLineItem(eShape.straightLine.getAShape(), new Paint_NormalIMG("straightLine", "Icons/jake_22X22.txt")),
		ePolygonItem(eShape.polygon.getAShape(), new Paint_NormalIMG("polygon", "Icons/jake_22X22.txt")),
		
		eFreeLineItem(eShape.pen.getAShape(), new Paint_NormalIMG("", "ToolBarImgs/pen.png")),
		eFreeHighlightItem(eShape.highlight.getAShape(), new Paint_NormalIMG("", "ToolBarImgs/highlight.png")),
		;
		
		private AShape shape;  private AFunction function;
		private eShapeSelectItem(AShape shape, AFunction painter) {
			this.shape=shape;
			this.function=painter;
		}
		public AShape getSelectShape() {return this.shape;}
		public AFunction getPainter() {return this.function;}
	}
	
	public static GraphicComponent getGCItem(eShapeSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(new FSetTool(item.getSelectShape().getDrawer()));//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(new FSetShape(item.getSelectShape()));
//		GC.addFunction(item.getPainter());
		GC.setAShape(eShape.rect.getAShape());
//		GC.addFunction(new Paint_showMouseOnMe());
		GC.addFunction(new Shape_MoverWeak());
		return GC;
	}
	
	public static GraphicComponent getPenItem(eShapeSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(new FSetTool(item.getSelectShape().getDrawer()));//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(new FSetShape(item.getSelectShape()));
		GC.addFunction(new FPaintMasterWithPenColor());
		GC.addFunction(item.getPainter());
		GC.setAShape(eShape.rect.getAShape());
		GC.addFunction(new FShowSelected());
		GC.addFunction(new FPaintMasterIfMouseOn());
		GC.addFunction(new FGCpeekaboo(new PenSettingGCPanel()));
		return GC;
	}
	public static GraphicComponent getHighlightItem(eShapeSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(new FSetTool(item.getSelectShape().getDrawer()));//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(new FSetShape(item.getSelectShape()));
		GC.addFunction(new FPaintMasterWithHighLightColor());
		GC.addFunction(item.getPainter());
		GC.setAShape(eShape.rect.getAShape());
		GC.addFunction(new FShowSelected());
		GC.addFunction(new FPaintMasterIfMouseOn());
		GC.addFunction(new FGCpeekaboo(new HighlightSettingGCPanel()));
		return GC;
	}
}
