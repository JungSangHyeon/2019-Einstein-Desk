package saveAsImage;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import canvas.CanvasGC;
import view.DrawingPanel;
import zStuff_Function.AFunction;
import zStuff_GraphicComponent.GraphicComponent;

public class SaveAsImage extends AFunction {
	private static final long serialVersionUID = 3062786749371115895L;

	public void mousePressed(MouseEvent e) {
		GraphicComponent canvas = CanvasGC.getCanvas();
		Rectangle canvasBound = canvas.getShape().getBounds();
		canvas.setBorderPaint(false);
		BufferedImage image = new BufferedImage((int)canvasBound.getWidth(), (int)canvasBound.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.translate(-canvasBound.getX(), -canvasBound.getY());
		DrawingPanel.drawingPanelPaint(g);
		g.dispose();
		canvas.setBorderPaint(true);
		
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
				ImageIO.write(image, "png", saveFile);
				JOptionPane.showMessageDialog(null, "이미지 저장 완료");
			} catch (Exception ex) {ex.printStackTrace();}
		}

	}
}
