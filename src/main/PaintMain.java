package main;

import java.awt.Color;

import global.InjectEnums;
import global.InjectEnums.eInt;
import globalTimer.GlobalTimer;
import loadingPage.LoadingPage;
import view.PaintMainFrame;

public class PaintMain {

	public static void main(String[] args) {
		System.out.println("Einstein Desk");
		new GlobalTimer();//Time Starts Moving
		
		InjectEnums.injectPreEnums();
		LoadingPage loadingPage = new LoadingPage(Color.white, "Image/a.png");
//		LoadingPage loadingPage = new LoadingPage(Color.white, eString.LoadingPageImageAddress.getVal());
		
		int id = GlobalTimer.addTimer(eInt.LeastLoadingPageOnTime.getVal()); //min Title Time
		InjectEnums.injectAllEnums();
		
		PaintMainFrame mf = new PaintMainFrame();
		mf.initialize();
		mf.setVisible(true);
//		RedoUndo.setFirst();
		
		GlobalTimer.waitTimeOut(id);
		loadingPage.LoadingPageOff();
		
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