package se.liu.antos931jakos322.towerdefence.entities;

import javax.swing.*;
import java.awt.*;

/**
 *  A tower is an object with attackDamage range and cost
 *  A tower is used in the game to damage incoming enemy objects
 *
 *
 */


public abstract class Tower extends EntityAbstract implements Entity
{
    protected int attackPower;
    protected int range;
    protected int cost;
    protected int upgradeCost;
    protected double bulletDrawX;
    protected double bulletDrawY;
    protected TowerType towerType;
    protected TowerProjectile projectile;

    protected Tower(TowerType towerType, Color color, int cost, int attackPower, int range, int upgradeCost) {
        super(color, 0.7);
        this.cost = cost;
        this.attackPower = attackPower;
        this.range = range;
        this.towerType = towerType;
        this.upgradeCost = upgradeCost;
        this.projectile = null;
    }

    public boolean attackAndReturnIsFatal(Enemy enemy){

        if (enemy == null){
            bulletDrawX = -1;
            bulletDrawY = -1;
            return false;
        }
        projectile = new BulletProjectile(Color.black,0.1,20);
        projectile.attack(enemy, position);
        //changeBulletPlacement(enemy);
        //enemy.takeDamage(attackPower);
        int enemyHealth = enemy.getHealth();
        if (enemyHealth <= 0){
            return true;
        }
        return false;
    }

    public void changeBulletPlacement(Enemy enemy){
        int distanceToEnemyScale = 1;

        double difX = enemy.getDrawPosX() - getDrawPosX();
        double difY = enemy.getDrawPosY() - getDrawPosY();

        bulletDrawX =  (getDrawPosX() + ((difX ) * moveAmount));
        bulletDrawY =  (getDrawPosY() + ((difY ) * moveAmount));

        if(moveAmount < distanceToEnemyScale){
            final double bulletSpeed = 0.2;

            moveAmount += bulletSpeed;
        }
        else{
            moveAmount = 0;
        }
    }



    public int getRange() {
        return range;
    }

    public int getCost() {
        return cost;
    }

    @Override public void draw(final Graphics2D g2d, final int tileSize) {
        final double towerScale = 0.6;
        final double bulletScale = 0.02 * attackPower; // the bullet scales with tower power
        int towerSize = (int) (tileSize * towerScale);
        int bulletSize = (int) (tileSize * bulletScale);


        final int towerOffset = tileSize/2 - towerSize/2; // the ratio to keep a tower centered on a tile
        final int bulletOffset = tileSize/2 - bulletSize/2;

        int bulletPosX = (int) (bulletDrawX) + bulletOffset ;
        int bulletPosY = (int) (bulletDrawY) + bulletOffset ;

        drawPosX = getPosition().x * tileSize + towerOffset;
        drawPosY = getPosition().y * tileSize + towerOffset;
        g2d.setColor(getColor());
        g2d.fillRect(getDrawPosX(), getDrawPosY(), towerSize, towerSize);
        g2d.fillOval(bulletPosX, bulletPosY, bulletSize, bulletSize);
    }

    public String getDescription() {
        String description = towerType +" TOWER\nattack power: " + attackPower + "\ncost: " + cost + "\nupgrade cost: " + upgradeCost + "\nrange: " + range ;

        return description;
    }
    public void upgrade(){
        attackPower += 1;
        upgradeCost += 1;
        range += 1;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

}
