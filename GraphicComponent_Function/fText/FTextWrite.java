package fText;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import calculation.AffineMath;
import zStuff_Function.AFunction;
import zStuff_Text.FTextWrite_Stuff;

public class FTextWrite extends AFunction{
	private static final long serialVersionUID = -1587056258171782344L;
	
	static final String myCaret = "|";
	
	int scaletextSize = 50;
	double textYIntervalFactor = 1.1;
	Color textColor = Color.white;
	
	public enum Arrange{LEFTUP, CENTER}
	
	public void realPaint(Graphics2D g) {
		if(master.getText().length()>0||(master.isSelected()&&FTextWrite_Stuff.isTextEditing())) {
			try {
				textColor = master.getTextColor();
				if(master.getTempTextColor()!=null) {
					textColor = master.getTempTextColor();
				}
				g.setColor(textColor);
				
				scaletextSize = (int) (master.getTextSize());
				
				g.setFont(new Font(null, Font.BOLD, scaletextSize));
				
				String rawText = master.getText();
				if(master.isSelected()&&FTextWrite_Stuff.isTextEditing()) {
					if(rawText.equals("")) {rawText = myCaret;}//if no text -> caret
					else {
						rawText = rawText.replace(myCaret, "");//if text -> move caret
						String backSide = rawText.substring(FTextWrite_Stuff.getTextEditArea().getCaretPosition());
						String frontSide = rawText.substring(0, FTextWrite_Stuff.getTextEditArea().getCaretPosition());
						String cookText = frontSide + myCaret + backSide;
						rawText = cookText;
					}
				}
				
				Vector<Shape> textShape = new Vector<Shape>();
				for(String txtDivideByEndter : rawText.split((char)10+"")) {//split text by ENTER(10)
					GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), txtDivideByEndter);
					if(gv.getOutline().getBounds().getHeight()!=0) {textShape.add(gv.getOutline());}
					else {textShape.add(new Rectangle(0,0,0,scaletextSize));}
				}
				
				if(master.getTextArrange() == Arrange.CENTER) {centerPaint(g, textShape);}
				else if(master.getTextArrange() == Arrange.LEFTUP) {leftUpPaint(g, textShape);}
			}catch(Exception e) {System.out.println("FTextWrite Err");}
		}
	}
	
	private void leftUpPaint(Graphics2D g, Vector<Shape> textShape) {
		Rectangle2D masterBeforeRotateBorder = AffineMath.getRotateShape(master.getShape(), -master.getAngle(), master.getCenter()).getBounds2D();
		double gap = textShape.get(0).getBounds().getHeight()/2;
		double startX = masterBeforeRotateBorder.getX() + gap; 
		double startY = masterBeforeRotateBorder.getY() + gap;
		
		for(Shape nowTextShape : textShape) {
			Rectangle2D nowBound = nowTextShape.getBounds2D();
			
			AffineTransform at = new AffineTransform();
			startY += nowBound.getHeight();
			at.translate(startX, startY);//MY radio. 왜 이런지는 모르겠음. 텍스트가 원래 이상하게 그려지긴 했음...
			startY += nowBound.getHeight()*textYIntervalFactor/2;
			nowTextShape = at.createTransformedShape(nowTextShape);
			
			at = new AffineTransform();//한번에 적용이 안됨.
			at.setToRotation(Math.toRadians(master.getAngle()), masterBeforeRotateBorder.getCenterX(), masterBeforeRotateBorder.getCenterY());
			nowTextShape = at.createTransformedShape(nowTextShape);
			
			g.fill(nowTextShape);
		}
	}

	private void centerPaint(Graphics2D g, Vector<Shape> textShape) {
		Rectangle2D masterBeforeRotateBorder = AffineMath.getRotateShape(master.getShape(), -master.getAngle(), master.getCenter()).getBounds2D();
		double sumH = 0;
		for(Shape nowTextShape : textShape) {
			sumH+=nowTextShape.getBounds2D().getHeight();
		}
		double startY = masterBeforeRotateBorder.getY() + (masterBeforeRotateBorder.getHeight() - sumH*textYIntervalFactor)/2;
		double myY = startY;
		
		for(Shape nowTextShape : textShape) {
			Rectangle2D nowBound = nowTextShape.getBounds2D();
			double transX = masterBeforeRotateBorder.getX() + (masterBeforeRotateBorder.getWidth() - nowBound.getWidth())/2;
			double transY = myY;
			AffineTransform at = new AffineTransform();
			at.translate(transX-scaletextSize*4/50, transY+scaletextSize/5*4);//MY radio. 왜 이런지는 모르겠음. 텍스트가 원래 이상하게 그려지긴 했음...
			nowTextShape = at.createTransformedShape(nowTextShape);
			
			at = new AffineTransform();//한번에 적용이 안됨.
			at.setToRotation(Math.toRadians(master.getAngle()), masterBeforeRotateBorder.getCenterX(), masterBeforeRotateBorder.getCenterY());
			nowTextShape = at.createTransformedShape(nowTextShape);
			
			myY+=nowBound.getHeight()*textYIntervalFactor;
			
			g.fill(nowTextShape);
		}
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {
			FTextWrite_Stuff.setTextForTextEdit(master.getText());
			FTextWrite_Stuff.giveFocusToTextEditArea();
			FTextWrite_Stuff.setTextEditing(true);
		}
	}
}
