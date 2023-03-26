package com.github.ursteiner;

import com.github.ursteiner.model.Tower;
import com.github.ursteiner.model.Type;
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

    @Test
    void canUpgrade(){
        Tower t1 = new Tower(new Point(), 0,0, Type.DETECTOR);
        boolean canUpgrade1 = TowerDefHelper.canUpgrade(t1, 100, 0);
        Assertions.assertEquals(false, canUpgrade1, "Detector tower cant be upgraded");

        Tower t2 = new Tower(new Point(), 0,0, Type.BLUE);
        boolean canUpgrade2 = TowerDefHelper.canUpgrade(t2, 150, 1);
        Assertions.assertEquals(false, canUpgrade2, "Tower can be upgraded");

        Tower t3 = new Tower(new Point(), 0,0, Type.BLUE);
        boolean canUpgrade3 = TowerDefHelper.canUpgrade(t3, 250, 2);
        Assertions.assertEquals(false, canUpgrade3, "Tower can be upgraded");

        Tower t4 = new Tower(new Point(), 0,0, Type.BROWN);
        boolean canUpgrade4 = TowerDefHelper.canUpgrade(t4, 100, 2);
        Assertions.assertEquals(false, canUpgrade4, "Tower can't be upgraded");
    }
}