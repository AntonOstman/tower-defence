package se.liu.antos931jakos322.towerdefence.maplogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapInfo
{
    private Point dimentions;
    private List<Point> path = new ArrayList<>();

    public MapInfo(final Point dimentions, final List<Point> path) {
	this.dimentions = dimentions;
	this.path = path;
    }

    public Point getDimentions() {
	return dimentions;
    }

    public List<Point> getPath() {
	return path;
    }
}
