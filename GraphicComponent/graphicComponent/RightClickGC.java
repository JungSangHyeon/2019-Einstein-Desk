package graphicComponent;

import fImagePaint.FImageAndTextPaint_X;
import fImagePaint.FImageAndTextPaint_Y;
import fPaint.FReleaseAddPanel;
import fPaint.FShowMouseOn;
import fSystem.FMethodIvoker;
import rightClickPanel.ShapeColorSetter.Target;
import zStuff_Function.AFunction;
import zStuff_Function.FInvoker.reservedMethod;

public class RightClickGC {

	static int textSize = 12;

	public static AFunction[] //add Functions Here
			FillColor = { 
					new FImageAndTextPaint_Y("채우기", textSize, "RightClickPanelImage/Fill.png"), 
					new FReleaseAddPanel(Target.Fill), 
					new FShowMouseOn(), 
	        },
			BorderColor = { //Border 
					new FImageAndTextPaint_Y("윤곽선", textSize, "RightClickPanelImage/Border.png"), 
					new FReleaseAddPanel(Target.Border), 
					new FShowMouseOn(), 
	        },
			TextColor = { //Text
					new FImageAndTextPaint_Y("텍스트", textSize, "RightClickPanelImage/Text.png"), 
					new FReleaseAddPanel(Target.Text), 
					new FShowMouseOn(), 
	        },
			Top = { //zOrder_Top 
					new FImageAndTextPaint_X("맨 위로 보내기", textSize, "RightClickPanelImage/Top.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.TOP)
	        },
			Front = { //zOrder_Front
					new FImageAndTextPaint_X("앞으로 보내기", textSize, "RightClickPanelImage/Front.png"), 
					new FShowMouseOn(),
					new FMethodIvoker(reservedMethod.FRONT)
	        },
			Hell = { //zOrder_Hell 
					new FImageAndTextPaint_X("맨 뒤로 보내기", textSize, "RightClickPanelImage/Hell.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.HELL)
	        },
			Back = { //zOrder_Back
					new FImageAndTextPaint_X("뒤로 보내기", textSize, "RightClickPanelImage/Back.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.BACK)
	        },
			Copy = { //Copy
					new FImageAndTextPaint_X("복사", textSize, "RightClickPanelImage/Copy.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.COPY)
	        },
			Paste = { //paste
					new FImageAndTextPaint_X("붙여넣기", textSize, "RightClickPanelImage/Paste.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.PASTE)
	        },
			Group = { //Group
					new FImageAndTextPaint_X("그룹화", textSize, "RightClickPanelImage/Group.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.GROUP)
	        },
			UnGroup = { //UnGroup
					new FImageAndTextPaint_X("그룹화 해제", textSize, "RightClickPanelImage/UnGroup.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.UNGROUP)
	        }
	;
}
