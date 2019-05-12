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
	protected int borderThick = 5;
	protected String name = "";
	protected double nameSize = 50; //적은 값 : 실제 값 = 14 : 10
	protected Color nameColor = Color.BLACK, backgroundColor = new Color(0, 183, 195), borderColor = new Color(60,60,60,100);//얘는 안쪽 보더임.
	
	public AComponentPainter(String name, String fileAddress) {
		if(name!=null) {this.name = name;}
		
		String fileType = fileAddress.substring(fileAddress.length()-4, fileAddress.length());
		if(fileType.equals(".png")) {
			try {ImgStorage.addImage(ImageIO.read(new File(fileAddress)));}
			catch (Exception e) {System.out.println(fileAddress+"에 이미지 읎따.");}//없으면 그냥 안띄움.
		}else if(fileType.equals(".txt")) {
			ImgStorage.addImage(IconToImage.getImg(fileAddress, Color.cyan));//fill Color
		}
		imageIndex = ImgStorage.getIndex();
	}
	
	public abstract void paintComponent(Graphics g, Shape shape);
	
	public void setTextSize(int size) {this.nameSize = size;}
	public void setTextColor(Color textColor) {this.nameColor=textColor;}
	public void setBackGroundColor(Color backgroundColor) {this.backgroundColor=backgroundColor;}
	
}
