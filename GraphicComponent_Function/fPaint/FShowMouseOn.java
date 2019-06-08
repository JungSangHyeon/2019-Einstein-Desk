package fPaint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import zStuff_Function.AFunction;
import zStuff_Image.ImgStorage;

public class FShowMouseOn extends AFunction{
	private static final long serialVersionUID = 5046938002818853280L;

	boolean mouseOnMe = false;
	Color selectColor  = new Color(191,191,191,100);
	
	protected long imageIndex = -1; 
	public FShowMouseOn(String fileName) {
		try {imageIndex = ImgStorage.addImage(ImageIO.read(new File(fileName)));}
		catch (IOException e) {/*DO NOTHING*/}
	}
	public FShowMouseOn() {}
	
	public void realPaint(Graphics2D g) {
		if(mouseOnMe) {
			if(imageIndex!=-1) {
				BufferedImage img = ImgStorage.getImage(imageIndex) ;
				AffineTransform at = new AffineTransform();
				Rectangle bound = master.getShape().getBounds();
				at.translate(bound.getX(), bound.getY());
				g.drawImage(img, at, null);
			}else {
				g.setColor(selectColor);
				g.fill(master.getShape());
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if(master.getShape().contains(e.getPoint())) {mouseOnMe = true;}
		else {mouseOnMe = false;}
	}
}
