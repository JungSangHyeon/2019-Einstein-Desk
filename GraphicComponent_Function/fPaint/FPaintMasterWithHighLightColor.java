package fPaint;

import java.awt.Graphics2D;

import data.LineData;
import zStuff_Function.AFunction;

public class FPaintMasterWithHighLightColor extends AFunction{
	private static final long serialVersionUID = 5046938002818853280L;

	public void realPaint(Graphics2D g) {//���̶���Ʈ �÷��� ��� �������� ���� �׸��� ��
		g.setColor(LineData.getHighlightColor());
		g.fill(master.getShape());
	}
}