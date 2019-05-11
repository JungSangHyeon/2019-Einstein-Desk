package painter;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import moveAndZoom.DrawingPanelMoveAndZoom;
import stuff_Component.AComponentPainter;
import stuff_Component.ImgStorage;

public class ImgPainter extends AComponentPainter {
	private static final long serialVersionUID = -7941746680321916676L;
	
	final static int minTextSize = 10, textWRadio = 8, textHRadio = 4, textLocationXFactor = 40, textLocationYFactor = 20;
	
	public ImgPainter(String name, String fileAddress) {super(name, fileAddress);}

	public void paintComponent(Graphics g, Shape shape) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setClip(shape);
		Rectangle2D rect = shape.getBounds2D();
		double rectX = rect.getX(), rectY= rect.getY(), rectW = rect.getWidth(), rectH = rect.getHeight();
		
		BufferedImage img = ImgStorage.getImage(imageIndex) ;
		if (img != null) {
			double imgW = img.getWidth(), imgH = img.getHeight(), radio = 0;
			radio = (imgH*rectW/imgW>rectH)? rectW/imgW : rectH/imgH;
			imgW *= radio; imgH *= radio;
			
			AffineTransform t = new AffineTransform();//세밀하게? 그리는법. 픽셀보다 작은단위? 모라해야 합네까
			t.translate(rectX+rectW / 2 - imgW / 2, rectY+rectH / 2 - imgH / 2);
			t.scale(radio, radio);
			g2d.drawImage(img, t, null);
		}
		
		g2d.setColor(nameColor);
		float size =g2d.getFontMetrics(new Font(null, Font.PLAIN, (int)nameSize)).stringWidth(name);
		if(rectW<size) {nameSize = rectW/textWRadio;}
		else {nameSize = Math.min(rectH/textHRadio, rectW/textWRadio) ;}
		if(nameSize*DrawingPanelMoveAndZoom.getScale()>minTextSize) {
			g2d.setFont(new Font(null, Font.PLAIN, (int)nameSize));
			g2d.drawString(name, (float)(rectX+rectW/textLocationXFactor), (float)(rectY+rectH-rectH/textLocationYFactor));//scale에 맞춰서 보일지 말지 해야겠는디.
		}
		g2d.setClip(null);
	}
	
}
