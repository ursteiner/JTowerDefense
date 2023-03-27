package com.github.ursteiner.model;

import lombok.Data;

@Data
public class Particle {

	private final int a;
	private double t;
	private final int v0;
	private int x;
	private int y;
	private final int intensity = 255;

	public Particle(int a, int v0) {
		super();
		this.a = a;
		this.v0 = v0;

		this.t = 0;
	}

	public int getWinkel() {
		return a;
	}
}
