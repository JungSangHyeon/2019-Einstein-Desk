package fImagePaint;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import calculation.AffineMath;
import zStuff_Image.AFImagePaint;
import zStuff_Image.ImgStorage;

public class FImageNormalPaint extends AFImagePaint {
	private static final long serialVersionUID = -7941746680321916676L;
	
	public FImageNormalPaint(String fileAddress) {this.setImage(fileAddress);}

	public void realPaint(Graphics2D g2d) {
		Rectangle2D beforeRotateBound = AffineMath.getRotateShape(master.getShape(), -master.getAngle(), master.getCenter()).getBounds2D();
		g2d.rotate(Math.toRadians(master.getAngle()), master.getCenter().getX(), master.getCenter().getY());//affine�� ������ �ȵǳ׿�
		BufferedImage img = ImgStorage.getImage(imageIndex) ;
		if (img != null) {
			AffineTransform at = new AffineTransform();//�����ϰ�? �׸��¹�. �ȼ����� ��������? ����ؾ� �ճױ�
			at.translate(beforeRotateBound.getX(), beforeRotateBound.getY());
			at.scale(beforeRotateBound.getWidth()/img.getWidth(), beforeRotateBound.getHeight()/img.getHeight());
			g2d.drawImage(img, at, null);
		}
		g2d.rotate(-Math.toRadians(master.getAngle()), master.getCenter().getX(), master.getCenter().getY());
	}
}
