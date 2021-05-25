package se.liu.antos931jakos322.towerdefence.gamelogic;

import java.awt.*;
import java.util.List;
/**
 * Mapinfo contains the information of a map.
 * This includes the path, and the map diementions.
 * Mapinfo is used to load maps.json file and does not explicitely get constructed when not creating the maps.json file.
 */
public class MapInfo
{
    private Point dimensions = null;
    private List<Point> path = null;

    public Point getDimensions() {
	return dimensions;
    }

    public List<Point> getPath() {
	return path;
    }
}
