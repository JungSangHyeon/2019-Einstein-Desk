package su_ScrollPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class SU_ScrollPanel extends JPanel {

	JPanel innerPanel;
	JScrollPane vertical;

	int basicSpeed = 10;
	
	public SU_ScrollPanel(JPanel panel) {
		this.setLayout(new BorderLayout());
		innerPanel = panel;
		vertical = new JScrollPane(innerPanel);
//		vertical.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));//안보여준다.
		this.add(vertical);
//		this.setBackground(Color.RED);
		vertical.getVerticalScrollBar().setUnitIncrement(basicSpeed);//스크롤 속도설정.
	}
	public void setSpeed(int speed) {
		this.basicSpeed=speed;
	}
	public void setDeep(int deep) {
		innerPanel.setPreferredSize(new Dimension(this.getWidth(), deep));
//		vertical.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		vertical.revalidate();
	}
}
