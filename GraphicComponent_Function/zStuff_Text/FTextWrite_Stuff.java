package zStuff_Text;

import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import PDR_2P_Shape.StarNShape;
import fText.FTextWrite.Arrange;
import redoUndo.RedoUndo;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class FTextWrite_Stuff {

static boolean textEditing = false;
	
	private static JTextArea textEditArea = new JTextArea(), focusArea = new JTextArea();
	public static JTextArea getTextEditArea() {return textEditArea;}
	public static JTextArea getFocusArea() {return focusArea;}
	
	public static boolean isTextEditing() {return textEditing;}
	public static boolean isTextEditAreaFocusOwner() {return textEditArea.isFocusOwner();}
	public static void setTextEditing(boolean boo) {textEditing=boo;}
	public static void giveFocusToTextEditArea() {textEditArea.requestFocus();}
	public static void removeFocusTextEditArea() {focusArea.requestFocus();RedoUndo.saveNowInHistory(); processOrder();}
	public static void setTextForTextEdit(String text) {textEditArea.setText(text);}
	
	public static void setting() {
		int size = 1;
//		focusArea.setBounds(100,100,200,200);
//		textEditArea.setBounds(300,100,200,200);
		focusArea.setBounds(1920-size*2,1080-size,size,size);
		textEditArea.setBounds(1920-size,1080-size,size,size);
		textEditArea.addCaretListener(new CaretHadler());
		textEditArea.addFocusListener(new focusHandler());
	}
	
	
	public static class focusHandler implements FocusListener{
		public void focusGained(FocusEvent e) {}
		public void focusLost(FocusEvent e) {textEditing = false;}
	}
	
	public static class CaretHadler implements CaretListener{
		public void caretUpdate(CaretEvent e) {
			for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
				gc.setText(textEditArea.getText());
			}
		}
	}
	
	private static void processOrder() {
		for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
			try {
				String order = gc.getText();
				if(order.split("\\(")[0].equals("this.setTextSize")) {
					int size =  Integer.parseInt(order.split("\\(")[1].split("\\)")[0]);
					gc.setTextSize(size);
					gc.setText("");
				}else if(order.split("\\(")[0].equals("this.setTextArrange")) {
					String mode =  order.split("\\(")[1].split("\\)")[0];
					if(mode.equals("LEFTUP")) {
						gc.setTextArrange(Arrange.LEFTUP);
						gc.setText("");
					}else if(mode.equals("CENTER")){
						gc.setTextArrange(Arrange.CENTER);
						gc.setText("");
					}
				}else if(order.split("\\(")[0].equals("this.setBorderThick")) {
					int thick =  Integer.parseInt(order.split("\\(")[1].split("\\)")[0]);
					gc.setborderThick(thick);
					gc.setText("");
				}else if(order.split("\\(")[0].equals("this.setShapeAsStar")) {
					int spike =  Integer.parseInt(order.split("\\(")[1].split("\\)")[0]);
					Rectangle rect = gc.getShape().getBounds();
					if(spike>1) {
						gc.setShape(StarNShape.makePath((float)rect.getX(), (float)rect.getY(), (float)rect.getWidth(), (float)rect.getHeight(), spike));
					}
					gc.setText("");
				}
			}catch(Exception e) {System.out.println("¸í·É¾î Àß¸øÃÆ¾û"); gc.setText("");}
		}
	}
}
