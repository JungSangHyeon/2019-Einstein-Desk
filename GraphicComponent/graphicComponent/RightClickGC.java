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
					new FImageAndTextPaint_Y("ä���", textSize, "RightClickPanelImage/Fill.png"), 
					new FReleaseAddPanel(Target.Fill), 
					new FShowMouseOn(), 
	        },
			BorderColor = { //Border 
					new FImageAndTextPaint_Y("������", textSize, "RightClickPanelImage/Border.png"), 
					new FReleaseAddPanel(Target.Border), 
					new FShowMouseOn(), 
	        },
			TextColor = { //Text
					new FImageAndTextPaint_Y("�ؽ�Ʈ", textSize, "RightClickPanelImage/Text.png"), 
					new FReleaseAddPanel(Target.Text), 
					new FShowMouseOn(), 
	        },
			Top = { //zOrder_Top 
					new FImageAndTextPaint_X("�� ���� ������", textSize, "RightClickPanelImage/Top.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.TOP)
	        },
			Front = { //zOrder_Front
					new FImageAndTextPaint_X("������ ������", textSize, "RightClickPanelImage/Front.png"), 
					new FShowMouseOn(),
					new FMethodIvoker(reservedMethod.FRONT)
	        },
			Hell = { //zOrder_Hell 
					new FImageAndTextPaint_X("�� �ڷ� ������", textSize, "RightClickPanelImage/Hell.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.HELL)
	        },
			Back = { //zOrder_Back
					new FImageAndTextPaint_X("�ڷ� ������", textSize, "RightClickPanelImage/Back.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.BACK)
	        },
			Copy = { //Copy
					new FImageAndTextPaint_X("����", textSize, "RightClickPanelImage/Copy.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.COPY)
	        },
			Paste = { //paste
					new FImageAndTextPaint_X("�ٿ��ֱ�", textSize, "RightClickPanelImage/Paste.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.PASTE)
	        },
			Group = { //Group
					new FImageAndTextPaint_X("�׷�ȭ", textSize, "RightClickPanelImage/Group.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.GROUP)
	        },
			UnGroup = { //UnGroup
					new FImageAndTextPaint_X("�׷�ȭ ����", textSize, "RightClickPanelImage/UnGroup.png"), 
					new FShowMouseOn(), 
					new FMethodIvoker(reservedMethod.UNGROUP)
	        }
	;
}
