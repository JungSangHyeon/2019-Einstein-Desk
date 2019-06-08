package fileSelectPanel;

import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_X;

public class FileSelectPanel extends GCPanel_LayoutPixel_X{
	private static final long serialVersionUID = -206303412998677778L;

	public FileSelectPanel() {
		//1920 = n * pixelSize + (n-1) * gap 
		this.setPixelSize(100,100);
		this.setPixelGap(10, 10);
		this.setSize(5, 1);
		this.setGCLocation(0,0);
		this.saveClip();
	}
	
}
