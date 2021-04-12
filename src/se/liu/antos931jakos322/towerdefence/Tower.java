package se.liu.antos931jakos322.towerdefence;

import java.awt.*;

/**
 *  An abstract class with the core elements of a tower
 *  A tower is used to attack the enemy wandering the map path
 *
 */


public abstract class Tower implements Entity
{
    protected Point pos;
    protected Color color;
    protected int attackPower;
    public Tower(final Point pos, Color color) {
        this.pos = pos;
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

    public Point getPosistion(){
        return pos;
    }


    @Override public void draw(final Graphics2D g2d, final int tileSize) {
        g2d.setColor(color);
        g2d.fillRect(pos.x * tileSize, pos.y * tileSize, tileSize, tileSize);
    }
}
