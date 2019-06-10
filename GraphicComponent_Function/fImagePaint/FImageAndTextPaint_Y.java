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

public class FImageAndTextPaint_Y extends AFImagePaint {//우클릭하면 나오는것 용.
	private static final long serialVersionUID = -4220534203464156180L;

	static final int imgLRGap = 6, imgUpGap = 6;

	String name = "";
	double nameSize = 50;
	Color nameColor = Color.BLACK;
	
	public FImageAndTextPaint_Y(String name, int size, String imgFileAddress) {
		this.nameSize = size;
		this.name=name;
		this.setImage(imgFileAddress);
	}
	
	public void setTextSize(int size) {this.nameSize = size;}
	public void setTextColor(Color c) {this.nameColor=c;}
	
	public void realPaint(Graphics2D g) {
		Rectangle rect = master.getShape().getBounds();
		int rectX = rect.x, rectY= rect.y, rectW = rect.width, rectH = rect.height;
		g.setColor(nameColor);
		
		BufferedImage img = ImgStorage.getImage(imageIndex);
		int imgWH = Math.min(rectW-imgLRGap*2, img.getWidth());
		if (img != null) {g.drawImage(img, rectX + rectW/2 - imgWH/2, rectY+imgUpGap, imgWH, imgWH, null);};
		if(!name.equals("")) {
			g.setFont(new Font("맑은 고딕", Font.PLAIN, (int)nameSize));
			GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), name);
			Shape textShape =  gv.getOutline();
			AffineTransform at = new AffineTransform();
			double textShapeHeight = textShape.getBounds().getHeight();
			double textShapeWidth = textShape.getBounds().getWidth();
			at.translate(0, textShapeHeight);//make it normal form
			int textAreaRectHeight = rectH - imgUpGap - imgWH;
			at.translate(rectX+rectW/2 - textShapeWidth/2, rectY + imgWH + imgUpGap +  textAreaRectHeight/2 - textShapeHeight/2);
			
			textShape = at.createTransformedShape(textShape);
			g.setColor(Color.BLACK);
			g.fill(textShape);
		}
	}
}
