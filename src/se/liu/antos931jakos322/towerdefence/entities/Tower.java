package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

/**
 *  A tower is an object with attackDamage range and cost
 *  A tower is used in the game to damage incoming enemy objects
 *
 *
 */


public abstract class Tower implements Entity
{
    protected Point position;
    protected Color color;
    protected int attackPower;
    protected int range;
    protected int cost;


    protected Tower(Color color) {
        this.cost = 10;
        this.position = null;
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

    public void setPosition(final Point position) {
        this.position = position;
    }

    @Override public void draw(final Graphics2D g2d, final int tileSize) {
        final double towerScale = 0.6;
        int towerSize = (int) (tileSize * towerScale);

        final int towerOffset = tileSize/2 - towerSize/2; // see explanation in notes

        g2d.setColor(color);
        g2d.fillRect(position.x * tileSize + towerOffset, position.y * tileSize + towerOffset, towerSize, towerSize);
    }
}
