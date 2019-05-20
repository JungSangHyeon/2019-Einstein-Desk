package zFunction_Stuff;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class ImgStorage {//이미지는 클론 만들때 안되소 따로 빼놓음. 그편이 메모리도 덜 먹을 듯 ㄹ함. //호! 잘된다.

	static Vector<BufferedImage> images = new  Vector<BufferedImage>();
	
	public static int getIndex() {return images.size()-1;}
	public static void addImage(BufferedImage img) {images.add(img);}
	public static BufferedImage getImage(int i) {return images.get(i);}
	
}//이것도 미리 다 저장해놓고 가져다 쓰느 ㄴ 형태로,
