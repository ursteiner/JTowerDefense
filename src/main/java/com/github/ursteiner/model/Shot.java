package com.github.ursteiner.model;

import lombok.Data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Data
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

	public void fadeShot() {
		if (opacity > 0) {
			opacity -= 0.25f;
		}
	}
}
