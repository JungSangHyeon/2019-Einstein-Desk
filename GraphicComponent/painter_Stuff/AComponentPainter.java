package painter_Stuff;

import java.awt.Graphics;
import java.awt.Shape;
import java.io.Serializable;

public abstract class AComponentPainter  implements Serializable{
	private static final long serialVersionUID = -2266921661341345665L;
	public abstract void paintComponent(Graphics g, Shape shape);
}
