package main;

import global.InjectEnums;
import globalTimer.GlobalTimer;
import view.PaintMainFrame;

public class PaintMain {

	public static void main(String[] args) {
		System.out.println("SUPER-ULTRA-PAINT V2");
		
		new GlobalTimer();//�ð��� �帥��!
		
		InjectEnums.injectPreEnums();
		
//		LoadingPage loadingPage = new LoadingPage(Color.white, eString.LoadingPageImageAddress.getVal());
//		int id = GlobalTimer.addTimer(eInt.LeastLoadingPageOnTime.getVal()); //�ּ� Ÿ��Ʋ �����ִ� �ð�.
		
		InjectEnums.injectAllEnums();
		
		PaintMainFrame mf = new PaintMainFrame();
		mf.initialize();
		
//		GlobalTimer.waitTimeOut(id);
//		loadingPage.LoadingPageOff();
	}
	
}

//�׷�ȭ�� Rectangle2D.union�����ɷ� �� �� �ִ�. �� ����� �������� �ִµ�. ������ �ڵ�� ������ �� �ѵ�. �ؽ�Ʈ ����� �����Ͻðڴ�.

//�׸� �κи� �ٽ� �׸��� �ϱ�.

//Ÿ�� �׼� �ֱ�. �׷��� ������Ʈ
//���� ��!

// - rotator  �������� ���õǸ�, ���õȰ��� Ÿ������ �ϴ� ��Ŀ�� �����.
//������Ʈ�� ��Ŀ�� �������, ���⸦ �����ؼ�, �׸� ������, ��������ϰ�, ������, �ؾ��Ѵ�.
//move and zoom �ϴ���. ������ ��츦 ������ �ϰ� �ϱ�.
//�巡�׵��, ���� �����̵� �� ����� ����, �� ����� ������ ���ֶ�.
//������ ������ �� ����.

//D&D Component Delete
//Icon Invisible Part
//D&D get Same Component
//Share Mover
//remove select Method from Data
//D&D for Many. edit hand tool
