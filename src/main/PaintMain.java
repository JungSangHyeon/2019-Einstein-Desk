package main;

import java.awt.RenderingHints;

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

//finish tool Bar & Shape Bar //consider use pre made Toolbar 
//Icon Invisible Part or change Color

//make Share mover
//make Share rotate, resizer.

//D&D get Same Component
//Add On Animation in A Container -> processor와 painter, time action을 합쳐버리자. 이름은 모르겠는디. 아이작 템처럼 만들어버리자. 

//타임 액션 넣기. 그래픽 컴포넌트 force
//그릴 부분만 다시 그리게 하기.

//log -> branch. like a tree
//Use System Clip Board 
//Consider use mouse Button 2 as D&D Controller 

//tip
//그룹화는 Rectangle2D.union같은걸로 할 수 있다. 한 덩어리의 뉴쉐입을 주는듯. 교수님 코드는 공부할 만 한듯. 텍스트 만들면 좋아하시겠다.
// - rotator  쉐이프가 선택되면, 선택된것을 타겟으로 하는 앵커를 만든다.
//로테이트된 앵커들 만들려면, 기울기를 저장해서, 그릴 때마다, 원래대로하고, 돌리고, 해야한다.
//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);//beatifull shape. 못생긴? 쉐입을 부드럽고 이쁘게 만들어준다. 진짜 이것만해도 확 이쁘네. 와우.

//long startTime = System.currentTimeMillis();//이걸 사용하여, 속도에 따른 두께를 가지는 선을 만들 수 있다.
//someMethodWhichYouWantToProfile();
//long endTime = System.currentTimeMillis();
//System.out.println("Total execution time: " + (endTime-startTime) + "ms"); 