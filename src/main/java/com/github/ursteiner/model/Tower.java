package com.github.ursteiner.model;

import lombok.Data;
import java.awt.Point;
@Data
public class Tower {

	public static final int MAX_UPGRADE_LEVEL = 3;
	public static final int TOWER_WIDTH = 20 * GameData.ZOOM;
	public static final int TOWER_HEIGHT = 20 * GameData.ZOOM;
	private final Point pos;
	private int level;
	private Point canonEndpoint = new Point();
	private Point lastAttackerPos = new Point();
	private int radius;
	private int reloadTimer;
	private int canonColor;
	private int strength;
	private int timeToWait = 20;
	private double canonLength = 12 * GameData.ZOOM;
	private final Type type;
	private Shot shots;
	private int upgradeProgress = 0;
	private boolean upgrade = false;

	public static final int MAX_CANNON_LENGTH = 12 * GameData.ZOOM;

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
			radius += 20 * GameData.ZOOM;
			timeToWait -= 5;
			strength += 2;
			upgrade = false;
			upgradeProgress = 0;
		}
	}

	public void upgrade() {
		this.upgrade = true;
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

	public Shot getShots() {
		return shots;
	}

	public void setShot(Shot shot) {
		this.shots = shot;
	}
}
