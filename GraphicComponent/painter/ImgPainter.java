package painter;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import stuff_Component.AComponentPainter;
import stuff_Component.ImgStorage;

public class ImgPainter extends AComponentPainter {
	private static final long serialVersionUID = -7941746680321916676L;
	
	final static int minTextSize = 10;
	final static int textWRadio = 8;
	final static int textHRadio = 4;
	
	public ImgPainter(String name, String fileAddress) {super(name, fileAddress);}

	public void paintComponent(Graphics g, Shape shape) {
		Rectangle rect = shape.getBounds();
		int rectX = rect.x, rectY= rect.y, rectW = rect.width, rectH = rect.height;
		
		BufferedImage img = ImgStorage.getImage(imageIndex) ;
		if (img != null) {
			float imgW = img.getWidth(), imgH = img.getHeight(), radio = 0;
			g.setClip(shape);
			radio = (imgH*rectW/imgW>rectH)? rectW/imgW : rectH/imgH;
			imgW *= radio; imgH *= radio;
			g.drawImage(img, rectX+(int) (rectW / 2 - imgW / 2), rectY+(int) (rectH / 2 - imgH / 2), (int)imgW, (int) imgH,null);
			g.setClip(null);
		}
		
		g.setColor(nameColor);
		float size =g.getFontMetrics(new Font(null, Font.PLAIN, (int)nameSize)).stringWidth(name);
		if(rectW<size) {nameSize = rectW/textWRadio;}
		else {nameSize = Math.min(rectH/textHRadio, rectW/textWRadio) ;}
		if(nameSize>minTextSize) {
			g.setFont(new Font(null, Font.PLAIN, (int)nameSize));
			g.drawString(name, rectX+TextLocateX, rectY+rectH+TextLocateY);
		}
	}
	
}
