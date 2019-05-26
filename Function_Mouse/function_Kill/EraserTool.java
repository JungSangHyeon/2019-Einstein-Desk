package function_Kill;

import java.awt.event.MouseEvent;
import java.util.Vector;

import component_Stuff.GraphicComponent;
import data.GCStorage_Normal;
import doUndo.RedoUndo;
import function_Stuff.ATool;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class EraserTool extends ATool{
	private static final long serialVersionUID = 1746179459505183985L;

	public void mousePressed(MouseEvent e) {eraseSelected(e);}
	public void mouseDragged(MouseEvent e) {eraseSelected(e);}
	
	private void eraseSelected(MouseEvent e) {
		Vector<GraphicComponent> Components = GCStorage_Normal.getGCVector();
		for(int i=Components.size()-1; i>-1; i--) {
			if(Components.get(i).getAShape().isSelected(Components.get(i), DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {
				GCStorage_Normal.removeGC(Components.get(i));
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {RedoUndo.saveNowInHistory();}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseWheelMoved(MouseEvent e) {}
}
