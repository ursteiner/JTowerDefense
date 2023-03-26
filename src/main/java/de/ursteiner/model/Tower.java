package de.ursteiner.model;

import java.awt.Point;

public class Tower {

	public static final int MAX_UPGRADE_LEVEL = 3;

	private final Point pos;
	private int level;
	private Point canonEndpoint = new Point();
	private Point lastAttackerPos = new Point();
	private int radius;
	private int reloadTimer;
	private int canonColor;
	private int strength;
	private int timeToWait = 20;
	// a^2 + b^2 = c^2
	private double canonLength = 12;
	private final Type type;
	private Shot shots;
	private int upgradeProgress = 0;
	private boolean upgrade = false;

	public static final int MAX_CANNON_LENGTH = 12;

	public Tower(Point pos, int radius, int reloadTimer, Type type) {
		super();
		this.pos = pos;
		this.radius = radius;
		this.reloadTimer = reloadTimer;
		this.type = type;
		this.level = 1;
		this.strength = 1;
		this.canonEndpoint = pos;

		this.canonColor = 0;
	}

	public void doUpgrade() {
		if (upgradeProgress <= type.getUpgradeTime()) {
			upgradeProgress++;
		} else {
			level++;
			radius += 20;
			timeToWait -= 5;
			strength += 2;
			upgrade = false;
			upgradeProgress = 0;
		}
	}

	public void upgrade() {
		this.upgrade = true;
	}

	public int getUpgradeProgress() {
		return upgradeProgress;
	}

	public boolean isInUpgrade() {
		return upgrade;
	}

	public boolean shoot() {
		if (reloadTimer >= timeToWait) {
			reloadTimer = 0;
			canonColor = 255;
			return true;
		} else {
			reloadTimer++;
			return false;
		}

	}

	public Point getPos() {
		return pos;
	}

	public int getRadius() {
		return radius;
	}

	public Point getCanonEndpoint() {
		return canonEndpoint;
	}

	public void setCanonEndpoint(Point canonEndpoint) {
		this.canonEndpoint = canonEndpoint;
	}

	public int getLevel() {
		return level;
	}

	public int getStrength() {
		return strength;
	}

	public int getCanonColor() {
		return canonColor;
	}

	public void setCanonColor(int canonColor) {
		this.canonColor = canonColor;
	}

	public Shot getShots() {
		return shots;
	}

	public void setShot(Shot shot) {
		this.shots = shot;
	}

	public Type getType() {
		return type;
	}

	public double getCanonLength() {
		return canonLength;
	}

	public void setCanonLength(double canonLength) {
		this.canonLength = canonLength;
	}

	public Point getLastAttackerPos() {
		return lastAttackerPos;
	}

	public void setLastAttackerPos(Point lastAttackerPos) {
		this.lastAttackerPos = lastAttackerPos;
	}

}
