package rightClickPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import fPaint.FShadow;
import rightClick.RightClickMenu;
import rightClickPanel.ShapeColorSetter.Target;
import zStuff_GCPanel.NullPeekabooPanel;
import zStuff_GCPanel_LineSetting.ColorConstant;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class ShapeColorSettingPanel extends NullPeekabooPanel{
	private static final long serialVersionUID = 3141453841486592808L;

	int leftRightGap = 5;
	Target nowTarget;
	
	public ShapeColorSettingPanel(Target target) {
		nowTarget = target;
		
		ShapeColorSetter headColorSettingPanel = new ShapeColorSetter(nowTarget);
		headColorSettingPanel.setSize(10, 1);
		
		Point point = RightClickMenu.getNowPoint();
		int thisWidth = headColorSettingPanel.getWidth() + leftRightGap*2;
		
		if(nowTarget == Target.Fill) {this.setBounds(point.x, point.y, thisWidth, 236);}
		else if(nowTarget == Target.Border) {this.setBounds(point.x+45, point.y, thisWidth, 236);}
		else if(nowTarget == Target.Text) {this.setBounds(point.x+45*2, point.y, thisWidth, 236);}
		
		this.setPanelBackgroundColor(Color.WHITE);
		this.setBorderColor(new Color(198,198,198));
		this.setBorderPaint(true);
		this.setborderThick(1);
		
		headColorSettingPanel.setGCLocation(this.getX() + 5, this.getY() + 40);
		headColorSettingPanel.addItems(ColorConstant.headThemeColors);
		this.add(headColorSettingPanel);
		
		headColorSettingPanel = new ShapeColorSetter(nowTarget);
		headColorSettingPanel.setGCLocation(this.getX() + 5, this.getY() + 40 + 20);
		headColorSettingPanel.setSize(10, 5);
		headColorSettingPanel.addItems(ColorConstant.themeColors);
		this.add(headColorSettingPanel);
		
		headColorSettingPanel = new ShapeColorSetter(nowTarget);
		headColorSettingPanel.setGCLocation(this.getX() + 5, this.getY() + 40 + 20 +117);
		headColorSettingPanel.setSize(10, 1);
		headColorSettingPanel.addItems(ColorConstant.rainbowColors);
		this.add(headColorSettingPanel);
		
		headColorSettingPanel = new ShapeColorSetter(nowTarget);
		headColorSettingPanel.setGCLocation(this.getX()+1 + 5, this.getY() + 40 + 20 +147);
		headColorSettingPanel.setPixelSize(thisWidth-1 - 10, 23);
		headColorSettingPanel.setPixelGap(0, 0);
		headColorSettingPanel.setSize(1, 1);
		Color[] noColor = {new Color(0,0,0,0)};
		headColorSettingPanel.addItems(noColor);
		this.add(headColorSettingPanel);
		
		this.addFunction(new FShadow());
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
			gc.setTempBorderColor(null);
			gc.setTempFillColor(null);
			gc.setTempTextColor(null);
		}
		super.mouseMoved(e);
	}
	@Override
	public void paint(Graphics2D g2d) {
		super.paint(g2d);
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font(null, Font.PLAIN, 16));
		g2d.drawString("Theme Color", this.getX()+10, this.getY()+25);
		g2d.drawString("Rainbow", this.getX()+10, this.getY()+161);
		g2d.drawString("No Fill", this.getX()+10, this.getY()+224);
	}
}
