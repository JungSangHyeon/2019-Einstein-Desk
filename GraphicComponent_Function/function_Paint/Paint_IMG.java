package function_Paint;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import zFunction_Stuff.ANameAndImagePainter;
import zFunction_Stuff.ImgStorage;

public class Paint_IMG extends ANameAndImagePainter {// D&D��
	private static final long serialVersionUID = -7941746680321916676L;
	
	final static int textWRadio = 8, textHRadio = 4, textLocationXFactor = 40, textLocationYFactor = 20;
	
	public Paint_IMG(String name, String fileAddress) {
		this.setName(name);
		this.setImg(fileAddress);
	}

	public void paintComponent(Graphics2D g2d, Shape shape) {
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		Rectangle2D rect = shape.getBounds2D();
		g2d.fill(rect);
		double rectX = rect.getX(), rectY= rect.getY(), rectW = rect.getWidth(), rectH = rect.getHeight();
		if(backGroundNeed) {g2d.setColor(backGroundColor); g2d.fill(rect);}//���� �̹��� Ȱ���� ����
		BufferedImage img = ImgStorage.getImage(imageIndex) ;
		if (img != null) {
			double imgW = img.getWidth(), imgH = img.getHeight(), radio = 0;
			radio = (imgH*rectW/imgW>rectH)? rectW/imgW : rectH/imgH;
			imgW *= radio; imgH *= radio;
			AffineTransform t = new AffineTransform();//�����ϰ�? �׸��¹�. �ȼ����� ��������? ����ؾ� �ճױ�
			t.translate(rectX+rectW / 2 - imgW / 2, rectY+rectH / 2 - imgH / 2);
			t.scale(radio, radio);
			g2d.drawImage(img, t, null);
		}
		if(!name.equals("")) {
			g2d.setColor(nameColor);
			float size =g2d.getFontMetrics(new Font(null, Font.PLAIN, (int)nameSize)).stringWidth(name);
			if(rectW<size) {nameSize = rectW/textWRadio;}
			else {nameSize = Math.min(rectH/textHRadio, rectW/textWRadio) ;}
			g2d.setFont(new Font(null, Font.PLAIN, (int)nameSize));
			g2d.drawString(name, (float)(rectX+rectW/textLocationXFactor), (float)(rectY+rectH-rectH/textLocationYFactor));//scale�� ���缭 ������ ���� �ؾ߰ڴµ�.
		}
	}
	
}
