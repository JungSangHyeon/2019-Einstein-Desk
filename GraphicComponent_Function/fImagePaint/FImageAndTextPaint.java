package fImagePaint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import zStuff_Image.AFImagePaint;
import zStuff_Image.ImgStorage;

public class FImageAndTextPaint extends AFImagePaint {//우클릭하면 나오는것 용.
	private static final long serialVersionUID = -7129480604093083071L;
	
	static final float textWRadio = 3.5f, textHRadio = 1.5f, textYFactor = 0.357f;//시작 좌표+ 네모의 높이 /2 + 글씨크기 *textYFactor
	static final int imgLeftGap = 2, imgRightGap = 4, imgUpDownGap = 2;

	String name = "";
	double nameSize = 50;
	Color nameColor = Color.BLACK;
	
	public FImageAndTextPaint(String name, String fileAddress) {
		this.name=name;
		this.setImage(fileAddress);
	}
	public void setTextSize(int size) {this.nameSize = size;}
	public void setTextColor(Color c) {this.nameColor=c;}
	
	public void realPaint(Graphics2D g) {
		g.setClip(master.getShape());
		Rectangle rect = master.getShape().getBounds();
		int rectX = rect.x, rectY= rect.y, rectW = rect.width, rectH = rect.height;
		g.setColor(nameColor);
		BufferedImage img = ImgStorage.getImage(imageIndex) ;
		if(rect.getWidth()>rect.getHeight()) {
			if (img != null) {g.drawImage(img, rectX+imgLeftGap, rectY+imgUpDownGap, rectH-imgLeftGap*2, rectH-imgUpDownGap*2, null);}
			if(!name.equals("")) {
				float gap = rectW - (imgLeftGap+rectH-imgUpDownGap*4);
				float size =g.getFontMetrics(new Font(null, Font.PLAIN, (int)nameSize)).stringWidth(name);
				if(gap<size) {nameSize = gap/textWRadio;}
				else {nameSize = Math.min(rectH/textHRadio, gap/textWRadio) ;}
				g.setFont(new Font(null, Font.PLAIN, (int)nameSize));
				g.drawString(name, rectX+imgLeftGap+rectH+imgRightGap, (int) (rectY+rectH/2 + nameSize*textYFactor) );
			}
		}
	}
}
