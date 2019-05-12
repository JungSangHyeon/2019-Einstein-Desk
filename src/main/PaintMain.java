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

//그룹화는 Rectangle2D.union같은걸로 할 수 있다. 한 덩어리의 뉴쉐입을 주는듯. 교수님 코드는 공부할 만 한듯. 텍스트 만들면 좋아하시겠다.

//그릴 부분만 다시 그리게 하기.

//타임 액션 넣기. 그래픽 컴포넌트
//도구 손!

// - rotator  쉐이프가 선택되면, 선택된것을 타겟으로 하는 앵커를 만든다.
//로테이트된 앵커들 만들려면, 기울기를 저장해서, 그릴 때마다, 원래대로하고, 돌리고, 해야한다.
//move and zoom 하는중. 무버가 경우를 나누어 하게 하기.
//드래그드랍, 세모나 동글이도 고 모양대로 들어가고, 고 모양대로 나오게 해주라.
//무버를 밖으로 못 빼나.

//Icon Invisible Part or change Color

//D&D Component Delete
//D&D get Same Component
//Share Mover
