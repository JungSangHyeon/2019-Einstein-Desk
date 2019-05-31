package function_Paint;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import zFunction_Stuff.ANameAndImagePainter;
import zFunction_Stuff.ImgStorage;

public class Paint_NormalIMG extends ANameAndImagePainter {// D&D함
	private static final long serialVersionUID = -7941746680321916676L;
	
	final static int textWRadio = 8, textHRadio = 4, textLocationXFactor = 40, textLocationYFactor = 20;
	
	public Paint_NormalIMG(String name, String fileAddress) {
		this.setName(name);
		this.setImg(fileAddress);
	}

	public void paintComponent(Graphics2D g2d, Shape shape) {
		Rectangle2D rect = getBeforeRotateBorder();
		double rectX = rect.getX(), rectY= rect.getY(), rectW = rect.getWidth(), rectH = rect.getHeight();
		g2d.rotate(Math.toRadians(master.getAngle()), master.getCenter().getX(), master.getCenter().getY());//affine에 넣으면 안되네여
		if(backGroundNeed) {g2d.setColor(backGroundColor); g2d.fill(rect);}//투명 이미지 활용을 위함
		BufferedImage img = ImgStorage.getImage(imageIndex) ;
		if (img != null) {
			double imgW = img.getWidth(), imgH = img.getHeight();
			AffineTransform t = new AffineTransform();//세밀하게? 그리는법. 픽셀보다 작은단위? 모라해야 합네까
			t.translate(rectX, rectY);
			t.scale(rectW/imgW, rectH/imgH);
			g2d.drawImage(img, t, null);
		}
		g2d.rotate(-Math.toRadians(master.getAngle()), master.getCenter().getX(), master.getCenter().getY());
	}
	
	private Rectangle2D getBeforeRotateBorder() {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		return at.createTransformedShape(master.getShape()).getBounds2D();
	}
	
}
