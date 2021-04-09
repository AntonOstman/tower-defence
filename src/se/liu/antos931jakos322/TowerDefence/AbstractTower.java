package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;

public abstract class AbstractTower implements Entity
{
    protected Point pos;
    protected Color color;
    protected int attackPower;
    public AbstractTower(final Point pos, Color color) {
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


    @Override public void draw(final Graphics2D g2d, final int TILE_SIZE) {
        g2d.setColor(color);
        g2d.fillRect(pos.x*TILE_SIZE, pos.y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
}
