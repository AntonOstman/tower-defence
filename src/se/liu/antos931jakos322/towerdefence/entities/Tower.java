package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

/**
 *  An abstract class with the core elements of a tower
 *  A tower is used to attack the enemy wandering the map path
 *
 */


public abstract class Tower implements Entity
{
    protected Point position;
    protected Color color;
    protected int attackPower;
    protected int range;
    protected int cost;
    protected final static TowerType TOWER_TYPE = TowerType.ARROW;


    protected Tower(final Point position, Color color) {
        this.cost = 10;
        this.position = position;
        this.color = color;
        this.attackPower = 10;
        this.range = 5;
    }

    public boolean attackAndReturnIsFatal(Enemy enemy){
        if (enemy == null){
            return false;
        }
        enemy.takeDamage(attackPower);
        int enemyHealth = enemy.getHealth();
        if (enemyHealth <= 0){
            return true;
        }
        return false;
    }

    public Point getPosition(){
        return position;
    }

    public int getRange() {
        return range;
    }

    public int getCost() {
        return cost;
    }

    @Override public void draw(final Graphics2D g2d, final int tileSize) {
        final double towerScale = 0.6;
        int towerSize = (int) (tileSize * towerScale);

        final int towerOffset = tileSize/2 - towerSize/2; // see explanation in notes

        g2d.setColor(color);
        g2d.fillRect(position.x * tileSize + towerOffset, position.y * tileSize + towerOffset, towerSize, towerSize);
    }
}
