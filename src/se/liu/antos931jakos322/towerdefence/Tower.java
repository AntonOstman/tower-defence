package se.liu.antos931jakos322.towerdefence;

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
    protected Tower(final Point position, Color color) {
        this.position = position;
        this.color = color;
        this.attackPower = 10;
    }

    public boolean isFatalAttack(Enemy enemy){
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


    @Override public void draw(final Graphics2D g2d, final int tileSize) {
        final double towerScale = 0.9;
        int towerSize = (int) (tileSize * towerScale);
        g2d.setColor(color);
        g2d.fillRect(position.x * tileSize, position.y * tileSize, towerSize, towerSize);
    }
}