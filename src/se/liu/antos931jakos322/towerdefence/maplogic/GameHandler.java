package se.liu.antos931jakos322.towerdefence.maplogic;

import se.liu.antos931jakos322.towerdefence.entities.Enemy;
import se.liu.antos931jakos322.towerdefence.entities.Tower;
import se.liu.antos931jakos322.towerdefence.entities.Projectile;
import se.liu.antos931jakos322.towerdefence.entities.WaveMaker;
import se.liu.antos931jakos322.towerdefence.other.HelperFunctions;
import se.liu.antos931jakos322.towerdefence.userinterface.GameListener;

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
    private List<Tower> towers;
    private List<Enemy> enemies;
    private List<Projectile> projectiles;
    private int health;
    private int money;
    private final static int WAVE_TIMER = 50;

    private int waveLevel = 0;
    private List<GameListener> gameListeners;
    private WaveMaker waveMaker;


    public GameHandler(Map map) {
        this.map = map;
        this.enemies = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.health = 100;
        this.money = 10;
        this.gameListeners = new ArrayList<>();
        this.waveMaker = new WaveMaker();
    }
    public void tick(){
        activateTowers();
        moveProjectiles();
        enemies.addAll(waveMaker.update());
        enemyMove();

        notifyListeners();

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

    public void moveProjectiles(){
        for (Projectile projectile : projectiles){
            projectile.attack();
        }
    }


    public void activateTowers(){
        for (Tower tower: towers){
            Enemy closestEnemy = getClosestEnemy(tower.getPosition(), tower.getRange());
            if (closestEnemy == null){
                return;
            }
            Projectile projectile =  tower.getProjectile(closestEnemy);

            // tower.attack(closestEnemy); towers only add projectiles
            projectiles.add(projectile);

            if (closestEnemy.getHealth() <= 0){
                enemies.remove(closestEnemy);
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
            int nextTile = enemy.getPathPosition();
            Point lastTile = map.getLastTile();
            if( enemy.isPathMovementDone(map.getPath(nextTile), lastTile) ){
                takeDamage(enemy.getDamage());
                i.remove();
            }
        }
    }

    public void removeEnemy(Enemy enemy){
        money += enemy.getRewardMoney();
        enemies.remove(enemy);
    }

    public void addTower(Tower tower){
        money -= tower.getCost();
        towers.add(tower);
    }

    public void addProjectile(Projectile projectile){
        projectiles.add(projectile);
    }

    public boolean canAffordAndPlaceTower(Tower tower){
        Point towerPos = tower.getPosition();
        TileType desiredPlacementTile  = map.getTile(towerPos).getTileType();

        // if the player attempts to place the tower on something thats not grass return false
        if (desiredPlacementTile != TileType.GRASS) {
            return false;
        }
        // if there is another tower on the tile return fakse
        for (Tower otherTowers: towers){
            if(otherTowers.getPosition().equals(towerPos)){
                return false;
            }
        }
        // if the player cannot afford the tower return false
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
        for(GameListener listener: gameListeners){
            listener.mapChanged();
        }
    }

    public void addListener(GameListener listener){
        gameListeners.add(listener);
    }

    public List<Enemy> getEnemiesOnPoint(Point coord){
        List<Enemy> enemyList = new ArrayList<>();
        for(Enemy enemy : enemies){
            if (coord.equals(enemy.getPosition())){
                enemyList.add(enemy);
            }
        }
        return enemyList;
    }


    public Tower getTowerOnPoint(Point coord){
        for(Tower tower : towers){
            if (coord.equals(tower.getPosition())){
                return tower;
            }
        }
        return null;
    }

    public boolean isTowerUpgradable(Tower tower){
        if (money - tower.getUpgradeCost() < 0){
            return false;
        }
        return true;
    }

    public void upgradeTower(Tower tower){
        money -= tower.getUpgradeCost();
        tower.upgrade();

    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public Map getMap() { // this should probably be changed so map cannot be directly accessed. Game handler controls map not others
        return map;
    }
}
