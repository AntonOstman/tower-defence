package se.liu.antos931jakos322.towerdefence.gamelogic;

import se.liu.antos931jakos322.towerdefence.entities.enemies.Enemy;
import se.liu.antos931jakos322.towerdefence.entities.towers.Tower;
import se.liu.antos931jakos322.towerdefence.entities.towers.Projectile;
import se.liu.antos931jakos322.towerdefence.entities.enemies.WaveMaker;
import se.liu.antos931jakos322.towerdefence.other.HelperFunctions;
import se.liu.antos931jakos322.towerdefence.userinterface.GameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Gamehandler is the core class which contains the logic for starting and running the game.
 * Gamehandlers main way of running the game is by using the tick() method which is activated by a timer.
 * when the tick() method is called the game progresses one step by calling the logic entities.
 * Since GameHandler controls the game that also means it handles the interactions between the game map and entities.
 * Which means for example GameHandler means GameHandler knows when
 * enemies have come to the end of the game map and the player should take damage.
 *
 * GameHandler also has information for player health, money and wheter the game is over or not and handles that information.
 *
 */

public class GameHandler
{
    private GameMap gameMap;
    private List<Tower> towers;
    private List<Enemy> enemies;
    private List<Projectile> projectiles;
    private int health;
    private int money;
    private List<GameListener> gameListeners;
    private WaveMaker waveMaker;
    private Timer tickTimer;
    private int tickDelay;
    private boolean gamePaused;
    private boolean gameOver;

    public GameHandler(GameMap gameMap) {
        this.gameMap = gameMap;
        this.enemies = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.health = 100;
        this.money = 10;
        this.gameListeners = new ArrayList<>();
        this.waveMaker = new WaveMaker();
        this.tickDelay = 30;
        this.tickTimer = new Timer(tickDelay, new DoOneStep()); // timer is set when the game starts in method startgame
        this.gamePaused = true;
        this.gameOver = false;
    }
    public void tick(){

        setGameOver();
        activateTowers();
        moveProjectiles();
        createEnemies();
        moveEnemy();
        notifyListeners();

    }

    public void createEnemies(){
        enemies.addAll(waveMaker.update());
    }

    public void setGameOver(){

        if (health <= 0) {
            pauseGame();
            gameOver = true;
        }
    }

