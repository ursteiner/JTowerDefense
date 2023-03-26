package com.github.ursteiner.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shot {

	private Point p2;
	private float opacity;
	private boolean bloodMode;

	private final Random randomGenerator = new Random();

	private final List<Particle> particles = new ArrayList<>();

	public Shot(Point p2, boolean bloodMode) {
		super();
		this.p2 = p2;
		this.opacity = 1.0f;
		this.bloodMode = bloodMode;

		for (int i = 1; i < 10; i++) {
			particles.add(new Particle(randomGenerator.nextInt(30) + 40, randomGenerator.nextInt(40)));
		}

	}

	public Point getP2() {
		return p2;
	}

	public void fadeShot() {
		if (opacity > 0) {
			opacity -= 0.25f;
		}
	}

	public float getOpacity() {
		return opacity;
	}

	public List<Particle> getParticles() {
		return particles;
	}

	public boolean isBloodMode() {
		return bloodMode;
	}

}
