package main;

import global.InjectEnums;
import globalTimer.GlobalTimer;
import redoUndo.RedoUndo;
import view.PaintMainFrame;
import zOrder.Z_Order;
import zOrder.Z_Order.SendTo;

public class PaintMain {

	public static void main(String[] args) {
//		Vector<Integer> a =  new Vector<Integer> ();
//		a.add(1);
//		a.add(2);
//		a.add(3);
//		a.add(4);
//		a.add(5);
//		SuperVector.change(a, 1, 4);
//		System.out.println(a.get(0));
//		System.out.println(a.get(1));
//		System.out.println(a.get(2));
//		System.out.println(a.get(3));
//		System.out.println(a.get(4));
		
		System.out.println("ULTRA-PAINT");
		
		new GlobalTimer();//Time Starts Moving
		
		InjectEnums.injectPreEnums();
		
//		LoadingPage loadingPage = new LoadingPage(Color.white, eString.LoadingPageImageAddress.getVal());
//		int id = GlobalTimer.addTimer(eInt.LeastLoadingPageOnTime.getVal()); //min Title Time
		
		InjectEnums.injectAllEnums();
		
		PaintMainFrame mf = new PaintMainFrame();
		mf.initialize();
		
//		GlobalTimer.waitTimeOut(id);
//		loadingPage.LoadingPageOff();
		
		RedoUndo.setFirst();
	}
	
}
//work 

//part paint, / img add / ��Ŀ�� �������� ���� �۵��ϰ� ����. / cmc>?

//������Ʈ ����� ���� ��� �ǰ� ����. �׸�����!
//�����췯�� ����Ͽ� ����ϱ� ����.

//�̹����� �ϳ����� �����ϰ� ���̵�� �ҷ����� �ϱ�.

//finish tool Bar & Shape Bar //consider use pre made Toolbar 

//D&D get Same Component
//Add On Animation in A Container -> processor�� painter, time action(force)�� ���Ĺ�����. ������ ��ó�� ����������. 

//log -> branch. like a tree
//Use System Clip Board 
//portal shape

//tip
//�׷�ȭ�� Rectangle2D.union�����ɷ� �� �� �ִ�. �� ����� �������� �ִµ�. ������ �ڵ�� ������ �� �ѵ�. �ؽ�Ʈ ����� �����Ͻðڴ�.
// - rotator  �������� ���õǸ�, ���õȰ��� Ÿ������ �ϴ� ��Ŀ�� �����.
//������Ʈ�� ��Ŀ�� �������, ���⸦ �����ؼ�, �׸� ������, ��������ϰ�, ������, �ؾ��Ѵ�.

//long startTime = System.currentTimeMillis();//�̰� ����Ͽ�, �ӵ��� ���� �β��� ������ ���� ���� �� �ִ�.
//someMethodWhichYouWantToProfile();
//long endTime = System.currentTimeMillis();
//System.out.println("Total execution time: " + (endTime-startTime) + "ms"); 