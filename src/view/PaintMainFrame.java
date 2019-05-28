package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import global.InjectEnums.eInt;
import global.InjectEnums.eString;

@SuppressWarnings("serial")
public class PaintMainFrame extends JFrame {

	DrawingPanel drawingPanel;
	
	public PaintMainFrame() {
		this.setMinimumSize(new Dimension(eInt.MainFrameMinimumWidth.getVal(),eInt.MainFrameMinimumHeight.getVal()));
		this.setSize(eInt.MainFrameNormalWidth.getVal(),eInt.MainFrameNormalHeight.getVal());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setUndecorated(true);
		
		try {this.setIconImage(ImageIO.read(new File(eString.MainFrameIconImageAddress.getVal())));} 
		catch (IOException e) {/*DO NOTHING*/}
		
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		
		drawingPanel = new DrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		this.setVisible(true);
		drawingPanel.initialize();
	}
}
