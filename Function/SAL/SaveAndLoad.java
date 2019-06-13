package SAL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Image.ImgStorage;
import zStuff_Image.forSaveImg;

public class SaveAndLoad {

	public static void save() {
		GCStorage_Selected.clearSelected();
		try {
			ObjectOutputStream projectOOS = new ObjectOutputStream(new FileOutputStream("saveFile/"+"test"));
			projectOOS.reset();
			projectOOS.writeObject(ProjectManager.getSave());
			projectOOS.writeObject(ImgStorage.saveImg());
			projectOOS.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	@SuppressWarnings("unchecked")
	public static void load() {
		try {
			ObjectInputStream projectOIS = new ObjectInputStream(new FileInputStream("saveFile/"+"test"));
			ProjectManager.setFiles((Vector<GraphicComponent>)projectOIS.readObject());
			ImgStorage.loadImgVector((Vector<forSaveImg>) projectOIS.readObject());
			projectOIS.close();
		} catch (Exception e) {e.printStackTrace();}
	}
}
