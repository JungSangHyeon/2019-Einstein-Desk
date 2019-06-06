package zStuff_Image;

import java.awt.image.BufferedImage;

public class IDImage {
	
	public IDImage(long l, BufferedImage img) {
		this.id = l;
		this.image = img;
	}
	
	private long id;
	public long getId() {return id;}
	
	private BufferedImage image;
	public BufferedImage getImage() {return image;}
	public void setImage(BufferedImage image) {this.image = image;}
}
