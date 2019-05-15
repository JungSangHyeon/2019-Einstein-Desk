package penSettingPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PenSettingPanel extends JPanel{

	int borderThick = 1;
	
	boolean on = false;
	public  void off() {on = false;action();}
	public void onOff() {on = (!on);action();}
	private void action() {this.setVisible(on);}
	
	public PenSettingPanel() {
		this.setVisible(false);
		this.setLayout(null);
//		PaletPanel PP = new PaletPanel(Constants.penColors, "pen");
//		PP.setBounds(5,40,312,260);//조정 할 것.
//		this.add(PP);
		SizeBar SB = new SizeBar("pen");
		SB.setBounds(5,340,312,110);//조정 할 것.
		this.add(SB);
	}
	
	public void paintComponent(Graphics g) {
		
		g.setColor(new Color(204,204,204));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(new Color(242,242,242));
		g.fillRect(borderThick,borderThick, getWidth()-borderThick*2, getHeight()-borderThick*2);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.PLAIN, 16));
		g.drawString("Colors", 10, 25);
		g.drawString("Size", 10, 335);
	}
	
}
