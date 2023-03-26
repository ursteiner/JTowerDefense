package de.ursteiner.model;

import java.awt.Point;

public class Blood {

    private final Point pos;
    private int red;

    public Blood(Point pos) {
	this.pos = pos;
	this.red = 255;
    }

    public Point getPos() {
	return pos;
    }

    public void dryBlood() {
	if (red > 100) {
	    red--;
	}
    }

    public int getBloodColor() {
	return red;
    }
}
