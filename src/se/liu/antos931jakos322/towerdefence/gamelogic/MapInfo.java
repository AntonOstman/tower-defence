package se.liu.antos931jakos322.towerdefence.gamelogic;

import java.awt.*;
import java.util.List;
/**
 * Mapinfo contains the information of a map.
 * This includes the path, and the map diementions.
 */
public class MapInfo
{
    private Point dimensions;
    private List<Point> path;

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
