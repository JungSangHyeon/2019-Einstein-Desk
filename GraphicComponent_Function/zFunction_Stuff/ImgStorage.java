package zFunction_Stuff;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class ImgStorage {//�̹����� Ŭ�� ���鶧 �ȵǼ� ���� ������. ������ �޸𸮵� �� ���� �� ����. //ȣ! �ߵȴ�.

	static Vector<BufferedImage> images = new  Vector<BufferedImage>();
	
	public static int getIndex() {return images.size()-1;}
	public static void addImage(BufferedImage img) {images.add(img);}
	public static BufferedImage getImage(int i) {return images.get(i);}
	
}//�̰͵� �̸� �� �����س��� ������ ���� �� ���·�,
