package global;

import java.awt.Color;

import enumInjector.Injector;
import enumInjector.Injector.InjectTarget;

public class InjectEnums {

	public static void injectPreEnums() {
		Injector.inject(eInt.LeastLoadingPageOnTime);
		Injector.inject(eString.LoadingPageImageAddress);
	}

	public static void injectAllEnums() {
		InjectTarget[][] targetArrs= {eInt.values(), eString.values(), eColor.values()};//여따 enum들 넣으시오.
		Injector.registeAll(targetArrs);
		Injector.injectRegisted();
	}
	
	//Addresses_이걸 설정하는 Enum을 만들 수 도 있긴한디....
	static String mainFrameTableAddress = "Setting1/MainFrame.txt";
	static String drawingPanelTableAddress = "Setting1/DrawingPanel.txt";
	static String timeTableAddress = "Setting1/Time.txt";
	static String shapeTableAddress = "Setting1/Shape.txt";
	static String systemTableAddress = "Setting1/System.txt";
	static String imageTableAddress = "Setting1/Image.txt";

	//Inject Enums
	public enum eInt implements InjectTarget {
		MainFrameNormalWidth(mainFrameTableAddress, "MainFrameNormalWidth"),
		MainFrameNormalHeight(mainFrameTableAddress, "MainFrameNormalHeight"),
		MainFrameMinimumWidth(mainFrameTableAddress, "MainFrameMinimumWidth"),
		MainFrameMinimumHeight(mainFrameTableAddress, "MainFrameMinimumHeight"),
		
		LeastLoadingPageOnTime(timeTableAddress, "LeastLoadingPageOnTime"),
		
		ShapeBasicBorderThick(shapeTableAddress, "ShapeBasicBorderThick"),
		;
		private String[] info; private int i;
		private eInt(String table, String key) {this.info = new String[] { table, key };}
		public void setVal(String[] val) {this.i = Integer.parseInt(val[0]);}
		public int getVal() {return this.i;}
		public String[] getInfo() {return this.info;}
	}
	
	public enum eString implements InjectTarget {
		LoadingPageImageAddress(imageTableAddress, "LoadingPageImageAddress"),
		
		MainFrameIconImageAddress(imageTableAddress, "MainFrameIconImageAddress"),
		;
		private String[] info; private String s;
		private eString(String table, String key) {this.info = new String[] { table, key };}
		public void setVal(String[] val) {this.s = val[0];}
		public String getVal() {return this.s;}
		public String[] getInfo() {return this.info;}
	}
	
	public enum eColor implements InjectTarget {
		DrawingPanelBackGroundColor(drawingPanelTableAddress, "DrawingPanelBackGroundColor"),
		
		ShapeBasicBorderColor(shapeTableAddress, "ShapeBasicBorderColor"),
		ShapeBasicFillColor(shapeTableAddress, "ShapeBasicFillColor"),
		;
		private String[] info; private Color c;
		private eColor(String table, String key) {this.info = new String[] { table, key };}
		public void setVal(String[] val) {this.c = new Color(Integer.parseInt(val[0]),Integer.parseInt(val[1]),Integer.parseInt(val[2]));}
		public Color getVal() {return this.c;}
		public String[] getInfo() {return this.info;}
	}
	
}
