package zFunction_Stuff;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.Serializable;

import javax.imageio.ImageIO;

import iconPainter.IconToImage;

public abstract class ANameAndImagePainter extends AFunction implements Serializable{
	private static final long serialVersionUID = -2266921661341345665L;
	
	protected int imageIndex; 
	protected String name = "";
	protected double nameSize = 50; //적은 값 : 실제 값 = 14 : 10
	protected Color nameColor = Color.BLACK, backGroundColor = Color.WHITE;
	protected boolean backGroundNeed = false;
	
	public abstract void paintComponent(Graphics2D g, Shape shape);
	
	public void setTextSize(int size) {this.nameSize = size;}
	public void setTextColor(Color c) {this.nameColor=c;}
	public void setBackGroundColor(Color c) {this.backGroundColor=c;}
	public void setBackGroundNeed(boolean boo) {this.backGroundNeed=boo;}
	public void setName(String name) {if(name!=null) {this.name = name;}}
	public void setImg(String fileAddress) {
		String fileType = fileAddress.substring(fileAddress.length()-4, fileAddress.length());
		if(fileType.equals(".png")) {
			try {ImgStorage.addImage(ImageIO.read(new File(fileAddress)));}
			catch (Exception e) {/*DO NOTHING*/}
		}else if(fileType.equals(".txt")) {
			ImgStorage.addImage(IconToImage.getImg(fileAddress));//fill Color
		}
		imageIndex = ImgStorage.getIndex();
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void processSelectEvent(boolean selected) {}
}
