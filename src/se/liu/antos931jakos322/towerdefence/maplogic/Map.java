package se.liu.antos931jakos322.towerdefence.maplogic;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * Map contain the core background information of the game.
 * It structures the tiles which the game is played on.
 * It contains information related to the game such as how much money the player has
 *
 */

public class Map
{
    private Tile[][] tiles;
    private List<Point> path = new ArrayList<>();
    private int width, height;


    public Map(final int width, final int height)  {
	this.width = width;
	this.height = height;
	this.tiles = new Tile[height][width];

	//createMap();
    	//loadMap(); loadmap throws IO excpetion and the correct usage is probably to try loading in tester class?
    }


    private void createMap() throws IOException{
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	// Creates the path for map 1
	List<Point> path1 = Arrays.asList(new Point(0,4),
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
					  new Point(9,2));

	// Creates the path for map 2
	List<Point> path2 = Arrays.asList(new Point(0,5),
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
					  new Point(9,5));

	List<Point> path3 = Arrays.asList(new Point(0,5),
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
					  new Point(9,7));



	// Creates a list for mapInfo objects, MapInfo have height width and the path as input parameters.
	List<MapInfo> mapInfoList = Arrays.asList(new MapInfo(new Point(10, 10), path1),
						  new MapInfo(new Point(10, 10), path2),
						  new MapInfo(new Point(10, 10), path3));
	String mapInfoAsJson = gson.toJson(mapInfoList);

	FileWriter writer = new FileWriter("src/se/liu/antos931jakos322/towerdefence/maplogic/maps.json");
	writer.write(mapInfoAsJson);
	writer.close();
    }

    public void loadMap() throws IOException {
        int selectedMapIndex = 0;

	List<MapInfo> mapInfoList = new ArrayList<>();
	// create Gson instance
	Gson gson = new Gson();
	// create a reader
	Reader reader = Files.newBufferedReader(Paths.get("src/se/liu/antos931jakos322/towerdefence/maplogic/maps.json"));
	// convert JSON array to object
	mapInfoList = new Gson().fromJson(reader, new TypeToken<List<MapInfo>>() {}.getType());
	reader.close();

	MapInfo selectedMap = mapInfoList.get(selectedMapIndex);
	Point dimentions = selectedMap.getDimentions();
	// Creates the base map with all grass tiles
	for (int h = 0; h < dimentions.y; h++) {
	    for (int w = 0; w < dimentions.x; w++) {
		tiles[h][w] = new Tile(w, h,  TileType.GRASS);
	    }
	}
	// Creates the road tiles
	for (Point pathTile : selectedMap.getPath()){
	    tiles[pathTile.y][pathTile.x] = new Tile(pathTile.x, pathTile.y, TileType.ROAD);
	}
	// Updates the path in this class
	path = selectedMap.getPath();


    }


    public Tile getTile(Point pos){
        return tiles[pos.y][pos.x];
    }

    public Point getDimension() {
	return new Point(width, height);
    }


    @Override public String toString() {
	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {
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
        return path.get(path.size() - 1);

    }
}
