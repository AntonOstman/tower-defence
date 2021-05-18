package se.liu.antos931jakos322.towerdefence.gamelogic;

import se.liu.antos931jakos322.towerdefence.entities.Entity;
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
import java.util.Iterator;
import java.util.List;

/**
 *
 * Starts the game and controls what happens in the game
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


           projectile.move();
           Point2D projectilePosition = projectile.getPosition();
           //check on the tile the projectile is on if there are any enemies on it
           List<Enemy> potentialTargets = getEnemiesWithin(projectilePosition, projectile.getProjectileSize());
           if (!potentialTargets.isEmpty()) {
               // if there are enemies let the projectile attack them
               projectile.attack(potentialTargets);
           }
           // if the projectile cannot penetrate through any more enemies, remove it
           if (projectile.getPenetrationAmount() <= 0){
               projectilesToRemove.add(projectile);
           }
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
            Enemy closestEnemy = getClosestEnemy(tower.getPosition(), tower.getRange());
            if (closestEnemy == null){
                return;
            }

            if (tower.canAttack()) {
                Projectile projectile = tower.createProjectile(closestEnemy);
                projectiles.add(projectile);

            }
            // tower.attack(closestEnemy); towers only add projectiles

        }
    }

    public void takeDamage(int damage){
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public void moveEnemy(){

        Iterator<Enemy> i = enemies.iterator();
        while(i.hasNext()){
            Enemy enemy = i.next();
            int nextTile = enemy.getPathPosition();
            Point lastTile = gameMap.getLastTile();
            enemy.moveEnemy(gameMap.getPath(nextTile), lastTile);

            if(enemy.isFinished()){
                takeDamage(enemy.getDamage());
                i.remove();
            }
            if (enemy.getHealth() <= 0){
                i.remove();
                money += enemy.getRewardMoney();
            }

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
            listener.mapChanged();
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
            if (coord.equals(tower.getPosition())){
                tower.setSelected(true);
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
