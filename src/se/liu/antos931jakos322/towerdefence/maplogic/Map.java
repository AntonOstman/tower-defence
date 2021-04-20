package se.liu.antos931jakos322.towerdefence.maplogic;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import se.liu.antos931jakos322.towerdefence.entities.BossEnemy;
import se.liu.antos931jakos322.towerdefence.entities.Enemy;
import se.liu.antos931jakos322.towerdefence.entities.GenericEnemy;
import se.liu.antos931jakos322.towerdefence.entities.SpeedEnemy;
import se.liu.antos931jakos322.towerdefence.other.HelperFunctions;
import se.liu.antos931jakos322.towerdefence.entities.Tower;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    private List<Tower> towers;
    private List<Enemy> enemies;
    private int health;
    private int money;
    private List<MapListener> mapListeners;

    private final static int WAVE_TIMER = 50;
    private int tickCounter = 1;
    private int waveLevel = 0;


    public Map(final int width, final int height) throws IOException {
	this.money = 10;
	this.width = width;
	this.height = height;
	this.tiles = new Tile[height][width];
	this.enemies = new ArrayList<>();
	this.towers = new ArrayList<>();
	this.mapListeners = new ArrayList<>();
    	this.health = 100;
    	createMap();
    	loadMap();
    }
    public void addListener(MapListener listener){
        mapListeners.add(listener);
    }

    public void notifyListeners(){
        for(MapListener listener: mapListeners){
            listener.mapChanged();
	}
    }

    public Enemy getClosestEnemy(Point towerPos, int range){
        Enemy closestEnemy = null;

        if (!enemies.isEmpty()){
            double closestDistance = Double.POSITIVE_INFINITY;
		// for every enemy check if another is closer and is in range.
	    	// If one closer is found save that enemy, do that for all enemies
	    	// if no enemies in range are found return null
	    for(Enemy enemy: enemies){
                Point enemyPos = enemy.getPosition();
                Point relativePoint = new Point(towerPos.x - enemyPos.x,towerPos.y - enemyPos.y);
                double currentDistance = HelperFunctions.pythagoras(relativePoint.x, relativePoint.y);
		if(currentDistance < closestDistance && currentDistance <= range){
		    closestEnemy = enemy;
		    closestDistance = currentDistance;

		}
	    }
	}
	return closestEnemy;
    }

    public void activateTowers(){
        for (Tower tower: towers){
            Enemy closestEnemy = getClosestEnemy(tower.getPosition(), tower.getRange());

            if (tower.attackAndReturnIsFatal(closestEnemy)){
                removeEnemy(closestEnemy);
	    }
	}
    }

    public void takeDamage(int damage){
	health -= damage;
    }

    public int getHealth() {
	return health;
    }

    public void enemyMove(){

	Iterator<Enemy> i = enemies.iterator();
        while(i.hasNext()){
            Enemy enemy = i.next();
            int damage = enemy.moveAndTakeDamage(path);
            if(damage == 1){
                i.remove();
		takeDamage(1);
            }
	}
    }

    public void removeEnemy(Enemy enemy){
	money += enemy.getRewardMoney();
        enemies.remove(enemy);
    }

    public void tick(){
        activateTowers();
        addEnemy();
	enemyMove();
	notifyListeners();
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

    private void loadMap() throws IOException {
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
		tiles[h][w] = new Tile(w, h, null, TileType.GRASS);
	    }
	}
	// Creates the road tiles
	for (Point pathTile : selectedMap.getPath()){
	    tiles[pathTile.y][pathTile.x] = new Tile(pathTile.x, pathTile.y, null, TileType.ROADO);
	}
	// Updates the path in this class
	path = selectedMap.getPath();


}

    public void addEnemy(){

        // The first number is the starting speed, the secound is the changing speed
	int spawningspeedGeneric = (int)(50- (tickCounter / 100.0));
	if (spawningspeedGeneric <= 2){ spawningspeedGeneric = 2;}
        if( tickCounter % spawningspeedGeneric == 0){
	    enemies.add(new GenericEnemy());
	}

	int spawningspeedBoss = (int)(1000- (tickCounter / 100.0));
	if (spawningspeedBoss <= 2){ spawningspeedBoss = 2;}
	if( tickCounter % spawningspeedBoss == 0){
	    enemies.add(new BossEnemy());
	}

	int spawningspeedSpeedy = (int)(70- (tickCounter / 100.0));
	if (spawningspeedSpeedy <= 2){ spawningspeedSpeedy = 2;}
	if( tickCounter % spawningspeedSpeedy == 0){
	    enemies.add(new SpeedEnemy());
	}

        tickCounter ++;


    }

    public void addTower(Tower tower){
	money -= tower.getCost();
	towers.add(tower);
    }

    public boolean canAffordAndPlaceTower(Tower tower){
	Point towerPos = tower.getPosition();
	TileType desiredPlacementTile  = tiles[towerPos.y][towerPos.x].getTileType();
	if (desiredPlacementTile != TileType.GRASS) {
	    return false;
	}
        if (money - tower.getCost() < 0){
            return false;
	}

	return true;
    }

    public List<Tower> getTowers() {
	return towers;
    }

    public List<Enemy> getEnemies() {
	return enemies;
    }

    public Tile getTile(int x, int y){
        return tiles[y][x];
    }

    public int getMoney() {
	return money;
    }

    public Point getDimension() {
	return new Point(width, height);
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
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
}
