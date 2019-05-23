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
	
	String text = "������ ����Ʃ��Ʃ";
	Shape textShape;
	double textSize = 200;// 50�� 4, 40 / 
	
	private Rectangle2D getBeforeRotateBorder() {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		return at.createTransformedShape(master.getShape()).getBounds2D();
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		try {
			//"asd asdasdasd enter asdasd dddd"
			//���� ������ ������. //text.split((char)10+"")
			//���� �׸�� ����� ���Ϳ� �����Ѵ�.
			
			//�׸𸶴� w�� ����Ͽ� �������� �߽� w�� ��ġ�Ѵ�,
			
			//��� �׸��� ���̸� ���ؼ�, ������ ����Ʈ�� ����, ��ü�� ��ŸƮ ���̸� ���Ѵ�.
			// �� ������, �׸��µ�, ��ŸƮ����, �״����� ��ŸƮ���� + ����1 ... �̷��� �Ͽ� �׸���.
			
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
//			for(String w : text.split((char)10+"")) {//�ѱ��ھ� ��.
//				if(32==w.charAt(0)) {hori+=size*0.4;}//0.4 = space factor
//				if(10==w.charAt(0)) {verti+=size; hori = size; }//�ٳѱ�. enter
//				else {
//					if(masterBorder.getWidth()<hori+10/scale+size) {verti+=size; hori = size;}//10 = �Ѿ�°� �����ϴ� ��
//					
//					g.setFont(new Font(null, Font.BOLD, (int)size));
//					
//					GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), "Nice to See you �ȳ�");
//					
//					AffineTransform at = new AffineTransform();
//					at.translate(masterBorder.getX() + hori, masterBorder.getY() + verti);
//					
//					Shape s1 = at.createTransformedShape(gv.getOutline());
//					
//					AffineTransform at2 = new AffineTransform();//�ű��ϰԵ�, �ѹ��� �ȵ�.
//					at2.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
//					
//					Shape s2 = at2.createTransformedShape(s1);
//
//					g.setColor(Color.GREEN);
//					g.fill(s2.getBounds());
//					g.setColor(Color.WHITE);
//					g.fill(s2);
//					verti+=size;
//					if(124!=w.charAt(0)) {hori+=at.createTransformedShape(gv.getOutline()).getBounds2D().getWidth()+4/scale;}//textGapLevel = 4//carlet �ƴϸ� �β� ����.
//				}
//			}
		}catch(Exception e) {System.out.println("text error");}
		
//		try {
//			double scale = DrawingPanelMoveAndZoom.getScale();
//			double size = (int) (textSize/scale), hori = size, verti = size*2;
//			Rectangle2D masterBorder = getBeforeRotateBorder();
//			for(String w : text.split((char)10+"")) {//�ѱ��ھ� ��.
//				if(32==w.charAt(0)) {hori+=size*0.4;}//0.4 = space factor
//				if(10==w.charAt(0)) {verti+=size; hori = size; }//�ٳѱ�. enter
//				else {
//					if(masterBorder.getWidth()<hori+10/scale+size) {verti+=size; hori = size;}//10 = �Ѿ�°� �����ϴ� ��
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
//					AffineTransform at2 = new AffineTransform();//�ű��ϰԵ�, �ѹ��� �ȵ�.
//					at2.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
//					
//					Shape s2 = at2.createTransformedShape(s1);
//					
//					g.setColor(Color.WHITE);
//					g.fill(s2);
//					
//					if(124!=w.charAt(0)) {hori+=at.createTransformedShape(gv.getOutline()).getBounds2D().getWidth()+4/scale;}//textGapLevel = 4//carlet �ƴϸ� �β� ����.
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
