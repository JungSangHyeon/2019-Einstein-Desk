package dragAndDrop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import component_Stuff.GraphicComponent;

@SuppressWarnings("serial")
public class dadFrame extends JFrame {

	GraphicComponent gc;
	
	public dadFrame() {
		this.setUndecorated(true);
		this.setBackground(new Color(100,100,100,150));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		gc.paint((Graphics2D)g);
	}

	public class mouseHandler implements MouseMotionListener{
		public void mouseDragged(MouseEvent arg0) {
		}
		public void mouseMoved(MouseEvent e) {
		}
	} 
	
}
