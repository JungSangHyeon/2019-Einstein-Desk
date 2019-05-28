package function_Paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import zFunction_Stuff.AFunction;

public class Paint_TextWrite extends AFunction{
	private static final long serialVersionUID = -1587056258171782344L;
	
	Color textColor = Color.white;
	String myCaret = "|";
	double textSize = 50;
	double textYIntervalFactor = 1.1;
	
	Shape textShape;
	
	private Rectangle2D getBeforeRotateBorder() {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		return at.createTransformedShape(master.getShape()).getBounds2D();
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		try {
			Font startFont = g.getFont();
			g.setFont(new Font(null, Font.BOLD, (int)textSize));
			Vector<Shape> textShape = new Vector<Shape>();
			String rawText = master.getText();
			if(master.isSelected()) {
				if(Paint_TextWrite_Stuff.isTextEditing()) {
					if(rawText.equals("")) {rawText = myCaret;}
					else {
						rawText = rawText.replace(myCaret, "");
						String backSide = rawText.substring(Paint_TextWrite_Stuff.getTextEditArea().getCaretPosition());
						String frontSide = rawText.substring(0, Paint_TextWrite_Stuff.getTextEditArea().getCaretPosition());
						String cookText = frontSide + myCaret + backSide;
						rawText = cookText;
					}
				}
			}
			for(String txtDivideByEndter : rawText.split((char)10+"")) {//ENTER
				GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), txtDivideByEndter);
				textShape.add(gv.getOutline());
			}
			Rectangle2D masterBorder = getBeforeRotateBorder();
			double sumH = 0;
			for(Shape nowTextShape : textShape) {
				sumH+=nowTextShape.getBounds2D().getHeight();
			}
			double startY = masterBorder.getY() + (masterBorder.getHeight() - sumH*textYIntervalFactor)/2;
			double myY = startY;
			for(Shape nowTextShape : textShape) {
				Rectangle2D nowBound = nowTextShape.getBounds2D();
				double transX = masterBorder.getX() + (masterBorder.getWidth() - nowBound.getWidth())/2;
				double transY = myY;
				AffineTransform at = new AffineTransform();
				at.translate(transX-textSize*4/50, transY+textSize/5*4);//MY radio. 왜 이런지는 모르겠음. 텍스트가 원래 이상하게 그려지긴 했음...
				nowTextShape = at.createTransformedShape(nowTextShape);
				
				at = new AffineTransform();
				at.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
				nowTextShape = at.createTransformedShape(nowTextShape);
				
				myY+=nowBound.getHeight()*textYIntervalFactor;
				
				g.setColor(textColor);
				g.fill(nowTextShape);
			}
			g.setFont(startFont);
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {
			Paint_TextWrite_Stuff.setTextForTextEdit(master.getText());
			Paint_TextWrite_Stuff.giveFocusToTextEditArea();
			Paint_TextWrite_Stuff.setTextEditing(true);
		}
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void processSelectEvent(boolean selected) {}
}
