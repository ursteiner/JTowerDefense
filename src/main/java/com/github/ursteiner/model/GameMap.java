package com.github.ursteiner.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameMap {

    private final List<Point> waypoints = new ArrayList<>();
    private final List<Point> sea = new ArrayList<>();

    public GameMap(){
        waypoints.add(new Point(0, 40));
        waypoints.add(new Point(20, 40));
        waypoints.add(new Point(40, 40));
        waypoints.add(new Point(60, 40));
        waypoints.add(new Point(80, 40));
        waypoints.add(new Point(100, 40));
        waypoints.add(new Point(120, 40));
        waypoints.add(new Point(140, 40));
        waypoints.add(new Point(160, 40));
        waypoints.add(new Point(180, 40));
        waypoints.add(new Point(200, 40));
        waypoints.add(new Point(220, 40));
        waypoints.add(new Point(240, 40));
        waypoints.add(new Point(260, 40));
        waypoints.add(new Point(280, 40));
        waypoints.add(new Point(300, 40));
        waypoints.add(new Point(300, 60));
        waypoints.add(new Point(300, 80));
        waypoints.add(new Point(300, 100));
        waypoints.add(new Point(300, 120));
        waypoints.add(new Point(300, 140));
        waypoints.add(new Point(300, 160));
        waypoints.add(new Point(280, 160));
        waypoints.add(new Point(260, 160));
        waypoints.add(new Point(240, 160));
        waypoints.add(new Point(220, 160));
        waypoints.add(new Point(200, 160));
        waypoints.add(new Point(180, 160));
        waypoints.add(new Point(180, 140));
        waypoints.add(new Point(180, 120));
        waypoints.add(new Point(160, 120));
        waypoints.add(new Point(140, 120));
        waypoints.add(new Point(120, 120));
        waypoints.add(new Point(100, 120));
        waypoints.add(new Point(100, 140));
        waypoints.add(new Point(100, 160));
        waypoints.add(new Point(100, 180));
        waypoints.add(new Point(100, 200));
        waypoints.add(new Point(100, 220));
        waypoints.add(new Point(100, 240));
        waypoints.add(new Point(100, 260));
        waypoints.add(new Point(120, 260));
        waypoints.add(new Point(140, 260));
        waypoints.add(new Point(160, 260));
        waypoints.add(new Point(180, 260));
        waypoints.add(new Point(200, 260));
        waypoints.add(new Point(220, 260));
        waypoints.add(new Point(240, 260));
        waypoints.add(new Point(260, 260));
        waypoints.add(new Point(280, 260));
        waypoints.add(new Point(300, 260));
        waypoints.add(new Point(320, 260));
        waypoints.add(new Point(340, 260));
        waypoints.add(new Point(360, 260));
        waypoints.add(new Point(380, 260));
        waypoints.add(new Point(400, 260));
        waypoints.add(new Point(420, 260));
        waypoints.add(new Point(440, 260));
        waypoints.add(new Point(460, 260));
        waypoints.add(new Point(480, 260));
        waypoints.add(new Point(500, 260));

        sea.add(new Point(20, 150));
        sea.add(new Point(20, 170));
        sea.add(new Point(40, 150));
        sea.add(new Point(40, 170));

        sea.add(new Point(280, 220));
        sea.add(new Point(300, 220));
        sea.add(new Point(300, 200));
        sea.add(new Point(320, 220));
    }

}
