package de.ursteiner.model;

import java.awt.Point;

public class Hint {

	private String text;
	private Point p;

	public Hint(String text, Point p) {
		super();
		this.text = text;
		this.p = p;
	}

	public String getText() {
		return text;
	}

	public Point getP() {
		return p;
	}

}
