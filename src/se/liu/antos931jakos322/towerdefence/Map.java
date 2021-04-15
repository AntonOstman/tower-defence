package se.liu.antos931jakos322.towerdefence;

/**
 *
 * Map on which the player places towers and enemies walk with the intent to damage
 *
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Map
{
    private Tile[][] tiles;
    private List<Point> path = new ArrayList<>();
    private int width, height;

    private List<Tower> towers;
    private List<Enemy> enemies;
    private int health;
    private List<MapListener> mapListeners;

    public Map(final int width, final int height) {

	this.width = width;
	this.height = height;
	this.tiles = new Tile[height][width];
	this.enemies = new ArrayList<>();
	this.towers = new ArrayList<>();
	this.mapListeners = new ArrayList<>();
    	this.health = 100;
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
            double closestDistance = Double.POSITIVE_INFINITY; // starting distance

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
                enemyCleanUp(closestEnemy);
	    }
	}
    }

    public List<Point> createMapPath(Point startPoint){
        //assuming not more than 2 "ROADO" path tiles are next to eachother
	//returns the path that enemies should take to damage the player
	// also assumes road starts on the left side of the screen
	// also assumes road goes one step to the right direct after start
	Tile prevTile = tiles[startPoint.y][startPoint.x];
	Tile currentTile = tiles[startPoint.y][startPoint.x+1];
	List<Point> mapPath = new ArrayList<>();
	int curX = currentTile.getPosX();
	int curY = currentTile.getPosY();

	mapPath.add(new Point(curX-1,curY)); // prev tile
	mapPath.add(new Point(curX,curY)); // current tile
	boolean foundAllRoadTiles = false;
    	while(!foundAllRoadTiles){
		// iden är basically bara att kolla x-1 sen x+1 sen y-1 sen y+1

	    curX = currentTile.getPosX();
	    curY = currentTile.getPosY();

	    if (curX == width - 1){
	        foundAllRoadTiles = true;
		break;
	    }
	    Tile y1 = tiles[curY+1][curX];
	    Tile y2 = tiles[curY-1][curX];
	    Tile x1 = tiles[curY][curX+1];
	    Tile x2 = tiles[curY][curX-1];
		// måste definitivt göras finare.....
	    if (y1.getTileType() == TileType.ROADO && !y1.equals(prevTile)){
	        mapPath.add(new Point(curX, curY+1));
	        prevTile = currentTile;
	    	currentTile = y1;
		System.out.println("y1");
	    }
	    else if (y2.getTileType() == TileType.ROADO && !y2.equals(prevTile)){
		mapPath.add(new Point(curX, curY-1));
		prevTile = currentTile;
		currentTile = y2;
		System.out.println("y2");
	    }
	    else if (x1.getTileType() == TileType.ROADO && !x1.equals(prevTile)){
		mapPath.add(new Point(curX+1, curY));
		prevTile = currentTile;
		currentTile = x1;
		System.out.println("x1");
	    }
	    else if (x2.getTileType() == TileType.ROADO && !x2.equals(prevTile)){
		mapPath.add(new Point(curX-1, curY));
		prevTile = currentTile;
		currentTile = x2;
		System.out.println("x2");
	    }
	    else{
		foundAllRoadTiles = true;
	    }
	}
	return mapPath;

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

    public void enemyCleanUp(Enemy enemy){
	enemies.remove(enemy);

    }

    public void tick(){
        activateTowers();
	enemyMove();
	notifyListeners();
    }
    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public void addTower(Tower tower){
        towers.add(tower);
    }


    public List<Tower> getTowers() {
	return towers;
    }


    public List<Enemy> getEnemies() {
	return enemies;
    }

    public void hardCodedMap(){
	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {
		tiles[h][w] = new Tile(w, h, null, TileType.GRASS);
	    }
	}
    }


    public void hardRoad(){
    //hard coded path

        tiles[5][0] = new Tile(0, 5, null, TileType.ROADO);
	tiles[5][1] = new Tile(1, 5, null, TileType.ROADO);
	tiles[5][2] = new Tile(2, 5, null, TileType.ROADO);
	tiles[5][3] = new Tile(3, 5, null, TileType.ROADO);
	tiles[5][4] = new Tile(4, 5, null, TileType.ROADO);
	tiles[5][5] = new Tile(5, 5, null, TileType.ROADO);
	tiles[6][5] = new Tile(5, 6, null, TileType.ROADO);
	tiles[7][5] = new Tile(5, 7, null, TileType.ROADO);
	tiles[7][6] = new Tile(6, 7, null, TileType.ROADO);
	tiles[7][7] = new Tile(7, 7, null, TileType.ROADO);
	tiles[7][8] = new Tile(8, 7, null, TileType.ROADO);
	tiles[7][9] = new Tile(9, 7, null, TileType.ROADO);

	//path.add(new Point(-1,5));
//
//	path.add(new Point(0,5));
//	path.add(new Point(1,5));
//	path.add(new Point(2,5));
//	path.add(new Point(3,5));
//	path.add(new Point(4,5));
//	path.add(new Point(5,5));
//	path.add(new Point(5,6));
//	path.add(new Point(5,7));
//	path.add(new Point(6,7));
//	path.add(new Point(7,7));
//	path.add(new Point(8,7));
//	path.add(new Point(9,7));
	//path.add(new Point(10,7));
	path = createMapPath(new Point(0,5)); // fullösnings men vad ska man göra
	System.out.println(createMapPath(new Point(0,5)).equals(path));
	System.out.println(createMapPath(new Point(0,5)));
	System.out.println(path);
    }



    public Tile getTile(int x, int y){
        return tiles[y][x];
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
