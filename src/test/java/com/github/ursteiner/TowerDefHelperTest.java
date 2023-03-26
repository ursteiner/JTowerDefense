package com.github.ursteiner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

class TowerDefHelperTest {

    @Test
    void getDistance() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(10, 0);

        int distanceT1 = TowerDefHelper.getDistance(p1,p2);
        Assertions.assertEquals(10, distanceT1, "Distance should be 10");

        Point p3 = new Point(0,0);
        Point p4 = new Point(0,0);

        int distanceT2 = TowerDefHelper.getDistance(p3,p4);
        Assertions.assertEquals(0, distanceT2, "Distance should be 0");
    }

    @Test
    void checkCollision() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(10, 0);

        boolean collidesT1 = TowerDefHelper.checkCollision(p1, p2, 10, 10);

        Assertions.assertEquals(false, collidesT1, "P1 should not collide");

        Point p3 = new Point(1,1);
        Point p4 = new Point(0, 0);

        boolean collidesT2 = TowerDefHelper.checkCollision(p3, p4, 10, 10);

        Assertions.assertEquals(true, collidesT2, "P3 should collide");
    }
}