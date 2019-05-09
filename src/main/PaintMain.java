package main;

import global.InjectEnums;
import globalTimer.GlobalTimer;
import view.PaintMainFrame;

public class PaintMain {

	public static void main(String[] args) {
		System.out.println("SUPER-ULTRA-PAINT V2");
		
		new GlobalTimer();//시간은 흐른다!
		
		InjectEnums.injectPreEnums();
		
//		LoadingPage loadingPage = new LoadingPage(Color.white, eString.LoadingPageImageAddress.getVal());
//		GlobalTimer.addTimer("loadingPageOn", eInt.LeastLoadingPageOnTime.getVal()); //최소 타이틀 보여주는 시간.
		
		InjectEnums.injectAllEnums();
		
		PaintMainFrame mf = new PaintMainFrame();
		mf.initialize();
		
//		GlobalTimer.waitTimeOut("loadingPageOn");
//		loadingPage.LoadingPageOff();
	}
}
//그룹화는 Rectangle2D.union같은걸로 할 수 있다. 한 덩어리의 뉴쉐입을 주는듯. 교수님 코드는 공부할 만 한듯. 텍스트 만들면 좋아하시겠다.
//그릴 부분만 다시 그리게 하기.
//엑션핸들러 스태틱으로 만들어서, 처리.
//타임 액션 넣기. 그래픽 컴포넌트
//보더를 그냥 필 두번으로 하자.
//도구 손!
// - rotator  쉐이프가 선택되면, 선택된것을 타겟으로 하는 앵커를 만든다.
//로테이트된 앵커들 만들려면, 기울기를 저장해서, 그릴 때마다, 원래대로하고, 돌리고, 해야한다.
//move and zoom 하는중. 무버가 경우를 나누어 하게 하기.


