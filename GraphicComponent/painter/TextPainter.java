package painter;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import stuff_Component.AComponentPainter;
import stuff_Component.ImgStorage;

public class TextPainter extends AComponentPainter {
	private static final long serialVersionUID = -7129480604093083071L;
	
	final static int minTextSize = 15;
	final static float textWRadio = 3.5f;
	final static float textHRadio = 1.5f;
	final static int imgLeftGap = 2;
	final static int imgRightGap = 4;
	final static int imgUpDownGap = 2;
	final static float textYFactor = 0.357f;//시작 좌표+ 네모의 높이 /2 + 글씨크기 *textYFactor

	public TextPainter(String name, String fileAddress) {super(name, fileAddress);}

	public void paintComponent(Graphics g, Shape shape) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setClip(shape);
		Rectangle rect = shape.getBounds();
		int rectX = rect.x, rectY= rect.y, rectW = rect.width, rectH = rect.height;
		g2d.setColor(nameColor);
		
		BufferedImage img = ImgStorage.getImage(imageIndex) ;
		if(rect.getWidth()<rect.getHeight()) {
			if (img != null) {g2d.drawImage(img, rectX+imgLeftGap, rectY+imgUpDownGap, rectW-imgLeftGap*2, rectW-imgUpDownGap*2, null);}
			float gap = rectH - (imgUpDownGap+rectW-imgUpDownGap*2);
			nameSize = rectW/3;
			if(nameSize>gap*1.1) {nameSize = (float) (gap*1.1);}
			if(nameSize>minTextSize) {
				g2d.setFont(new Font(null, Font.PLAIN, (int)nameSize));
				g2d.drawString(name, rectX+imgLeftGap, (int) (rectY+rectW +( nameSize*textYFactor*2+imgUpDownGap) ));
			}
		}else {
			if (img != null) {g2d.drawImage(img, rectX+imgLeftGap, rectY+imgUpDownGap, rectH-imgLeftGap*2, rectH-imgUpDownGap*2, null);}
			float gap = rectW - (imgLeftGap+rectH-imgUpDownGap*4);
			float size =g2d.getFontMetrics(new Font(null, Font.PLAIN, (int)nameSize)).stringWidth(name);
			if(gap<size) {nameSize = gap/textWRadio;}
			else {nameSize = Math.min(rectH/textHRadio, gap/textWRadio) ;}
			if(nameSize>minTextSize) {
				g2d.setFont(new Font(null, Font.PLAIN, (int)nameSize));
				g2d.drawString(name, rectX+imgLeftGap+rectH+imgRightGap, (int) (rectY+rectH/2 + nameSize*textYFactor) );
			}
		}
		g2d.setClip(null);
	}
	
}
