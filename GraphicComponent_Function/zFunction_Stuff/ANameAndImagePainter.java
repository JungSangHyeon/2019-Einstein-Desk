package zFunction_Stuff;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public abstract class ANameAndImagePainter extends AFunction implements Serializable{
	private static final long serialVersionUID = -2266921661341345665L;
	
	protected int imageIndex; 
	protected String name = "";
	protected double nameSize = 50;
	protected Color nameColor = Color.BLACK, backGroundColor = Color.WHITE;
	protected boolean backGroundNeed = false;
	
	public void setTextSize(int size) {this.nameSize = size;}
	public void setTextColor(Color c) {this.nameColor=c;}
	public void setName(String name) {if(name!=null) {this.name = name;}}
	
	public void setBackGroundColor(Color c) {this.backGroundColor=c;}
	
	
	public void setImage(String fileAddress) {
		try {ImgStorage.addImage(ImageIO.read(new File(fileAddress)));}
		catch (IOException e) {/*DO NOTHING*/}
		imageIndex = ImgStorage.getIndex();
	}
	
}
