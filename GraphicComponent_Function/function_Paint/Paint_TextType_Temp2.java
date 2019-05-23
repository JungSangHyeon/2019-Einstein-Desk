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

import moveAndZoom.DrawingPanelMoveAndZoom;
import zFunction_Stuff.AFunction;

public class Paint_TextType_Temp2 extends AFunction{
	private static final long serialVersionUID = -1587056258171782344L;
	
	String text = "헬로헬로 나스튜미튜";
	Shape textShape;
	double textSize = 200;// 50에 4, 40 / 
	
	private Rectangle2D getBeforeRotateBorder() {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		return at.createTransformedShape(master.getShape()).getBounds2D();
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		try {
			//"asd asdasdasd enter asdasd dddd"
			//엔터 단위로 나눈다. //text.split((char)10+"")
			//각각 네모로 만들어 벡터에 저장한다.
			
			//네모마다 w를 계산하여 마스터의 중심 w로 배치한다,
			
			//모든 네모의 높이를 더해서, 마스터 하이트를 통해, 전체의 스타트 와이를 구한다.
			// 맨 위부터, 그리는데, 스타트와이, 그다음은 스타트와이 + 높이1 ... 이렇게 하여 그린다.
			
			g.setFont(new Font(null, Font.BOLD, (int)textSize));
			
			Vector<Shape> textShape = new Vector<Shape>();
			
			for(String txtDivideByEndter : text.split((char)10+"")) {
				GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), txtDivideByEndter);
				textShape.add(gv.getOutline());
			}
			
			Rectangle2D masterBorder = getBeforeRotateBorder();
			
			double sumH =0;
			for(Shape nowTextShape : textShape) {
				sumH+=nowTextShape.getBounds2D().getHeight();
			}
			double startY = masterBorder.getY() + (masterBorder.getHeight() - sumH)/2;
			
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
				
				
				myY+=nowBound.getHeight();
				
//				g.setColor(Color.green);
//				g.fill(nowTextShape.getBounds());
				g.setColor(Color.white);
				g.fill(nowTextShape);
			}
			
			
//			double scale = DrawingPanelMoveAndZoom.getScale();
//			double size = (int) (textSize/scale), hori = 0, verti = 0;
//			Rectangle2D masterBorder = getBeforeRotateBorder();
//			for(String w : text.split((char)10+"")) {//한글자씩 들어감.
//				if(32==w.charAt(0)) {hori+=size*0.4;}//0.4 = space factor
//				if(10==w.charAt(0)) {verti+=size; hori = size; }//줄넘김. enter
//				else {
//					if(masterBorder.getWidth()<hori+10/scale+size) {verti+=size; hori = size;}//10 = 넘어가는거 예상하는 값
//					
//					g.setFont(new Font(null, Font.BOLD, (int)size));
//					
//					GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), "Nice to See you 안녕");
//					
//					AffineTransform at = new AffineTransform();
//					at.translate(masterBorder.getX() + hori, masterBorder.getY() + verti);
//					
//					Shape s1 = at.createTransformedShape(gv.getOutline());
//					
//					AffineTransform at2 = new AffineTransform();//신기하게도, 한번에 안됨.
//					at2.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
//					
//					Shape s2 = at2.createTransformedShape(s1);
//
//					g.setColor(Color.GREEN);
//					g.fill(s2.getBounds());
//					g.setColor(Color.WHITE);
//					g.fill(s2);
//					verti+=size;
//					if(124!=w.charAt(0)) {hori+=at.createTransformedShape(gv.getOutline()).getBounds2D().getWidth()+4/scale;}//textGapLevel = 4//carlet 아니면 두께 더함.
//				}
//			}
		}catch(Exception e) {System.out.println("text error");}
		
//		try {
//			double scale = DrawingPanelMoveAndZoom.getScale();
//			double size = (int) (textSize/scale), hori = size, verti = size*2;
//			Rectangle2D masterBorder = getBeforeRotateBorder();
//			for(String w : text.split((char)10+"")) {//한글자씩 들어감.
//				if(32==w.charAt(0)) {hori+=size*0.4;}//0.4 = space factor
//				if(10==w.charAt(0)) {verti+=size; hori = size; }//줄넘김. enter
//				else {
//					if(masterBorder.getWidth()<hori+10/scale+size) {verti+=size; hori = size;}//10 = 넘어가는거 예상하는 값
//					
//					g.setFont(new Font(null, Font.BOLD, (int)size));
//					
//					GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), w);
//					
//					AffineTransform at = new AffineTransform();
//					at.translate(masterBorder.getX() + hori, masterBorder.getY() + verti);
//					
//					Shape s1 = at.createTransformedShape(gv.getOutline());
//					
//					AffineTransform at2 = new AffineTransform();//신기하게도, 한번에 안됨.
//					at2.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
//					
//					Shape s2 = at2.createTransformedShape(s1);
//					
//					g.setColor(Color.WHITE);
//					g.fill(s2);
//					
//					if(124!=w.charAt(0)) {hori+=at.createTransformedShape(gv.getOutline()).getBounds2D().getWidth()+4/scale;}//textGapLevel = 4//carlet 아니면 두께 더함.
//				}
//			}
//		}catch(Exception e) {System.out.println("text error");}
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
