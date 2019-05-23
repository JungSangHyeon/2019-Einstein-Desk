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

import global.TA;
import zFunction_Stuff.AFunction;

public class Paint_TextType extends AFunction{
	private static final long serialVersionUID = -1587056258171782344L;
	
	String text = "헬로헬로\n나스튜미튜";
	Shape textShape;
	double textSize = 50;// 50에 4, 40 / 
	
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
			
			for(String txtDivideByEndter : TA.ta.getText().split((char)10+"")) {
				GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), txtDivideByEndter);
				textShape.add(gv.getOutline());
			}
			
			Rectangle2D masterBorder = getBeforeRotateBorder();
			
			double sumH = 0;
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
				
				myY+=nowBound.getHeight();//1.1은 간격 넓히기를 위함.
				
				g.setColor(Color.white);
				g.fill(nowTextShape);
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
