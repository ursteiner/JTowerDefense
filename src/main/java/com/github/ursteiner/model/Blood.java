package com.github.ursteiner.model;

import lombok.Data;
import java.awt.Point;

@Data
public class Blood {

    private final Point pos;
    private int red;

    public Blood(Point pos) {
        this.pos = pos;
        this.red = 255;
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
