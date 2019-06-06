package zStuff_Image;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class ImgStorage { //�̹����� Ŭ�� ���鶧 �ȵǼ� ���� ������. ������ �޸𸮵� �� ���� �� ����. //ȣ! �ߵȴ�.
	
	static long nowId = 0;
	static Vector<IDImage> images = new  Vector<IDImage>();
	
	public static long addImage(BufferedImage img) {images.add(new IDImage(++nowId, img));return nowId;}
	public static void removeImage(long id) {images.remove(findIDImage(id));}
	public static BufferedImage getImage(long id) {return findIDImage(id).getImage();}
	public static void changeImg(long id, BufferedImage image) {findIDImage(id).setImage(image);}
	
	public static IDImage findIDImage(long id) {
		for(IDImage idImage : images) {if(idImage.getId()==id) {return idImage;}}
		return null;
	}
}//�̰͵� �̸� �� �����س��� ������ ���� �� ���·�,
