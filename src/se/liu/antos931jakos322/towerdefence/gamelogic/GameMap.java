package se.liu.antos931jakos322.towerdefence.gamelogic;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;


/**
 *
 * Map contain the core background information of the game.
 * It structures the tiles which the game is played on.
 *
 * GameMap also loads and reads the maps.json file which contains all the json MapInfo objects.
 * Which means GameMap loads the MapInfo objects dimensions and the path.
 * Path is a sorted list. This means the index 0 in path is the first position on the path, index 1 is the second position on the path etc.
 */

public class GameMap
{
    private Tile[][] tiles;
    private List<Point> path;
    private Point dimensions;
    private List<MapInfo> mapInfoContainer;

    public GameMap()  {
	this.dimensions = null;
	this.tiles = null;
	this.path = null;
	this.mapInfoContainer = null;

    }

	/**
	 * When maps.json has been read a map can be loaded into the game.
	 *
	 * @param selectedMapIndex selects the map to load
	 * */

    public void loadMap(int selectedMapIndex){
	// first gets the mapInfo object which should be loaded and replace all fields with the mapinfo scale
	MapInfo selectedMap = mapInfoContainer.get(selectedMapIndex);
	this.dimensions = selectedMap.getDimensions();
	this.tiles = new Tile[dimensions.y][dimensions.x];
	this.path = selectedMap.getPath();

	// Creates the base map with all grass tiles
	for (int h = 0; h < dimensions.y; h++) {
	    for (int w = 0; w < dimensions.x; w++) {
		tiles[h][w] = new Tile(new Point(w, h), TileType.GRASS);
	    }
	}

	// Overrides some of the grass tiles with road blocks
	// The path uses one tile margin that should not be shown so enemies dont instantly show and instead "walk into" the map
	for (int i = 1; i < path.size()-1; i++) {
	    Point pathTile = path.get(i);
	    tiles[pathTile.y][pathTile.x] = new Tile(new Point(pathTile.x, pathTile.y), TileType.ROAD);
	}
    }

    /**
     * reads the maps.json file which loads all mapInfo objects into the field mapInfoContainer
     *
     * @throws IOException if reading the file fails
     */
    public void readMap() throws IOException {
	// load the map resource
        URL url = ClassLoader.getSystemResource("maps/maps.json");
	InputStream inputStream = url.openStream();
	Reader reader = new BufferedReader(new InputStreamReader(inputStream));

	// convert JSON array to object.
	// The maps.json file has been created in before hand by code now removed.
	// But we can still read the Json file which reads the "old" MapInfo objects
	mapInfoContainer = new Gson().fromJson(reader, new TypeToken<List<MapInfo>>() {}.getType());
	reader.close();

    }


    /**
     * Returns the dimensions but as a new Point object
     * @return the game dimensions
     */
    public Point getDimensions() {
        int width = dimensions.x;
        int height = dimensions.y;
	return new Point(width, height);
    }

    /**
     * Returns the Position of the specified path index
     * Eg. to return the first postion of the path, index should be 0
     *
     * @param index the path to return.
     * @return Point2D object of the specifed path index
     */
    public Point getPathPosition(int index) {
	return path.get(index);
    }

    public int getLastPath(){
        return path.size() - 1;
    }

    public Tile getTile(Point pos){
	return tiles[pos.y][pos.x];
    }

    public int getNumberOfMaps(){
	return mapInfoContainer.size();
    }

    public int getWidth(){
        return dimensions.x;
    }

    public int getHeight(){
	return dimensions.y;
    }

}
