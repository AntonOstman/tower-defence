package se.liu.antos931jakos322.towerdefence.gamelogic;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import se.liu.antos931jakos322.towerdefence.userinterface.StartMenu;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public GameMap()  {
	this.dimensions = null;
	this.tiles = null;
	this.path = null;
	this.mapInfo = null;

	//loadMap(); loadmap throws IO excpetion and the correct usage is probably to try loading in tester class?
	// cool usage would be to load map with the "selected map index" you want to load
	// that way the player could choose map
    }


    public void createMap() throws IOException{
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	// OBS when creating the path, the path is using a margin on one tile over the edge to make for a smooth enering and exit
	// Creates the path for map 1 "cicle"
	List<Point> path1 = Arrays.asList(new Point(-1,4),
					  new Point(0,4),
					  new Point(1,4),
					  new Point(2,4),
					  new Point(3,4),
					  new Point(4,4),
					  new Point(5,4),
					  new Point(6,4),
					  new Point(7,4),
					  new Point(7,5),
					  new Point(7,6),
					  new Point(7,7),
					  new Point(7,8),
					  new Point(6,8),
					  new Point(5,8),
					  new Point(5,7),
					  new Point(5,6),
					  new Point(5,5),
					  new Point(5,4),
					  new Point(5,3),
					  new Point(5,2),
					  new Point(6,2),
					  new Point(7,2),
					  new Point(8,2),
					  new Point(9,2),
					  new Point(10,2),
					  new Point(11,2),
					  new Point(12,2),
					  new Point(13,2),
					  new Point(14,2),
					  new Point(15,2));

	// Creates the path for map 2 "Curve"
	List<Point> path2 = Arrays.asList(new Point(-1,5),
					  new Point(0,5),
					  new Point(1,5),
					  new Point(2,5),
					  new Point(3,5),
					  new Point(4,5),
					  new Point(5,5),
					  new Point(5,6),
					  new Point(5,7),
					  new Point(6,7),
					  new Point(7,7),
					  new Point(7,6),
					  new Point(7,5),
					  new Point(8,5),
					  new Point(9,5),
					  new Point(10,5),
					  new Point(10,4),
					  new Point(10,3),
					  new Point(11,3),
					  new Point(12,3),
					  new Point(12,3),
					  new Point(12,4),
					  new Point(12,5),
					  new Point(13,5),
					  new Point(14,5),
					  new Point(15,5));

	//"Flying"
	List<Point> path3 = Arrays.asList(new Point(-1,5),
					  new Point(0,5),
					  new Point(1,5),
					  new Point(2,5),
					  new Point(3,5),
					  new Point(4,5),
					  new Point(4,5),
					  new Point(4,5),
					  new Point(5,5),
					  new Point(6,5),
					  new Point(4,7),
					  new Point(5,7),
					  new Point(6,7),
					  new Point(7,7),
					  new Point(8,7),
					  new Point(9,7),
					  new Point(6,9),
					  new Point(7,9),
					  new Point(8,9),
					  new Point(9,9),
					  new Point(10,9),
					  new Point(11,9),
					  new Point(12,9),
					  new Point(13,9),
					  new Point(14,9),
					  new Point(15,9));
	//"Zigzag"
	List<Point> path4 = Arrays.asList(new Point(-1,4),
					  new Point(0,5),
					  new Point(3,12),
					  new Point(6,9),
					  new Point(9,11),
					  new Point(12,4),
					  new Point(14,7),
					  new Point(15,7));



	// Creates a list for mapInfo objects, MapInfo have height width and the path as input parameters.
	List<MapInfo> mapInfo = Arrays.asList(new MapInfo(new Point(15, 15), path1),
					      new MapInfo(new Point(15, 15), path2),
					      new MapInfo(new Point(15, 15), path4),
					      new MapInfo(new Point(15, 15), path3));

	String mapInfoAsJson = gson.toJson(mapInfo);

	FileWriter writer = new FileWriter("maps.json");
	writer.write(mapInfoAsJson);
	writer.close();


	}

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

	StartMenu.startLogger(logger);
	Reader reader = Files.newBufferedReader(Paths.get("maps.json"));


	// convert JSON array to object
	// if loading fails we should handle it by creating the map
	//List<MapInfo> mapInfo;
	try {
	    mapInfo = new Gson().fromJson(reader, new TypeToken<List<MapInfo>>()
	    {
	    }.getType());
	}
	catch (JsonSyntaxException jsonSyntaxException){
	    logger.log(Level.SEVERE, "maps file has been corrupted and can not be read", jsonSyntaxException);
	    throw new IOException("maps file has been corrupted and can not be read \n" + jsonSyntaxException);
	}
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

    public Point getLastTile(){
        return path.get(path.size()-1);
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
