package com.github.ursteiner;

import com.github.ursteiner.model.GameData;
import com.github.ursteiner.model.GameMap;
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

        Assertions.assertFalse(collidesT1, "P1 should not collide");

        Point p3 = new Point(1,1);
        Point p4 = new Point(0, 0);

        boolean collidesT2 = TowerDefHelper.checkCollision(p3, p4, 10, 10);

        Assertions.assertTrue(collidesT2, "P3 should collide");
    }

    @Test
    void canUpgrade(){
        GameData gameData1 = new GameData();
        gameData1.setLevel(0);
        gameData1.setMoney(100);
        Tower t1 = new Tower(new Point(), 0,0, Type.DETECTOR);
        boolean canUpgrade1 = TowerDefHelper.canUpgrade(t1, gameData1);
        Assertions.assertFalse(canUpgrade1, "Detector tower cant be upgraded");

        GameData gameData2 = new GameData();
        gameData2.setLevel(1);
        gameData2.setMoney(150);
        Tower t2 = new Tower(new Point(), 0,0, Type.BLUE);
        boolean canUpgrade2 = TowerDefHelper.canUpgrade(t2, gameData2);
        Assertions.assertFalse(canUpgrade2, "Tower can be upgraded");

        GameData gameData3 = new GameData();
        gameData3.setLevel(3);
        gameData3.setMoney(250);
        Tower t3 = new Tower(new Point(), 0,0, Type.BLUE);
        boolean canUpgrade3 = TowerDefHelper.canUpgrade(t3, gameData3);
        Assertions.assertFalse(canUpgrade3, "Tower can be upgraded");

        GameData gameData4 = new GameData();
        gameData4.setLevel(2);
        gameData4.setMoney(100);
        Tower t4 = new Tower(new Point(), 0,0, Type.BROWN);
        boolean canUpgrade4 = TowerDefHelper.canUpgrade(t4, gameData4);
        Assertions.assertFalse(canUpgrade4, "Tower can't be upgraded");
    }

    @Test
    void isPositionFree(){
        //free
        Point p1 = new Point(0,0);
        GameData gameData = new GameData();
        GameMap gameMap = new GameMap();
        boolean positionFree = TowerDefHelper.isPositionFree(p1, gameData, gameMap);
        Assertions.assertTrue(positionFree, "Position should be free");

        //already a tower at position
        Point p2 = new Point(0,0);
        GameData gameData2 = new GameData();
        gameData2.getBuildTowers().add(new Tower(new Point(0,0),0,0,Type.BLUE));
        GameMap gameMap2 = new GameMap();
        boolean positionFree2 = TowerDefHelper.isPositionFree(p2, gameData2, gameMap2);
        Assertions.assertFalse(positionFree2, "Position should not be free");

        //already a waypoint at position
        Point p3 = new Point(0,0);
        GameData gameData3 = new GameData();
        GameMap gameMap3 = new GameMap();
        gameMap3.getWaypoints().add(new Point(0,0));
        boolean positionFree3 = TowerDefHelper.isPositionFree(p3, gameData3, gameMap3);
        Assertions.assertFalse(positionFree3, "Position should not be free");
    }
}