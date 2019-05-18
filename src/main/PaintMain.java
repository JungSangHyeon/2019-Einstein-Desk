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

//스케쥴러를 사용하여 통신하기 생각.
//핸드일 때 셀렉트 아닌놈도 이벤트를 받을 필요가 있음. 고려 할 것.
//툴바 만드는 중.... 휠 누르면 드래그 드랍 되게 하자. 그리고, 빨랑 셀렉터 만들어. 앵커하자. 
//그리고 앵커 해서 변화주면, 점들도 죄다 변화줘.

//간단하게 만들기.
//function전부다 static화. enum으로. 이미지는 하나씩만 저장하고 아이디로 불러오게 하기.
//부분 페인트, 이벤트, 그래픽 플로우 정리.

//finish tool Bar & Shape Bar //consider use pre made Toolbar 

//D&D get Same Component
//Add On Animation in A Container -> processor와 painter, time action을 합쳐버리자. 이름은 모르겠는디. 아이작 템처럼 만들어버리자. 

//타임 액션 넣기. 그래픽 컴포넌트 force
//그릴 부분만 다시 그리게 하기.

//log -> branch. like a tree
//Use System Clip Board 
//Consider use mouse Button 2 as D&D Controller 
//portal shape

//tip
//그룹화는 Rectangle2D.union같은걸로 할 수 있다. 한 덩어리의 뉴쉐입을 주는듯. 교수님 코드는 공부할 만 한듯. 텍스트 만들면 좋아하시겠다.
// - rotator  쉐이프가 선택되면, 선택된것을 타겟으로 하는 앵커를 만든다.
//로테이트된 앵커들 만들려면, 기울기를 저장해서, 그릴 때마다, 원래대로하고, 돌리고, 해야한다.

//long startTime = System.currentTimeMillis();//이걸 사용하여, 속도에 따른 두께를 가지는 선을 만들 수 있다.
//someMethodWhichYouWantToProfile();
//long endTime = System.currentTimeMillis();
//System.out.println("Total execution time: " + (endTime-startTime) + "ms"); 