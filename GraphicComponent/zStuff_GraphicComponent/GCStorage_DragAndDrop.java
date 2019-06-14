package zStuff_GraphicComponent;

import java.util.Vector;

public class GCStorage_DragAndDrop {
	
	private static Vector<GraphicComponent> canvasToPanelGC = new Vector <GraphicComponent>();
	public static void addGCToCanvasToPanel(GraphicComponent gc) {canvasToPanelGC.add(gc);}
	public static void addAllToCanvasToPanel(Vector<GraphicComponent> shapeDatas) {canvasToPanelGC.addAll(shapeDatas);}
	public static void clearCanvasToPanel() {canvasToPanelGC.clear();}
	public static Vector<GraphicComponent> getCanvasToPanel() {return canvasToPanelGC;}
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	private static Vector<GraphicComponent> panelToCanvasGC = new Vector <GraphicComponent>();
	public static void addGCToPanelToCanvas(GraphicComponent gc) {panelToCanvasGC.add(gc);}
	public static void addAllToPanelToCanvas(Vector<GraphicComponent> shapeDatas) {panelToCanvasGC.addAll(shapeDatas);}
	public static void clearPanelToCanvas() {panelToCanvasGC.clear();}
	public static Vector<GraphicComponent> getPanelToCanvas() {return panelToCanvasGC;}
}
