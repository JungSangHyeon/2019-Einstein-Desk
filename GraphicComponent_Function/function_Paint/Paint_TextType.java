package function_Paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import zFunction_Stuff.AFunction;

public class Paint_TextType extends AFunction{
	private static final long serialVersionUID = -1587056258171782344L;
	
	Color textColor = Color.white;
	String myCaret = "|";
	double textSize = 50;
	double textYIntervalFactor = 1.1;
	
	Shape textShape;
	static boolean textEditing = false;
	
	
	private static JTextArea textEditArea = new JTextArea(), focusArea = new JTextArea();
	public static JTextArea getTextEditArea() {return textEditArea;}
	public static JTextArea getFocusArea() {return focusArea;}
	
	public static boolean isTextEditAreaFocusOwner() {return textEditArea.isFocusOwner();}
	public static void giveFocusToTextEditArea() {textEditArea.requestFocus();}
	public static void removeFocusTextEditArea() {focusArea.requestFocus();}
	public static void setTextForTextEdit(String text) {textEditArea.setText(text);}
	
	public Paint_TextType() {
		int size = 100;
		focusArea.setBounds(1920-size*2,1080-size,size,size);
		textEditArea.setBounds(1920-size,1080-size,size,size);
		textEditArea.addCaretListener(new CaretHadler());
		textEditArea.addFocusListener(new focusHandler());
	}
	
	public class focusHandler implements FocusListener{
		public void focusGained(FocusEvent e) {}
		public void focusLost(FocusEvent e) {textEditing = false;}
	}
	
	public class CaretHadler implements CaretListener{
		public void caretUpdate(CaretEvent e) {
			for(GraphicComponent gc : GCStorage.getSelectedGCVector()) {
				gc.setText(Paint_TextType.getTextEditArea().getText());
			}
		}
	}
	
	private Rectangle2D getBeforeRotateBorder() {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		return at.createTransformedShape(master.getShape()).getBounds2D();
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		try {
			g.setFont(new Font(null, Font.BOLD, (int)textSize));
			Vector<Shape> textShape = new Vector<Shape>();
			String rawText = master.getText();
			if(master.isSelected()) {
				if(textEditing) {
					if(rawText.equals("")) {rawText = myCaret;}
					else {
						rawText = rawText.replace(myCaret, "");
						String backSide = rawText.substring(textEditArea.getCaretPosition());
						String frontSide = rawText.substring(0, textEditArea.getCaretPosition());
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
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {
			setTextForTextEdit(master.getText());
			giveFocusToTextEditArea();
			textEditing = true;
		}
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
