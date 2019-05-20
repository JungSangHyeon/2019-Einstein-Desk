package loadingPage;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class LoadingPage extends JFrame{

	BufferedImage img = null;
	
	public LoadingPage(Color backGroundColor, String imgAddr) {
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.getContentPane().setBackground(backGroundColor);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Cursor invisCursor = tk.createCustomCursor(tk.createImage(""),new Point(), null);
		this.setCursor(invisCursor);
		
		try {this.img = ImageIO.read(new File(imgAddr));}
		catch (Exception e) {/*DO NOTHING*/}
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(img!=null) {g.drawImage(img, this.getWidth()/2 - img.getWidth()/2, this.getHeight()/2 - img.getHeight()/2, null);}
	}
	
	public void LoadingPageOff() {
		this.setVisible(false);
	}
}
