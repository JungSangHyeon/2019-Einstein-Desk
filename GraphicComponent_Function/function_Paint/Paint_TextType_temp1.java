package function_Paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import moveAndZoom.DrawingPanelMoveAndZoom;
import zFunction_Stuff.AFunction;

public class Paint_TextType_temp1 extends AFunction{
	private static final long serialVersionUID = -1587056258171782344L;
	
	String text = "ででででででで";
	Shape textShape;
	double textSize = 50;
	
	private Rectangle2D getBeforeRotateBorder() {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		return at.createTransformedShape(master.getShape()).getBounds2D();
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		try {
			double scale = DrawingPanelMoveAndZoom.getScale();
			double size = (int) (textSize/scale), hori = size, verti = size*2;
			Rectangle2D masterBorder = getBeforeRotateBorder();
			for(String w : text.split((char)10+"")) {//廃越切梢 級嬢姶.
				if(32==w.charAt(0)) {hori+=size*0.4;}//0.4 = space factor
				if(10==w.charAt(0)) {verti+=size; hori = size; }//匝角沿. enter
				else {
					if(masterBorder.getWidth()<hori+10/scale+size) {verti+=size; hori = size;}//10 = 角嬢亜澗暗 森雌馬澗 葵
					
					g.setFont(new Font(null, Font.BOLD, (int)size));
					
					GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), "fuck");
					
					AffineTransform at = new AffineTransform();
					at.translate(masterBorder.getX() + hori, masterBorder.getY() + verti);
					
					Shape s1 = at.createTransformedShape(gv.getOutline());
					
					AffineTransform at2 = new AffineTransform();//重奄馬惟亀, 廃腰拭 照喫.
					at2.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
					
					Shape s2 = at2.createTransformedShape(s1);
					
					g.setColor(Color.WHITE);
					g.fill(s2);
					
					if(124!=w.charAt(0)) {hori+=at.createTransformedShape(gv.getOutline()).getBounds2D().getWidth()+4/scale;}//textGapLevel = 4//carlet 焼艦檎 砧臆 希敗.
				}
				
				double dw = masterBorder.getWidth() - hori;
				double dh = masterBorder.getHeight() - verti;
				
				
			}
		}catch(Exception e) {System.out.println("text error");}
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
