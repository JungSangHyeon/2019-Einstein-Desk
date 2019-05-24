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
	
//	String text = "";
	Shape textShape;
	double textSize = 50;// 50에 4, 40 / 
	
	private static JTextArea textEditArea = new JTextArea(), focusArea = new JTextArea();
	public static JTextArea getTextEditArea() {return textEditArea;}
	public static JTextArea getFocusArea() {return focusArea;}
	public static boolean isTextEditAreaFocusOwner() {return textEditArea.isFocusOwner();}
	public static void giveFocusToTextEditArea() {textEditArea.requestFocus();}
	public static void removeFocusTextEditArea() {focusArea.requestFocus();}
	public static void setTextForTextEdit(String text) {textEditArea.setText(text);}
	
	public Paint_TextType() {
		int size = 200;
		focusArea.setBackground(Color.cyan);
		textEditArea.setBackground(Color.green);
		
		focusArea.setBounds(1920-size*2,1080-size,size,size);
		textEditArea.setBounds(1920-size,1080-size,size,size);
		textEditArea.addCaretListener(new CaretHadler());
		textEditArea.addFocusListener(new focusHandler());
	}
	
	public class focusHandler implements FocusListener{
		@Override
		public void focusGained(FocusEvent e) {
			
		}
		@Override
		public void focusLost(FocusEvent e) {
			textEditing = false;
		}
	}
	public class CaretHadler implements CaretListener{
		@Override
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
	
	double textYIntervalFactor = 1.1;
	
	public void paintComponent(Graphics2D g, Shape shape) {
		try {
			g.setFont(new Font(null, Font.BOLD, (int)textSize));
			
			Vector<Shape> textShape = new Vector<Shape>();
			
			String rawText = master.getText();
			if(master.isSelected()) {
				if(textEditing) {
					if(rawText.equals("")) {
						rawText = "|";
					}else {
						rawText = rawText.replace("|", "");
						String backSide = rawText.substring(textEditArea.getCaretPosition());
						String frontSide = rawText.substring(0, textEditArea.getCaretPosition());
						String cookText = frontSide + "|" + backSide;
						rawText = cookText;
					}
				}
			}
			
			for(String txtDivideByEndter : rawText.split((char)10+"")) {
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
				at.translate(transX-textSize*4/50, transY+textSize/5*4);
				nowTextShape = at.createTransformedShape(nowTextShape);
				
				at = new AffineTransform();
				at.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
				nowTextShape = at.createTransformedShape(nowTextShape);
				
				myY+=nowBound.getHeight()*textYIntervalFactor;//1.1은 간격 넓히기를 위함.
				
				g.setColor(Color.white);
				g.fill(nowTextShape);
			}
		}catch(Exception e) {System.out.println("text error");}
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {
			setTextForTextEdit(master.getText());// |=caret
			giveFocusToTextEditArea();
			textEditing = true;
		}
	}
	boolean textEditing = false;
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
