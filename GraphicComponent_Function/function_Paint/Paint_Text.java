package function_Paint;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import zFunction_Stuff.ANameAndImagePainter;
import zFunction_Stuff.ImgStorage;

public class Paint_Text extends ANameAndImagePainter {//D&D 안함
	private static final long serialVersionUID = -7129480604093083071L;
	
	final static float textWRadio = 3.5f, textHRadio = 1.5f, textYFactor = 0.357f;//시작 좌표+ 네모의 높이 /2 + 글씨크기 *textYFactor
	final static int imgLeftGap = 2, imgRightGap = 4, imgUpDownGap = 2;

	public Paint_Text(String name, String fileAddress) {
		this.setName(name);
		this.setImg(fileAddress);
	}

	public void paintComponent(Graphics2D g, Shape shape) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setClip(shape);
		Rectangle rect = shape.getBounds();
		int rectX = rect.x, rectY= rect.y, rectW = rect.width, rectH = rect.height;
		g2d.setColor(nameColor);
		BufferedImage img = ImgStorage.getImage(imageIndex) ;
		if(rect.getWidth()>rect.getHeight()) {
			if (img != null) {g2d.drawImage(img, rectX+imgLeftGap, rectY+imgUpDownGap, rectH-imgLeftGap*2, rectH-imgUpDownGap*2, null);}
			if(!name.equals("")) {
				float gap = rectW - (imgLeftGap+rectH-imgUpDownGap*4);
				float size =g2d.getFontMetrics(new Font(null, Font.PLAIN, (int)nameSize)).stringWidth(name);
				if(gap<size) {nameSize = gap/textWRadio;}
				else {nameSize = Math.min(rectH/textHRadio, gap/textWRadio) ;}
				g2d.setFont(new Font(null, Font.PLAIN, (int)nameSize));
				g2d.drawString(name, rectX+imgLeftGap+rectH+imgRightGap, (int) (rectY+rectH/2 + nameSize*textYFactor) );
			}
		}
		g2d.setClip(null);
	}
}
