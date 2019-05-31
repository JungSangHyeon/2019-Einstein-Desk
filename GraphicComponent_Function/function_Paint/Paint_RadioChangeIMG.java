package function_Paint;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import zFunction_Stuff.ANameAndImagePainter;
import zFunction_Stuff.ImgStorage;

public class Paint_RadioChangeIMG extends ANameAndImagePainter {// D&D함
	private static final long serialVersionUID = -7941746680321916676L;
	
	final static int textWRadio = 8, textHRadio = 4, textLocationXFactor = 40, textLocationYFactor = 20;
	
	public Paint_RadioChangeIMG(String name, String fileAddress) {
		this.setName(name);
		this.setImage(fileAddress);
	}

	public void realPaint(Graphics2D g2d) {
		Shape startClip  = g2d.getClip();
		Rectangle2D rect = master.getShape().getBounds2D();
		g2d.setClip(master.getShape());
		g2d.fill(rect);
		double rectX = rect.getX(), rectY= rect.getY(), rectW = rect.getWidth(), rectH = rect.getHeight();
		if(backGroundNeed) {g2d.setColor(backGroundColor); g2d.fill(rect);}//투명 이미지 활용을 위함
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
		if(!name.equals("")) {
			g2d.setColor(nameColor);
			float size =g2d.getFontMetrics(new Font(null, Font.PLAIN, (int)nameSize)).stringWidth(name);
			if(rectW<size) {nameSize = rectW/textWRadio;}
			else {nameSize = Math.min(rectH/textHRadio, rectW/textWRadio) ;}
			g2d.setFont(new Font(null, Font.PLAIN, (int)nameSize));
			g2d.drawString(name, (float)(rectX+rectW/textLocationXFactor), (float)(rectY+rectH-rectH/textLocationYFactor));//scale에 맞춰서 보일지 말지 해야겠는디.
		}
		g2d.setClip(startClip);
	}
	
}
