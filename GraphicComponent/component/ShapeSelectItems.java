package component;

import GCPanel.HighlightSettingGCPanel;
import GCPanel.PenSettingGCPanel;
import component_Stuff.GraphicComponent;
import fGCDataModify.FMove_Weak;
import fGlobalDataModify.FSetShape;
import fGlobalDataModify.FSetTool;
import fImagePaint.FImageNormalPaint;
import fPaint.FGCpeekaboo;
import fPaint.FPaintMasterWithHighLightColor;
import fPaint.FPaintMasterWithPenColor;
import fPaint.FShowMouseOn;
import fPaint.FShowSelected;
import shape_Stuff.AShape;
import shape_Stuff.eShape;
import zStuff_Function.AFunction;


public class ShapeSelectItems {

	public enum eShapeSelectItem{
		eRectItem(eShape.rect.getAShape()),
		eEllipseItem(eShape.ellipse.getAShape()),
		eSpeechItem(eShape.speech.getAShape()),
		eStar4Item(eShape.starN.getAShape()),
//		eStar4Item(eShape.star4.getAShape(), new Paint_NormalIMG("", "Icons/jake_22X22.txt")),
		eTriangleItem(eShape.triangle.getAShape()),
		eStraightLineItem(eShape.straightLine.getAShape()),
		ePolygonItem(eShape.polygon.getAShape()),
		
		eFreeLineItem(eShape.pen.getAShape()),
		eFreeHighlightItem(eShape.highlight.getAShape()),
		;
		
		private AShape shape;
		private eShapeSelectItem(AShape shape) {
			this.shape=shape;
		}
		public AShape getSelectShape() {return this.shape;}
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
		GC.addFunction(new FMove_Weak());
		return GC;
	}
	
	public static GraphicComponent getPenItem(eShapeSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(new FSetTool(item.getSelectShape().getDrawer()));//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(new FSetShape(item.getSelectShape()));
		GC.addFunction(new FPaintMasterWithPenColor());
		GC.addFunction(new FImageNormalPaint("ToolBarImgs/pen.png"));
		GC.setAShape(eShape.rect.getAShape());
		GC.addFunction(new FShowSelected());
		GC.addFunction(new FShowMouseOn());
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
		GC.addFunction(new FImageNormalPaint("ToolBarImgs/highlight.png"));
		GC.setAShape(eShape.rect.getAShape());
		GC.addFunction(new FShowSelected());
		GC.addFunction(new FShowMouseOn());
		GC.addFunction(new FGCpeekaboo(new HighlightSettingGCPanel()));
		return GC;
	}
}
