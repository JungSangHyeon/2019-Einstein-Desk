package fPaint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;

import zStuff_Function.AFunction;

public class FDummy extends AFunction {
	private static final long serialVersionUID = 5046938002818853280L;

	int num;
	public FDummy(int i) {num = i;}

	public void realPaint(Graphics2D g) {// 하이라이트 컬러로 기냥 마스터의 쉐입 그리는 겨
		Rectangle rect = master.getShape().getBounds();
		GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), num+"");
		AffineTransform at = new AffineTransform();
		at.translate(rect.getX()+rect.getWidth()/2-gv.getOutline().getBounds().getWidth()/2, rect.getY()+rect.getHeight()/2-gv.getOutline().getBounds().getHeight()/2);
		g.setColor(Color.cyan);
		g.fill(at.createTransformedShape(gv.getOutline()));
	}

	public int getNum() {return num;}
}
