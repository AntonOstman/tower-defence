package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class Map
{
    private Tile[][] tiles;
    private List<Point> path = new ArrayList<>();
    private int width, height;

    private List<AbstractTower> towers;
    private List<Enemy> enemies;


    public Map(final int width, final int height) {

	this.width = width;
	this.height = height;
	this.tiles = new Tile[height][width];
	this.enemies = new ArrayList<>();
	this.towers = new ArrayList<>();
	addEnemy();
    }

    public Enemy getClosestEnemy(Point towerPos){
        Enemy closestEnemy = null;
        if (!enemies.isEmpty()){

            double closestDistance = Double.POSITIVE_INFINITY;

	    for(Enemy enemy: enemies){
                Point enemyPos = enemy.getPoint();
                double currentDistance = Math.pow((enemyPos.x),2) + Math.pow((enemyPos.y),2);

		if(currentDistance < closestDistance){
		    closestEnemy = enemy;
		    closestDistance = currentDistance;

		}

	    }

	}
	return closestEnemy;
    }


    public void removeEnemy(Entity enemy){
        enemies.remove(enemy);
    }

    public void activateTowers(){
        for (AbstractTower tower: towers){
            Enemy closestEnemy = getClosestEnemy(tower.getPosistion());
            if (tower.isFatalAttack(closestEnemy)){
                enemyCleanUp(closestEnemy);
	    }
	}
    }

    public void enemyMove(){
        for(Enemy enemy: enemies){
            enemy.move(path);
	}
    }

    public void enemyCleanUp(Enemy enemy){
	enemies.remove(enemy);

    }

    public void tick(){
        activateTowers();
	enemyMove();
    }
    public void addEnemy(){
        enemies.add(new GenericEnemy(new Point(9,9)));
        towers.add(new CanonTower(new Point(4,3)));

        enemies.add(new GenericEnemy(new Point(1,4)));
	towers.add(new ArrowTower(new Point(2,3)));

	//enemies.add(new GenericEnemy(new Point(3,3)));
	towers.add(new ArrowTower(new Point(4,6)));


    }
    public List<AbstractTower> getTowers() {
	return towers;
    }

    public void addEntity(Entity entity, int x, int y){
        Tile currentTile = tiles[x][y];
        currentTile.addEntity(entity);
    }

    public List<Enemy> getEnemies() {
	return enemies;
    }

    public void hardCodedMap(){
	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {
		tiles[h][w] = new Tile(w,h,null, tileType.grass);
	    }
	}
    }


    public void hardRoad(){
        tiles[5][0] = new Tile(0,5,null, tileType.roado);
	tiles[5][1] = new Tile(1,5,null, tileType.roado);
	tiles[5][2] = new Tile(2,5,null, tileType.roado);
	tiles[5][3] = new Tile(3,5,null, tileType.roado);
	tiles[5][4] = new Tile(4,5,null, tileType.roado);
	tiles[5][5] = new Tile(5,5,null, tileType.roado);
	tiles[6][5] = new Tile(5,6,null, tileType.roado);
	tiles[7][5] = new Tile(5,7,null, tileType.roado);
	tiles[7][6] = new Tile(6,7,null, tileType.roado);
	tiles[7][7] = new Tile(7,7,null, tileType.roado);
	tiles[7][8] = new Tile(8,7,null, tileType.roado);
	tiles[7][9] = new Tile(9,7,null, tileType.roado);
	path.add(new Point(0,5));
	path.add(new Point(1,5));
	path.add(new Point(2,5));
	path.add(new Point(3,5));
	path.add(new Point(4,5));
	path.add(new Point(5,5));
	path.add(new Point(5,6));
	path.add(new Point(5,7));
	path.add(new Point(6,7));
	path.add(new Point(7,7));
	path.add(new Point(8,7));
	path.add(new Point(9,7));


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
