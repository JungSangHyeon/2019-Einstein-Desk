package main;

import global.InjectEnums;
import globalTimer.GlobalTimer;
import view.PaintMainFrame;

public class PaintMain {

	public static void main(String[] args) {
		System.out.println("SUPER-ULTRA-PAINT V2");
		
		new GlobalTimer();//Time Starts Moving
		
		InjectEnums.injectPreEnums();
		
//		LoadingPage loadingPage = new LoadingPage(Color.white, eString.LoadingPageImageAddress.getVal());
//		int id = GlobalTimer.addTimer(eInt.LeastLoadingPageOnTime.getVal()); //min Title Time
		
		InjectEnums.injectAllEnums();
		
		PaintMainFrame mf = new PaintMainFrame();
		mf.initialize();
		
//		GlobalTimer.waitTimeOut(id);
//		loadingPage.LoadingPageOff();
	}
	
}
//work
//add all shape to item
//finish tool Bar & Shape Bar 

//make Share mover
//make Share rotate, resizer.

//Icon Invisible Part or change Color

//D&D get Same Component

//Ÿ�� �׼� �ֱ�. �׷��� ������Ʈ force
//�׸� �κи� �ٽ� �׸��� �ϱ�.

//Consider use mouse Button 2 as D&D Controller 

//tip
//�׷�ȭ�� Rectangle2D.union�����ɷ� �� �� �ִ�. �� ����� �������� �ִµ�. ������ �ڵ�� ������ �� �ѵ�. �ؽ�Ʈ ����� �����Ͻðڴ�.
// - rotator  �������� ���õǸ�, ���õȰ��� Ÿ������ �ϴ� ��Ŀ�� �����.
//������Ʈ�� ��Ŀ�� �������, ���⸦ �����ؼ�, �׸� ������, ��������ϰ�, ������, �ؾ��Ѵ�.