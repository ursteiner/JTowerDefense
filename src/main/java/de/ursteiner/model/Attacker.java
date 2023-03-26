package de.ursteiner.model;

import java.awt.Point;

public class Attacker {
	private final Point pos;
	private int vX;
	private int vY;
	private int waypointIndex;
	private final int maxEnergy;
	private int energy = 0;
	private boolean dead = false;
	private final Type type;
	private final boolean invisible;
	private boolean detected = true;

	private boolean marchedThrough = false;

	public Attacker(Point pos, int energy, Type type, boolean invisible) {
		super();
		this.pos = pos;
		this.maxEnergy = energy;
		this.type = type;
		this.invisible = invisible;

		this.waypointIndex = 0;
	}

	public Point getPos() {
		return pos;
	}


	public boolean isDead() {
		return dead;
	}

	public int getEnergy() {
		return energy;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void hit(int damage) {
		energy += damage;
		if (energy >= maxEnergy) {
			dead = true;
		}
	}

	public boolean isMarchedThrough() {
		return marchedThrough;
	}

	public void setMarchedThrough(boolean marchedThrough) {
		this.marchedThrough = marchedThrough;
	}

	public int getWaypointIndex() {
		return waypointIndex;
	}

	public void setWaypointIndex(int waypointIndex) {
		this.waypointIndex = waypointIndex;
	}

	public Type getType() {
		return type;
	}

	public int getVx() {
		return vX;
	}

	public void setVx(int vX) {
		this.vX = vX;
	}

	public int getVy() {
		return vY;
	}
	public void setVy(int vY) {
		this.vY = vY;
	}

	public boolean isInvisible() {
		return invisible;
	}

	public boolean isDetected() {
		return detected;
	}

	public void setDetected(boolean detected) {
		this.detected = detected;
	}

}
