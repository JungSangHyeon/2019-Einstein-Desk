package fImagePaint;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import zStuff_Image.AFImagePaint;
import zStuff_Image.ImgStorage;

public class FImageWindowPaint extends AFImagePaint {// D&D함
	private static final long serialVersionUID = -7941746680321916676L;
	
	public FImageWindowPaint(String fileAddress) {this.setImage(fileAddress);}

	public void realPaint(Graphics2D g2d) {//일부로 회전 안시킴
		g2d.setClip(master.getShape());
		Rectangle2D rect = master.getShape().getBounds2D();
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
	}
}
