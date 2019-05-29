package imgLoad;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import component_Stuff.GraphicComponent;
import data.GCStorage_Normal;
import data.GCStorage_Selected;
import doUndo.RedoUndo;
import function_Paint.Paint_NormalIMG;
import function_Paint.Paint_TextWrite;
import function_Shape.Shape_Mover;
import function_Shape.Shape_Resizer;
import function_Shape.Shape_Rotator;
import onOff.AnchorPaint;
import shape_Stuff.eShape;
import zFunction_Stuff.AFunction;

public class ImgLoad extends AFunction{
	private static final long serialVersionUID = 6790901375407014041L;
	
	public void loadImg() {
		JFileChooser chooser = new JFileChooser("behindIMG/");// 객체 생성
		int ret = chooser.showOpenDialog(null); // 열기창 정의
		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "경로를 선택하지않았습니다.", "나 왜불렀니?", JOptionPane.WARNING_MESSAGE);
			return;
		}
		BufferedImage behindeImg = null;
		try {behindeImg = ImageIO.read(chooser.getSelectedFile());}
		catch (IOException e1) {e1.printStackTrace();}
		//make gc by img
		
		GraphicComponent GCData = new GraphicComponent();
		GCData.addPoint(new Point2D.Float(0, 0));
		GCData.addPoint(new Point2D.Float(behindeImg.getWidth(), behindeImg.getHeight()));
		GCData.setAShape(eShape.rect.getAShape());
		GCData.addFunction(new Shape_Mover());
		GCData.addFunction(new Shape_Rotator());
		GCData.addFunction(new Shape_Resizer());
		GCData.addFunction(new Paint_NormalIMG("",chooser.getSelectedFile().getPath()));
		GCData.addFunction(new Paint_TextWrite());
		GCData.setBorderPaint(false);
		GCData.setShape(GCData.getAShape().newShape(GCData.getPoints()));
		GCStorage_Normal.addNewGC(GCData);
		
		Rectangle rect = GCStorage_Normal.getLastGC().getShape().getBounds();
		GCStorage_Normal.getLastGC().setMyCenter(new Point2D.Float(rect.x+rect.width/2, rect.y+rect.height/2));
		
		GCStorage_Selected.addSelectedGC(GCStorage_Normal.getLastGC());
		AnchorPaint.on();
		RedoUndo.saveNowInHistory();
		
		//add img to storage
	}

	public void mousePressed(MouseEvent e) {
		loadImg();
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void paintComponent(Graphics2D g, Shape shape) {}
	public void processSelectEvent(boolean selected) {}
}
