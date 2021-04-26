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
    protected double moveAmount;
    protected double bulletDrawX;
    protected double bulletDrawY;
    protected int drawPosX;
    protected int drawPosY;
    protected TowerType towerType;

    protected Tower(TowerType towerType, Color color, int cost, int attackPower, int range) {
        this.cost = cost;
        this.position = null;
        this.color = color;
        this.attackPower = attackPower;
        this.range = range;
        this.towerType = towerType;
    }

    public boolean attackAndReturnIsFatal(Enemy enemy){
        if (enemy == null){
            bulletDrawX = -1;
            bulletDrawY = -1;
            return false;
        }
        changeBulletPlacement(enemy);
        enemy.takeDamage(attackPower);
        int enemyHealth = enemy.getHealth();
        if (enemyHealth <= 0){
            return true;
        }
        return false;
    }

    public void changeBulletPlacement(Enemy enemy){
        int distanceToEnemyScale = 1;
        double difX = enemy.getDrawPosX() - drawPosX;
        double difY = enemy.getDrawPosY() - drawPosY;
        bulletDrawX =  (drawPosX + ((difX ) * moveAmount));
        bulletDrawY =  (drawPosY + ((difY ) * moveAmount));

        if(moveAmount < distanceToEnemyScale){
            final double bulletSpeed = 0.2;

            moveAmount += bulletSpeed;
        }
        else{
            moveAmount = 0;
        }
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
        final double bulletScale = 0.02 * attackPower; // the bullet scales with tower power
        int towerSize = (int) (tileSize * towerScale);
        int bulletSize = (int) (tileSize * bulletScale);


        final int towerOffset = tileSize/2 - towerSize/2; // the ratio to keep a tower centered on a tile
        final int bulletOffset = tileSize/2 - bulletSize/2;
        int bulletPosX = (int) (bulletDrawX) + bulletOffset ;
        int bulletPosY = (int) (bulletDrawY) + bulletOffset ;
        drawPosX = position.x * tileSize + towerOffset;
        drawPosY = position.y * tileSize + towerOffset;
        g2d.setColor(color);
        g2d.fillRect(drawPosX, drawPosY, towerSize, towerSize);
        g2d.fillOval(bulletPosX, bulletPosY, bulletSize, bulletSize);
    }

    public String getDescription() {
        String description = towerType +" TOWER\nattack power: " + attackPower + "\ncost: " + cost + "\nrange: " + range;

        return description;
    }
    public void upgrade(int attackPower, int range, int cost){
        this.attackPower += attackPower;
        this.range += range;
        this.cost += cost;

    }

    public Color getColor() {
        return color;
    }
}
