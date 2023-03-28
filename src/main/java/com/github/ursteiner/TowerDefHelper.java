package com.github.ursteiner;

import com.github.ursteiner.model.*;

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

		int x = (int) ((canonLength / d) * (a.x - t.x)) + t.x + 10 * GameData.ZOOM;
		int y = (int) ((canonLength / d) * (a.y - t.y)) + t.y + 10 * GameData.ZOOM;

		return new Point(x, y);
	}

	/**
	 *
	 * Check if point p1 is in the rectangle defined by p2 and width/height
	 *
	 * @param p1 point to check
	 * @param p2 start of the rectangle
	 * @param width width of the rectangle
	 * @param height height of the rectangle
	 * @return if p1 collides with the rectangle p2 & width / height
	 */
	public static boolean checkCollision(Point p1, Point p2, int width, int height) {
		return p1.x > p2.x && p1.x < p2.x + width && p1.y > p2.y && p1.y < p2.y + height;
	}

	public static boolean canUpgrade(Tower t, GameData gameData) {

		if (Type.DETECTOR.equals(t.getType())) {
			return false;
		}

		if (t.getLevel() == 1 && gameData.getMoney() >= 100 && gameData.getLevel() >= 4
				|| t.getLevel() == 2 && gameData.getMoney() >= 250 && gameData.getLevel() >= 10) {
			return true;
		}

		return false;
	}

	public static void calculateNewParticlePos(Particle p) {

		p.setT(p.getT() + 0.1);
		p.setX((int) (p.getV0() * Math.cos(p.getWinkel()) * p.getT()));
		p.setY((int) (p.getV0() * Math.sin(p.getWinkel()) * p.getT() - (g / 2) * (Math.pow(p.getT(), 2))));
	}

	public static boolean isPositionFree(Point p, GameData gameData, GameMap gameMap) {
		// no tower at position
		for (Tower tower : gameData.getBuildTowers()) {
			if (tower.getPos().x == p.x && tower.getPos().y == p.y) {
				return false;
			}
		}
		// no way point at position
		for (Point wp : gameMap.getWaypoints()) {
			if (wp.x == p.x && wp.y == p.y) {
				return false;
			}
		}

		return true;
	}
}
