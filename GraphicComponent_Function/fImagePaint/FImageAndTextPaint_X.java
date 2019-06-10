package fImagePaint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import zStuff_Image.AFImagePaint;
import zStuff_Image.ImgStorage;

public class FImageAndTextPaint_X extends AFImagePaint {//우클릭하면 나오는것 용.
	private static final long serialVersionUID = -7129480604093083071L;
	
	static final int imgLeftGap = 12, imgRightGap = 10, imgUpDownGap = 6;

	String name = "";
	double nameSize = 50;
	Color nameColor = Color.BLACK;
	
	public FImageAndTextPaint_X(String name, int size, String imgFileAddress) {
		this.nameSize = size;
		this.name=name;
		this.setImage(imgFileAddress);
	}
	public void setTextSize(int size) {this.nameSize = size;}
	public void setTextColor(Color c) {this.nameColor=c;}
	
	public void realPaint(Graphics2D g) {
		Rectangle rect = master.getShape().getBounds();
		int rectX = rect.x, rectY= rect.y, rectH = rect.height;
		g.setColor(nameColor);
		BufferedImage img = ImgStorage.getImage(imageIndex) ;
		if(rect.getWidth()>rect.getHeight()) {
			if (img != null) {g.drawImage(img, rectX+imgLeftGap, rectY+imgUpDownGap, rectH-imgUpDownGap*2, rectH-imgUpDownGap*2, null);}
			if(!name.equals("")) {
				g.setFont(new Font("맑은 고딕", Font.PLAIN, (int)nameSize));
				GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), name);
				Shape textShape =  gv.getOutline();
				AffineTransform at = new AffineTransform();
				double textShapeHeight = textShape.getBounds().getHeight();
				at.translate(0, textShapeHeight);//make it normal form
				at.translate(rectX+rectH +imgLeftGap, rectY + rectH/2-textShapeHeight/2);//rectH = image Width
				textShape = at.createTransformedShape(textShape);
				g.setColor(Color.BLACK);
				g.fill(textShape);
			}
		}
	}
}
