package imgLoad;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import canvas.CanvasGC;
import fComposite.FInCanvasGCBasicFunction;
import fImagePaint.FImageNormalPaint;
import onOff.AnchorPaint;
import redoUndo.RedoUndo;
import zStuff_Function.AFunction;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Shape.eShape;

public class ImgLoad extends AFunction{
	private static final long serialVersionUID = 6790901375407014041L;
	
	public void mousePressed(MouseEvent e) {loadImg();}
	
	public void loadImg() {
		JFileChooser chooser = new JFileChooser("saveImage/");// 객체 생성
		int ret = chooser.showOpenDialog(null); // 열기창 정의
		if (ret != JFileChooser.APPROVE_OPTION) {return;}
		
		BufferedImage behindeImg = null;
		try {behindeImg = ImageIO.read(chooser.getSelectedFile());}
		catch (IOException e) {JOptionPane.showMessageDialog(null, "이미지가 없습니다");}
		
		if(behindeImg!=null) {
			GraphicComponent GCData = new GraphicComponent();
			Rectangle rect = CanvasGC.getCanvas().getShape().getBounds();
			GCData.addPoint(new Point2D.Float((float)(rect.getX() + rect.getWidth()/2 -  behindeImg.getWidth()/2), (float)(rect.getY() + rect.getHeight()/2 -  behindeImg.getHeight()/2)));
			GCData.addPoint(new Point2D.Float((float)(rect.getX() + rect.getWidth()/2 +  behindeImg.getWidth()/2), (float)(rect.getY() + rect.getHeight()/2 +  behindeImg.getHeight()/2)));
			GCData.setAShape(eShape.rect.getAShape());
			GCData.addFunction(new FInCanvasGCBasicFunction());
			GCData.addFunction(new FImageNormalPaint(chooser.getSelectedFile().getPath()));
			GCData.setBorderPaint(false);
			GCData.setFillPaint(false);
			GCData.setShape(GCData.getAShape().newShape(GCData.getPoints()));
			GCStorage_Normal.addNewGC(GCData);
			
			Rectangle gcRect = GCStorage_Normal.getLastGC().getShape().getBounds();
			GCStorage_Normal.getLastGC().setCenter(new Point2D.Float(gcRect.x+gcRect.width/2, gcRect.y+gcRect.height/2));
			GCStorage_Selected.clearSelected();
			GCStorage_Selected.addSelectedGC(GCStorage_Normal.getLastGC());
			AnchorPaint.on();
			RedoUndo.saveNowInHistory();
		}
	}
}
