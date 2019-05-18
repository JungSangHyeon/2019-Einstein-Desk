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

//�����췯�� ����Ͽ� ����ϱ� ����.
//�ڵ��� �� ����Ʈ �ƴѳ� �̺�Ʈ�� ���� �ʿ䰡 ����. ��� �� ��.
//���� ����� ��.... �� ������ �巡�� ��� �ǰ� ����. �׸���, ���� ������ �����. ��Ŀ����. 
//�׸��� ��Ŀ �ؼ� ��ȭ�ָ�, ���鵵 �˴� ��ȭ��.

//�����ϰ� �����.
//function���δ� staticȭ. enum����. �̹����� �ϳ����� �����ϰ� ���̵�� �ҷ����� �ϱ�.
//�κ� ����Ʈ, �̺�Ʈ, �׷��� �÷ο� ����.

//finish tool Bar & Shape Bar //consider use pre made Toolbar 

//D&D get Same Component
//Add On Animation in A Container -> processor�� painter, time action�� ���Ĺ�����. �̸��� �𸣰ڴµ�. ������ ��ó�� ����������. 

//Ÿ�� �׼� �ֱ�. �׷��� ������Ʈ force
//�׸� �κи� �ٽ� �׸��� �ϱ�.

//log -> branch. like a tree
//Use System Clip Board 
//Consider use mouse Button 2 as D&D Controller 
//portal shape

//tip
//�׷�ȭ�� Rectangle2D.union�����ɷ� �� �� �ִ�. �� ����� �������� �ִµ�. ������ �ڵ�� ������ �� �ѵ�. �ؽ�Ʈ ����� �����Ͻðڴ�.
// - rotator  �������� ���õǸ�, ���õȰ��� Ÿ������ �ϴ� ��Ŀ�� �����.
//������Ʈ�� ��Ŀ�� �������, ���⸦ �����ؼ�, �׸� ������, ��������ϰ�, ������, �ؾ��Ѵ�.

//long startTime = System.currentTimeMillis();//�̰� ����Ͽ�, �ӵ��� ���� �β��� ������ ���� ���� �� �ִ�.
//someMethodWhichYouWantToProfile();
//long endTime = System.currentTimeMillis();
//System.out.println("Total execution time: " + (endTime-startTime) + "ms"); 