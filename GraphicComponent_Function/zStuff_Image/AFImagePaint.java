package zStuff_Image;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import zStuff_Function.AFunction;

public abstract class AFImagePaint extends AFunction implements Serializable{
	private static final long serialVersionUID = -2266921661341345665L;
	
	protected boolean mouseOn = false;
	protected long imageIndex; 
	protected Color backGroundColor = Color.WHITE;
	public void setBackGroundColor(Color c) {this.backGroundColor=c;}
	
	public void setImage(String fileAddress) {
		try {imageIndex = ImgStorage.addImage(ImageIO.read(new File(fileAddress)));}
		catch (IOException e) {/*DO NOTHING*/}
	}
}
