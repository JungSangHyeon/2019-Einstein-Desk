package painter_Stuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.io.File;
import java.io.Serializable;

import javax.imageio.ImageIO;

import iconPainter.IconToImage;

public abstract class AComponentPainter  implements Serializable{
	private static final long serialVersionUID = -2266921661341345665L;
	
	protected int imageIndex; 
	protected String name = "";
	protected double nameSize = 50; //적은 값 : 실제 값 = 14 : 10
	protected Color nameColor = Color.BLACK, backGroundColor = Color.WHITE;
	
	public AComponentPainter(String name, String fileAddress) {
		if(name!=null) {this.name = name;}
		String fileType = fileAddress.substring(fileAddress.length()-4, fileAddress.length());
		if(fileType.equals(".png")) {
			try {ImgStorage.addImage(ImageIO.read(new File(fileAddress)));}
			catch (Exception e) {/*DO NOTHING*/}
		}else if(fileType.equals(".txt")) {
			ImgStorage.addImage(IconToImage.getImg(fileAddress));//fill Color
		}
		imageIndex = ImgStorage.getIndex();
	}
	
	public void setTextSize(int size) {this.nameSize = size;}
	public void setTextColor(Color c) {this.nameColor=c;}
	public void setBackGroundColor(Color c) {this.backGroundColor=c;}
	
	public abstract void paintComponent(Graphics g, Shape shape);
	
}
