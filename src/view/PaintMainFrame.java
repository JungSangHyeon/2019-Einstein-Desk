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
	
	public PaintMainFrame() {//minimum size Setting
		this.setSize(eInt.MainFrameNormalWidth.getVal(),eInt.MainFrameNormalHeight.getVal());
		this.setMinimumSize(new Dimension(eInt.MainFrameMinimumWidth.getVal(),eInt.MainFrameMinimumHeight.getVal()));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		
		try {this.setIconImage(ImageIO.read(new File(eString.MainFrameIconImageAddress.getVal())));} 
		catch (IOException e) {}//없으면 암것도 안함.
		
		drawingPanel = new DrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		this.setVisible(true);
		drawingPanel.initialize();
	}
}
