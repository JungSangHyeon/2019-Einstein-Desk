package zStuff_Image;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class IDImage implements Serializable{
	private static final long serialVersionUID = 287716127999934207L;
	
	public IDImage(long l, BufferedImage img) {
		this.id = l;
		this.image = img;
	}
	
	public IDImage(long l, BufferedImage img, String fileName) {
		this.id = l;
		this.image = img;
		this.fileName=fileName;
	}
	
	private long id;
	public long getId() {return id;}
	
	private BufferedImage image;
	public BufferedImage getImage() {return image;}
	public void setImage(BufferedImage image) {this.image = image;}
	
	String fileName = "";
	public void setFileName(String fileName) {this.fileName=fileName;}
	public String getFileName() {return fileName;}
}
