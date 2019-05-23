package main;

import doUndo.RedoUndo;
import global.InjectEnums;
import globalTimer.GlobalTimer;
import view.PaintMainFrame;
import z_order.Z_Order;
import z_order.Z_Order.SendTo;

public class PaintMain {

	public static void main(String[] args) {
		System.out.println("ULTRA-PAINT");
		
		Z_Order.changeZOrder(SendTo.Top);
		
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

//part paint, / img add / 앵커가 보여졌을 때만 작동하게 하자. / cmc>?

//로테이트 가운데에 각도 출력 되게 하자. 그림으로!
//스케쥴러를 사용하여 통신하기 생각.

//이미지는 하나씩만 저장하고 아이디로 불러오게 하기.

//finish tool Bar & Shape Bar //consider use pre made Toolbar 

//D&D get Same Component
//Add On Animation in A Container -> processor와 painter, time action(force)을 합쳐버리자. 아이작 템처럼 만들어버리자. 

//log -> branch. like a tree
//Use System Clip Board 
//portal shape

//tip
//그룹화는 Rectangle2D.union같은걸로 할 수 있다. 한 덩어리의 뉴쉐입을 주는듯. 교수님 코드는 공부할 만 한듯. 텍스트 만들면 좋아하시겠다.
// - rotator  쉐이프가 선택되면, 선택된것을 타겟으로 하는 앵커를 만든다.
//로테이트된 앵커들 만들려면, 기울기를 저장해서, 그릴 때마다, 원래대로하고, 돌리고, 해야한다.

//long startTime = System.currentTimeMillis();//이걸 사용하여, 속도에 따른 두께를 가지는 선을 만들 수 있다.
//someMethodWhichYouWantToProfile();
//long endTime = System.currentTimeMillis();
//System.out.println("Total execution time: " + (endTime-startTime) + "ms"); 