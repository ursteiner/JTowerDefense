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
        waypoints.add(new Point(0 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(20 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(40 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(60 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(80 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(100 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(120 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(140 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(160 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(180 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(200 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(220 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(240 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(260 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(280 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(300 * GameData.ZOOM, 40 * GameData.ZOOM));
        waypoints.add(new Point(300 * GameData.ZOOM, 60 * GameData.ZOOM));
        waypoints.add(new Point(300 * GameData.ZOOM, 80 * GameData.ZOOM));
        waypoints.add(new Point(300 * GameData.ZOOM, 100 * GameData.ZOOM));
        waypoints.add(new Point(300 * GameData.ZOOM, 120 * GameData.ZOOM));
        waypoints.add(new Point(300 * GameData.ZOOM, 140 * GameData.ZOOM));
        waypoints.add(new Point(300 * GameData.ZOOM, 160 * GameData.ZOOM));
        waypoints.add(new Point(280 * GameData.ZOOM, 160 * GameData.ZOOM));
        waypoints.add(new Point(260 * GameData.ZOOM, 160 * GameData.ZOOM));
        waypoints.add(new Point(240 * GameData.ZOOM, 160 * GameData.ZOOM));
        waypoints.add(new Point(220 * GameData.ZOOM, 160 * GameData.ZOOM));
        waypoints.add(new Point(200 * GameData.ZOOM, 160 * GameData.ZOOM));
        waypoints.add(new Point(180 * GameData.ZOOM, 160 * GameData.ZOOM));
        waypoints.add(new Point(180 * GameData.ZOOM, 140 * GameData.ZOOM));
        waypoints.add(new Point(180 * GameData.ZOOM, 120 * GameData.ZOOM));
        waypoints.add(new Point(160 * GameData.ZOOM, 120 * GameData.ZOOM));
        waypoints.add(new Point(140 * GameData.ZOOM, 120 * GameData.ZOOM));
        waypoints.add(new Point(120 * GameData.ZOOM, 120 * GameData.ZOOM));
        waypoints.add(new Point(100 * GameData.ZOOM, 120 * GameData.ZOOM));
        waypoints.add(new Point(100 * GameData.ZOOM, 140 * GameData.ZOOM));
        waypoints.add(new Point(100 * GameData.ZOOM, 160 * GameData.ZOOM));
        waypoints.add(new Point(100 * GameData.ZOOM, 180 * GameData.ZOOM));
        waypoints.add(new Point(100 * GameData.ZOOM, 200 * GameData.ZOOM));
        waypoints.add(new Point(100 * GameData.ZOOM, 220 * GameData.ZOOM));
        waypoints.add(new Point(100 * GameData.ZOOM, 240 * GameData.ZOOM));
        waypoints.add(new Point(100 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(120 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(140 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(160 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(180 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(200 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(220 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(240 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(260 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(280 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(300 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(320 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(340 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(360 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(380 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(400 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(420 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(440 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(460 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(480 * GameData.ZOOM, 260 * GameData.ZOOM));
        waypoints.add(new Point(500 * GameData.ZOOM, 260 * GameData.ZOOM));

        sea.add(new Point(20 * GameData.ZOOM, 150 * GameData.ZOOM));
        sea.add(new Point(20 * GameData.ZOOM, 170 * GameData.ZOOM));
        sea.add(new Point(40 * GameData.ZOOM, 150 * GameData.ZOOM));
        sea.add(new Point(40 * GameData.ZOOM, 170 * GameData.ZOOM));

        sea.add(new Point(280 * GameData.ZOOM, 220 * GameData.ZOOM));
        sea.add(new Point(300 * GameData.ZOOM, 220 * GameData.ZOOM));
        sea.add(new Point(300 * GameData.ZOOM, 200 * GameData.ZOOM));
        sea.add(new Point(320 * GameData.ZOOM, 220 * GameData.ZOOM));
    }

}
