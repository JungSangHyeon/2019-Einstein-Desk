package PDR_2P_Shape;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import zStuff_Shape.APDRShape;

public class StarNShape extends APDRShape {
	private static final long serialVersionUID = 6528960320070190886L;

	private static final int nSpikes = 10,  smallFactor =2;
	private static final double SPIKINESS = 0.5;

	public Shape newTwoPointShape(Point2D.Float p1, Point2D.Float p2) {
		return makePath(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
	}

	private static GeneralPath makePath(float x, float y, float w, float h) {
		float ctrX = x + w/2, ctrY = y + h/ 2;
		int nPoints = nSpikes * 2 + 1;
		double xPoint[] = new double[nPoints];
		double yPoint[] = new double[nPoints];

		for (int i = 0; i < nPoints; i++) {
			double wRadius = (i % 2 == 0) ? w : (w * SPIKINESS);
			double hRadius = (i % 2 == 0) ? h : (h * SPIKINESS);
			double angle = (i * 360.0) / (2 * nSpikes);
			xPoint[i] = ctrX + wRadius * Math.cos(Math.toRadians(angle - 90))/smallFactor;
			yPoint[i] = ctrY + hRadius * Math.sin(Math.toRadians(angle - 90))/smallFactor;
		}

		GeneralPath path = new GeneralPath();
		path.moveTo(xPoint[0], yPoint[0]);
		for(int i=1; i<nPoints; i++) {path.lineTo(xPoint[i], yPoint[i]);}
		path.closePath();
		return path;
	}
}
