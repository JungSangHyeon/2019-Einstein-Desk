package fImagePaint;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import zStuff_Image.AFImagePaint;
import zStuff_Image.ImgStorage;

public class FImageWindowPaint extends AFImagePaint {// D&D��
	private static final long serialVersionUID = -7941746680321916676L;
	
	public FImageWindowPaint(String fileAddress) {this.setImage(fileAddress);}

	public void realPaint(Graphics2D g2d) {//�Ϻη� ȸ�� �Ƚ�Ŵ
		g2d.setClip(master.getShape());
		Rectangle2D rect = master.getShape().getBounds2D();
		double rectX = rect.getX(), rectY= rect.getY(), rectW = rect.getWidth(), rectH = rect.getHeight();
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
	}
}
