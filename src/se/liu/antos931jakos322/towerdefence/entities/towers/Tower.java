package se.liu.antos931jakos322.towerdefence.entities.towers;

import se.liu.antos931jakos322.towerdefence.entities.Entity;
import se.liu.antos931jakos322.towerdefence.entities.enemies.Enemy;
import se.liu.antos931jakos322.towerdefence.entities.projectiles.Projectile;
import se.liu.antos931jakos322.towerdefence.entities.projectiles.ProjectileGetter;
import se.liu.antos931jakos322.towerdefence.entities.projectiles.ProjectileType;
import se.liu.antos931jakos322.towerdefence.other.HelperFunctions;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Tower is the Abstract class for defensive structures in the game which also extends Entity
 * Tower facilitates the use of projectiles and also has properties...
 * These properties are:
 * attackpower - represents how much damage the tower's projectiles do
 * cost - represents how much a tower costs to create
 * attackspeed - how often a tower can send a projectile
 * range - how close the tower needs to be a target to send a projectile
 * Level - represents how much Tower has upgraded where each level increases the strength of Tower
 *
 * a Tower can attack enemies by first checking if it canAttack() to make sure it is following the specific tower's attacking rules
 * then using the createProjectile(enemy) method which returns the projectile which will be shot towards the enemy.
 *
 */


public abstract class Tower extends Entity
{
    protected int range;
    protected int cost;
    protected int upgradeCost;
    protected int attackSpeed;
    protected int attackSpeedCharge;
    protected TowerType towerType;
    protected ProjectileType projectileType;
    protected Point2D startPosition;
    protected boolean selected;
    protected int level;
    protected Enemy targetEnemy;

    /**
     *  Constructs a Tower for towers with an inital moving speed
     *
     * @param speed the speed which the tower moves with
     * @param towerType the type of tower
     * @param color the color of the tower
     * @param cost the cost of the tower
     * @param attackPower the attackpower or "damage" of the tower
     * @param range the range in which the tower can attack
     * @param upgradeCost the cost to upgrade the tower
     * @param projectileType the type of projectile the tower uses
     * @param attackSpeed the attackspeed of the tower. Higher attackspeed means a longer time to attack
     * @param size the size of the tower
     */
    protected Tower(TowerType towerType, Color color, int cost, int attackPower, int range, int upgradeCost, ProjectileType projectileType,
                    int attackSpeed, double speed, double size) {
        super(color, size, speed, attackPower);
        this.cost = cost;
        this.range = range;
        this.towerType = towerType;
        this.upgradeCost = upgradeCost;
        this.projectileType = projectileType;
        this.startPosition = null;
        this.attackSpeed = attackSpeed;
        this.attackSpeedCharge = 0;
        this.selected = false;
        this.level = 1;
        this.targetEnemy = null;
    }

    /**
     *  Constructs a Tower for Towers without an inital moving speed
     *
     * @param towerType the type of tower
     * @param color the color of the tower
     * @param cost the cost of the tower
     * @param attackPower the attackpower or "damage" of the tower
     * @param range the range in which the tower can attack
     * @param upgradeCost the cost to upgrade the tower
     * @param projectileType the type of projectile the tower uses
     * @param attackSpeed the attackspeed of the tower. Higher attackspeed means a longer time to attack
     * @param size the size of the tower
     */
    protected Tower(TowerType towerType, Color color, int cost, int attackPower, int range, int upgradeCost, ProjectileType projectileType,
                    int attackSpeed, double size) {
        super(color, size, attackPower);
        this.cost = cost;
        this.range = range;
        this.startPosition = null;
        this.towerType = towerType;
        this.upgradeCost = upgradeCost;
        this.projectileType = projectileType;
        this.attackSpeed = attackSpeed;
        this.attackSpeedCharge = 0;
        this.selected = false;
        this.level = 1;
        this.targetEnemy = null;
    }

