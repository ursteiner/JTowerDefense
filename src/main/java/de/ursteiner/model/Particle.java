package de.ursteiner.model;

public class Particle {

	private final int a;
	private double t;
	private final int v0;
	private int x;
	private int y;
	private final int intensity = 255;

	public int getIntensity() {
		return this.intensity;
	}

	public Particle(int a, int v0) {
		super();
		this.a = a;
		this.v0 = v0;

		this.t = 0;
	}

	public int getWinkel() {
		return a;
	}

	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
	}

	public int getV0() {
		return v0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
