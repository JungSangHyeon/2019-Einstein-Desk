package zStuff_Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class ImgStorage { //�̹����� Ŭ�� ���鶧 �ȵǼ� ���� ������. ������ �޸𸮵� �� ���� �� ����. //ȣ! �ߵȴ�.
	
	static long nowId = 0;
	static Vector<IDImage> images = new  Vector<IDImage>();
	
	public static Vector<forSaveImg> saveImg(){
		Vector<forSaveImg> imagesForSave = new Vector<forSaveImg> ();
		for(IDImage img : images) {
			if(!img.getFileName().equals("")) {
				imagesForSave.add(new forSaveImg(img.getId(), img.getFileName()));
			}
		}
		return imagesForSave;
	}
	
	public static void loadImgVector(Vector<forSaveImg> imgVector){
		Vector<IDImage> img = new Vector<IDImage>();
		for(forSaveImg info : imgVector) {
			try {
				img.add(new IDImage(info.getId(), ImageIO.read(new File(info.getFileName())), info.getFileName()));
			} catch (IOException e) {
				try {
					img.add(new IDImage(info.getId(), ImageIO.read(new File("Image/error.png")), info.getFileName()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		images.addAll(img);
		nowId = images.lastElement().getId();
	}

	//+address
	public static long addImage(BufferedImage img, String fileName) {images.add(new IDImage(++nowId, img, fileName));return nowId;}
	public static long addImage(BufferedImage img) {images.add(new IDImage(++nowId, img));return nowId;}
	public static void removeImage(long id) {images.remove(findIDImage(id));}
	public static BufferedImage getImage(long id) {return findIDImage(id).getImage();}
	public static void changeImg(long id, BufferedImage image) {findIDImage(id).setImage(image);}
	
	public static IDImage findIDImage(long id) {
		for(IDImage idImage : images) {if(idImage.getId()==id) {return idImage;}}
		return null;
	}
}//�̰͵� �̸� �� �����س��� ������ ���� �� ���·�,
