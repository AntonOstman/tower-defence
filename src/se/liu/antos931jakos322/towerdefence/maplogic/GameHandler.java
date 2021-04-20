package se.liu.antos931jakos322.towerdefence.maplogic;

import se.liu.antos931jakos322.towerdefence.entities.BossEnemy;
import se.liu.antos931jakos322.towerdefence.entities.Enemy;
import se.liu.antos931jakos322.towerdefence.entities.SpeedEnemy;
import se.liu.antos931jakos322.towerdefence.entities.Tower;
import se.liu.antos931jakos322.towerdefence.other.HelperFunctions;
import se.liu.antos931jakos322.towerdefence.entities.GenericEnemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * Starts the game and controls what happens in the game
 *
 */

public class GameHandler
{
    private Map map;
    private java.util.List<Tower> towers;
    private List<Enemy> enemies;
    private int health;
    private int money;
    private final static int WAVE_TIMER = 50;
    private int tickCounter = 1;
    private int waveLevel = 0;
    private List<MapListener> gameListeners;


    public GameHandler(Map map) {
        this.map = map;
        this.enemies = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.health = 100;
        this.money = 10;
        this.gameListeners = new ArrayList<>();
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
            int nextTile = enemy.getPath();
            Point lastTile = map.getLastTile();
            int damage = enemy.moveAndTakeDamage(map.getPath(nextTile), lastTile);
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
        TileType desiredPlacementTile  = map.getTile(towerPos).getTileType();
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
    public int getMoney() {
        return money;
    }

    public void notifyListeners(){
        for(MapListener listener: gameListeners){
            listener.mapChanged();
        }
    }
    public void addListener(MapListener listener){
        gameListeners.add(listener);
    }

    public Map getMap() {
        return map;
    }
}
