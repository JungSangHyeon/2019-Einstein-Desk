package zStuff_Text;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import component_Stuff.GraphicComponent;
import data.GCStorage_Selected;

public class FTextWrite_Stuff {

static boolean textEditing = false;
	
	private static JTextArea textEditArea = new JTextArea(), focusArea = new JTextArea();
	public static JTextArea getTextEditArea() {return textEditArea;}
	public static JTextArea getFocusArea() {return focusArea;}
	
	public static boolean isTextEditing() {return textEditing;}
	public static boolean isTextEditAreaFocusOwner() {return textEditArea.isFocusOwner();}
	public static void setTextEditing(boolean boo) {textEditing=boo;}
	public static void giveFocusToTextEditArea() {textEditArea.requestFocus();}
	public static void removeFocusTextEditArea() {focusArea.requestFocus();}
	public static void setTextForTextEdit(String text) {textEditArea.setText(text);}
	
	public static void setting() {
		int size = 1;
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
	
}
