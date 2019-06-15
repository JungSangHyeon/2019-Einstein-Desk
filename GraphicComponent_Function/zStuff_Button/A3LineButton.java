package zStuff_Button;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import zStuff_Function.AFunction;

@SuppressWarnings("serial")
public abstract class A3LineButton extends AFunction{//�� 3��¥�� ��ư. Ÿ���� Ű�� ���� �뵵. Ÿ�ٰ� Ÿ�� üũ�� �ٲ㼭 �ᵵ �ȴ�.

	int gap = 3;//¦���� ����
	int height = 2;//¦��� ����
	int width = 22;//¦��� ����
	int num = 3;//¦��� ����
	
	Color normalBackC = new Color(230, 230, 230);
	Color onBackC = new Color(200, 200, 200);
	Color pressBackC = new Color(170, 170, 170);
	Color changeBackC = new Color(68, 114, 196);
	
	Color normalBarC = new Color(72, 72, 72);
	Color changeBarC = new Color(255,255,255);
	
	Color backGroundColor;
	Color barColor;
	
	public boolean on = false;
	boolean mouseIn = false;
	public void off() {on=false; barColor = normalBarC; backGroundColor=normalBackC;}
	
	public A3LineButton() {
		backGroundColor = normalBackC;
		barColor = normalBarC;
	}
	
	public void realPaint(Graphics2D g) {
		Rectangle bound = master.getShape().getBounds();
		g.setColor(backGroundColor);
		g.fill(master.getShape());
		g.setColor(barColor);
		int startY = (int) (bound.getHeight()/2 - (height*num+gap*(num-1))/2);
		for(int i=0; i<num; i++) {g.fillRect((int) (bound.getWidth()/2-width/2), startY+(height+gap)*i, width, height);}
	}
	
	public void mousePressed(MouseEvent e){setBackGroundColor(2);}
	public void mouseReleased(MouseEvent e){reaction();setBackGroundColor(1);}
	public void mouseMoved(MouseEvent e){
		if(master.getShape().contains(e.getPoint())) {mouseIn=true;setBackGroundColor(1);}
		else {mouseIn=false;setBackGroundColor(1);}
	}
	
	public void setBackGroundColor(int i) {
		barColor = normalBarC;
		if(i==1) {
			if(on) {backGroundColor=changeBackC;barColor = changeBarC;}//Ÿ�� üũ
			else if(mouseIn) {backGroundColor=onBackC;}
			else {backGroundColor=normalBackC;}
		}else if(i==2){backGroundColor=pressBackC;}
	}
	
	public void reaction() {
		if(mouseIn) {
			realReaction();
			on=(!on);
		}
	}
	public abstract void realReaction();
}
