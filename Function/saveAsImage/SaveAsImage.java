package saveAsImage;

import java.awt.event.MouseEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import zStuff_Function.AFunction;
import zStuff_GraphicComponent.GCStorage_Normal;

public class SaveAsImage extends AFunction {
	private static final long serialVersionUID = 3062786749371115895L;

	public void mousePressed(MouseEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		File theDirectory = new File("saveImage");
		fileChooser.setCurrentDirectory(theDirectory);
		FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG file (*.png)", "png");
		fileChooser.addChoosableFileFilter(pngFilter);
		fileChooser.setFileFilter(pngFilter);

		int status = fileChooser.showSaveDialog(fileChooser);

		if (status == JFileChooser.APPROVE_OPTION) {
			try {
				File saveFile = fileChooser.getSelectedFile();
				String fileName = saveFile.getName();
				String type = "";
				if(!(fileName.length()<4)) {
					type = fileName.substring(fileName.length()-4, fileName.length());
				}
				if(!type.equals(".png")) {
					fileName+=".png";
					saveFile = new File(saveFile.getPath()+".png");
				}
				ImageIO.write(ImgManager.getImage(GCStorage_Normal.getGCVector()), "png", saveFile);
				JOptionPane.showMessageDialog(null, "이미지 저장 완료");
			} catch (Exception ex) {ex.printStackTrace();}
		}
	}
}
