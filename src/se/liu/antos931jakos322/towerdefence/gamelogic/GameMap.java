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
 * It contains information related to the game such as how much money the player has
 *
 */

public class GameMap
{
    private Tile[][] tiles;
    private List<Point> path;
    private Point dimensions;
    private List<MapInfo> mapInfo;

    public GameMap()  {
	this.dimensions = null;
	this.tiles = null;
	this.path = null;
	this.mapInfo = null;

    }

	/** When maps.json has been read a map can be loaded into the game.
	 * @param selectedMapIndex selects the map to load
	 *
	 * */

    public void loadMap(int selectedMapIndex){
	// Updates varibles
	MapInfo selectedMap = mapInfo.get(selectedMapIndex);
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
	// The path uses one tile margin that should not be shown
	for (int i = 1; i < path.size()-1; i++) {
	    Point pathTile = path.get(i);
	    tiles[pathTile.y][pathTile.x] = new Tile(new Point(pathTile.x, pathTile.y), TileType.ROAD);
	}
    }

    public void readMap() throws IOException {
	// load the map resource
        URL url = ClassLoader.getSystemResource("maps/maps.json");
	InputStream inputStream = url.openStream();

	Reader reader = new BufferedReader(new InputStreamReader(inputStream));
	// convert JSON array to object

	mapInfo = new Gson().fromJson(reader, new TypeToken<List<MapInfo>>() {}.getType());
	reader.close();


    }

    public int getNumberOfMaps(){
        return mapInfo.size();
    }

    public Tile getTile(Point pos){
        return tiles[pos.y][pos.x];
    }

    public Point getDimensions() {
        int width = dimensions.x;
        int height = dimensions.y;

	return new Point(width, height);
    }

    @Override public String toString() {
	for (int h = 0; h < dimensions.y; h++) {
	    for (int w = 0; w < dimensions.x; w++) {
		System.out.print(tiles[h][w]);
	    }
	    System.out.println();
	}
    return null;
    }

    public Point getPath(int index) {
	return path.get(index);
    }

    public int getLastPath(){
        return path.size() - 1;
    }

    public int getWidth(){
        int width = dimensions.x;
        return width;
    }

    public int getHeight(){
	int height = dimensions.y;
	return height;
    }

}
