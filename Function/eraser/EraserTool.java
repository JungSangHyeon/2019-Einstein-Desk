package eraser;

import java.awt.event.MouseEvent;
import java.util.Vector;

import canvasMoveAndZoom.GlobalAT;
import redoUndo.RedoUndo;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Tool.ATool;

public class EraserTool extends ATool{
	private static final long serialVersionUID = 1746179459505183985L;

	public void mousePressed(MouseEvent e) {eraseSelected(e);}
	public void mouseDragged(MouseEvent e) {eraseSelected(e);}
	
	private void eraseSelected(MouseEvent e) {
		Vector<GraphicComponent> Components = GCStorage_Normal.getGCVector();
		for(int i=Components.size()-1; i>-1; i--) {
			if(Components.get(i).getAShape().thisGCIsSelected(Components.get(i), GlobalAT.transformPoint(e.getPoint()))) {
				GCStorage_Normal.removeGC(Components.get(i));
			}
		}
	}
	public void mouseReleased(MouseEvent e) {RedoUndo.saveNowInHistory();}
}
