package se.liu.antos931jakos322.towerdefence.maplogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapInfo
{
    private Point dimensions;
    private List<Point> path = new ArrayList<>();

    public MapInfo(final Point dimensions, final List<Point> path) {
	this.dimensions = dimensions;
	this.path = path;
    }

    public Point getDimensions() {
	return dimensions;
    }

    public List<Point> getPath() {
	return path;
    }
}