    public Enemy getClosestEnemy(Point2D towerPos, int range){
        Enemy closestEnemy = null;

        if (!enemies.isEmpty()){
            double closestDistance = Double.POSITIVE_INFINITY;
            // for every enemy check if another is closer and is in range.
            // If one closer is found save that enemy, do that for all enemies
            // if no enemies in range are found return null
            for(Enemy enemy: enemies){
                Point2D enemyPos = enemy.getPosition();
                Point2D relativePoint = new Point2D.Double(towerPos.getX() - enemyPos.getX(),towerPos.getY() - enemyPos.getY());
                double currentDistance = Math.hypot(relativePoint.getX(), relativePoint.getY());
                // get the "real" distance

                if(currentDistance < closestDistance && currentDistance <= range){
                    closestEnemy = enemy;
                    closestDistance = currentDistance;

                }
            }
        }
        return closestEnemy;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void startGame() {

    tickTimer.start();
    gamePaused = false;
    }

    public void pauseGame(){
        tickTimer.stop();
        gamePaused = true;
    }

    public void moveProjectiles(){
        List<Projectile> projectilesToRemove = new ArrayList<>();

        for (Projectile projectile : projectiles){
            // start by moving all projectiles
           projectile.move();
            // check if a projectile can attack an enemy and in that case attack the enemy
            for (Enemy enemy : enemies){
                if (projectile.canAttack(enemy)) {
                    projectile.attack(enemy);
                }
            }
            // if the projectile cannot penetrate through any more enemies, remove it
           if (projectile.getPenetrationAmount() <= 0){
               projectilesToRemove.add(projectile);
           }
            Point2D projectilePosition = projectile.getPosition();
            // if there are any projectiles outside the game add them to the remove list
            boolean lessThanBounds = projectilePosition.getY() < 0 || projectilePosition.getX() < 0;
            boolean greaterThanBounds = projectilePosition.getX() > gameMap.getWidth() || projectilePosition.getY() > gameMap.getHeight();
            if(lessThanBounds || greaterThanBounds){
                projectilesToRemove.add(projectile);
            }
        }
        // remove the projectiles designated to be removed
        for (Projectile projectile : projectilesToRemove){
            projectiles.remove(projectile);
        }
    }


    public void activateTowers(){
        for (Tower tower: towers){
            tower.activate();
            // get the enemy closest to the tower...
            Enemy closestEnemy = getClosestEnemy(tower.getPosition(), tower.getRange());
            if (closestEnemy == null){
                continue;
            }
            // if the enemy can be attacked
            if (tower.canAttack(closestEnemy)) {
                // then send a projectile to that enemy
                Projectile projectile = tower.createProjectile(closestEnemy);
                projectiles.add(projectile);

            }

        }
    }

    public void takeDamage(int damage){
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public int getLevel(){
        return waveMaker.getWaveLevel();
    }


    public void moveEnemy(){

        List<Enemy> removeEnemies = new ArrayList<>();

        for (Enemy enemy : enemies){
            int nextTile = enemy.getPathPosition();
            enemy.setLastPosition(gameMap.getLastTile());
            enemy.setMovePosition(gameMap.getPath(nextTile));
            enemy.move();
            // if the enemy has come to the end of the map damage the player
            if(enemy.isFinished()){
                takeDamage(enemy.getDamage());
                removeEnemies.add(enemy);
            }
            // if an enemy is defeated remove it and give the player money
            else if (enemy.getHealth() <= 0){
                removeEnemies.add(enemy);
                money += enemy.getRewardMoney();
            }

        }

        for(Enemy enemy : removeEnemies){
            enemies.remove(enemy);
        }


    }



    public void addTower(Tower tower){
        money -= tower.getCost();
        towers.add(tower);
    }

    public void addProjectile(Projectile projectile){
        projectiles.add(projectile);
    }

    public boolean canAffordAndPlaceTower(Tower tower){
        Point2D towerPos = tower.getPosition();
        Point towerPosPoint = new Point((int)towerPos.getX(),(int)towerPos.getY());

        TileType desiredPlacementTile  = gameMap.getTile(towerPosPoint).getTileType();

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


    public int getMoney() {
        return money;
    }

    public void notifyListeners(){
        for(GameListener listener: gameListeners){
            listener.gameChanged();
        }
    }

    public void addListener(GameListener listener){
        gameListeners.add(listener);
    }


    // returnes the enemies within a ceratin range.
    // example: if range = 1 all enemies whithin 1 tiles range will be returned

    public List<Enemy> getEnemiesWithin(Point2D coord, double range) {
        List<Enemy> enemiesInRange = new ArrayList<>();
        for (Enemy enemy : enemies) {
            double difX = enemy.getX() - coord.getX();
            double difY = enemy.getY() - coord.getY();

           double distance = Math.hypot(difX,difY);
           if (distance <= range){
               enemiesInRange.add(enemy);

            }
        }
        return enemiesInRange;
    }


    public Tower getTowerOnPoint(Point2D coord){
        for(Tower tower : towers){
            if(HelperFunctions.isNear(tower.getPosition(), coord,0.5)){
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

    public GameMap getMap() { // this should probably be changed so map cannot be directly accessed. Game handler controls map not others
        return gameMap;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }


    public int getEnemyAmount(){
        return enemies.size();
    }

    public int getProjectileAmount(){
        return projectiles.size();
    }

    public int getTowerAmount(){

        return towers.size();
    }

    public Projectile getProjectile(int number){

        return projectiles.get(number);
    }
    public Tower getTower(int number){
        return towers.get(number);
    }

    public Enemy getEnemy(int number){

        return enemies.get(number);
    }

    public class DoOneStep extends AbstractAction{

        @Override public void actionPerformed(final ActionEvent e){
            tick();

        }
    }



}
