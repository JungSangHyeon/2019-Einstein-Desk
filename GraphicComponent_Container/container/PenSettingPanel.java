package container;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import lineSetting_Stuff.PenColorSelectPanel;
import lineSetting_Stuff.PenSizeBar;

@SuppressWarnings("serial")
public class PenSettingPanel extends JPanel{

	Image myImg;
	int borderThick = 1;
	Color borderColor = new Color(204,204,204), fillColor = new Color(242,242,242);
	PenColorSelectPanel PP ;
	PenSizeBar SB;
	
	public PenSettingPanel() {
		this.setVisible(false);
		this.setLayout(null);
		this.setSize(322,458);//�ߴ� ��� �� ������� ��߰ڼ�
		
		PP = new PenColorSelectPanel();
		PP.addMouseListener(new mouseHadlerForRepaint());
		PP.setLocation(5,40);
		this.add(PP);
		
		SB = new PenSizeBar();
		SB.setLocation(5,340);
		this.add(SB);
	}
	
	public void paint(Graphics2D g2d) {
		if(this.isVisible()) {
			createImg();
			paintImg(g2d);
		}
	}
	
	public void paintImg(Graphics2D g2d) {
		g2d.drawImage(myImg, this.getX(), this.getY(), null);
	}

	private void createImg() {
		myImg = this.createImage(this.getWidth(),this.getHeight());
		Graphics2D img_g = (Graphics2D)myImg.getGraphics();
		img_g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		img_g.setColor(borderColor);
		img_g.fillRect(0, 0, getWidth(), getHeight());
		
		img_g.setColor(fillColor);
		img_g.fillRect(borderThick,borderThick, getWidth()-borderThick*2, getHeight()-borderThick*2);
		
		img_g.setColor(Color.BLACK);//�ؽ�Ʈ�� �ø��� �ָ��ϴپ�
		img_g.setFont(new Font(null, Font.PLAIN, 16));
		img_g.drawString("Colors", 10, 25);
		img_g.drawString("Size", 10, 335);
		PP.paintImg(img_g);
		SB.paintImg(img_g);
	}

	public class mouseHadlerForRepaint extends MouseAdapter{
		public void mouseReleased(MouseEvent e) {SB.repaint();}
	}

}
