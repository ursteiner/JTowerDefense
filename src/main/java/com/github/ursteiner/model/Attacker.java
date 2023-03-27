package com.github.ursteiner.model;

import lombok.Data;
import java.awt.Point;

@Data
public class Attacker {
	private final Point pos;
	private int vx;
	private int vy;
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

	public void hit(int damage) {
		energy += damage;
		if (energy >= maxEnergy) {
			dead = true;
		}
	}
}
