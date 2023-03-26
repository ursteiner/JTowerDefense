package de.ursteiner;

import de.ursteiner.model.Particle;
import de.ursteiner.model.Tower;
import de.ursteiner.model.Type;

import java.awt.Point;

public class TowerDefHelper {

	private static final double g = 9.81d;

	/**
	 * d = Wurzel((x1-x2)^2+(y1-y2)^2)
	 * 
	 * @param p1 Point to compare
	 * @param p2 Point to compare
	 * @return distance
	 */
	public static int getDistance(Point p1, Point p2) {
		return (int) Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	public static Point rotateCannon(Point a, Point t, double canonLength) {
		double d = TowerDefHelper.getDistance(t, a);

		int x = (int) ((canonLength / d) * (a.x - t.x)) + t.x + 10;
		int y = (int) ((canonLength / d) * (a.y - t.y)) + t.y + 10;

		return new Point(x, y);
	}

	public static boolean checkCollision(Point p1, Point p2, int width, int height) {
		return p1.x > p2.x && p1.x < p2.x + width && p1.y > p2.y && p1.y < p2.y + height;
	}

	public static boolean canUpgrade(Tower t, int money, int level) {

		if (Type.DETECTOR.equals(t.getType())) {
			return false;
		}

		if (t.getLevel() == 1 && money >= 100 && level >= 4) {
			return true;
		} else if (t.getLevel() == 2 && money >= 250 && level >= 10) {
			return true;
		}

		return false;
	}

	public static void calculateNewParticlePos(Particle p) {

		p.setT(p.getT() + 0.1);
		p.setX((int) (p.getV0() * Math.cos(p.getWinkel()) * p.getT()));
		p.setY((int) (p.getV0() * Math.sin(p.getWinkel()) * p.getT() - (g / 2) * (Math.pow(p.getT(), 2))));
	}
}