    /**
     * returns wheter a attack on the desired enemy is possible
     *
     * @param enemy the enemy to check wheter an attack is possible
     * @return true if attack is possible otherwise false
     */
    public boolean canAttack(){

        // if the tower needs to recharge before it can shoot....
        if (attackSpeedCharge != attackSpeed){
            // recharge and...
            attackSpeedCharge++;
            // return false.
            return false;
        }

        // if the enemy is inside range and the tower could shoot...
        else if (HelperFunctions.isNear(position, targetEnemy.getPosition() , range)){
            // set that the tower needs to recharge...
            attackSpeedCharge = 0;
            // and return that the tower can attack
            return true;
        }
        else{
            return false;
        }

    }


    /**
     * Used by towers to attack enemies.
     * Returns the projectile object the tower is attacking with.
     *
     * @param enemy the enemey to attack
     * @return the projectile to attack with
     */
    public Projectile createProjectileAttack() {

        Projectile projectile = ProjectileGetter.getProjectile(projectileType);
        projectile.setTarget(targetEnemy);
        projectile.setPosition(position);
        projectile.setAttackPower(attackPower);

        return projectile;
    }

    public void decideAttack(Enemy enemy){
        if (targetEnemy == null){
            targetEnemy = enemy;
        }
        Point2D enemyPos = enemy.getPosition();
        Point2D targetEnemyPos = targetEnemy.getPosition();

        Point2D targetRelativePosition = new Point2D.Double(position.getX() - targetEnemyPos.getX(), position.getY() - targetEnemyPos.getY());

        Point2D potentialTargetRelativeDistance = new Point2D.Double(position.getX() - enemyPos.getX(), position.getY() - enemyPos.getY());
        double targetDistance = Math.hypot(targetRelativePosition.getX(), targetRelativePosition.getY());
        double potentialTargetDistance = Math.hypot(potentialTargetRelativeDistance.getX(), potentialTargetRelativeDistance.getY());
        // get the "real" distance

        if(potentialTargetDistance < targetDistance){
            targetEnemy = enemy;

        }



    }

    public void setSelected(final boolean selected) {
        this.selected = selected;
    }

    public int getRange() {
        return range;
    }

    public int getCost() {
        return cost;
    }

    public abstract void activate();
        // can be used by towers to do something special example: airplanetower moves

    /**
     * draws a on the Tower position as a square with the designated Color.
     * Also draws the tower level on the tower.
     * When tower is selectd draws the range of the tower in a circle
     * @param g2d the grapichs object to draw with
     * @param gameScale the scale of the game in which the Tower needs to draw with
     */

    @Override public void draw(final Graphics2D g2d, final int gameScale) {

        int towerSize = (int) (gameScale * size);
        double towerPosX = position.getX();
        double towerPosY = position.getY();

        final int towerOffset = gameScale / 2 - towerSize / 2; // the ratio to keep a tower centered on a tile
        int drawX = (int) (towerPosX * gameScale) + towerOffset;
        int drawY = (int) (towerPosY * gameScale) + towerOffset;
        g2d.setColor(getColor());
        g2d.fillRect(drawX, drawY, towerSize, towerSize);

        if(selected){

            int pixelRange = range * gameScale;

            int circleX = (int) (towerPosX * gameScale) - pixelRange + gameScale / 2;
            int circleY = (int) (towerPosY * gameScale) - pixelRange + gameScale / 2;

            g2d.drawOval(circleX, circleY, range * gameScale * 2, range * gameScale * 2);
        }
        g2d.setColor(Color.white);

        g2d.setFont(new Font("TimesRoman", Font.PLAIN, gameScale / 3));
        int textPosX = (int) (towerPosX * gameScale) + gameScale / 3;
        int textPosY = (int) (towerPosY * gameScale) + gameScale / 2;
        g2d.drawString(String.valueOf(level), textPosX, textPosY);
    }

    public String getDescription() {
        String description = towerType +" TOWER\nattack power: " + attackPower + "\ncost: " + cost + "\nupgrade cost: " + upgradeCost +
                             "\nrange: " + range + "\nattack speed: " + attackSpeed ;

        return description;
    }
    public void upgrade(){
        attackPower += 1;
        upgradeCost += 1;
        level++;

    }


    @Override public void setPosition(final Point2D position) {
        super.setPosition(position);
        startPosition = position;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

}
