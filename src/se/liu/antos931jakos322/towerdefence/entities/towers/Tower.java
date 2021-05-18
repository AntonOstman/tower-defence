package se.liu.antos931jakos322.towerdefence.entities.towers;

import se.liu.antos931jakos322.towerdefence.entities.Entity;
import se.liu.antos931jakos322.towerdefence.entities.enemies.Enemy;

import java.awt.*;

/**
 * Tower is the Abstract class for defensive structures in the game
 * Tower facilitates the use of projectiles and has properties
 * which can be increased to improve the effectiveness
 * These properties are:
 * attackpower - represents how much damage the tower's projectiles do
 * cost - represents how much a tower costs to create
 * attackspeed - how often at tower can send a projectile
 * range - how close the tower needs to be a target to send a projectile
 * Level - represents how much Tower has upgraded where each level increases
 * the strength of Tower
 *
 */


public abstract class Tower extends Entity
{
    protected int attackPower;
    protected int range;
    protected int cost;
    protected int upgradeCost;
    protected int attackSpeed;
    protected int attackSpeedCharge;
    protected TowerType towerType;
    protected ProjectileType projectileType;
    protected boolean selected;
    protected int rangeTickCounter;
    protected int level;


    protected Tower(TowerType towerType, Color color, int cost, int attackPower, int range, int upgradeCost, ProjectileType projectileType, int attackSpeed) {
        super(color, 0.6);
        this.cost = cost;
        this.attackPower = attackPower;
        this.range = range;
        this.towerType = towerType;
        this.upgradeCost = upgradeCost;
        this.projectileType = projectileType;
        this.attackSpeed = attackSpeed;
        this.attackSpeedCharge = 0;
        this.selected = false;
        this.rangeTickCounter = 0;
        this.level = 1;
    }



    public boolean canAttack(){
        if (attackSpeedCharge == attackSpeed){
            attackSpeedCharge = 0;
            return true;
        }
        else{
         attackSpeedCharge++;
         return false;
        }
    }

    public Projectile createProjectile(Enemy enemy) {

        Projectile projectile = ProjectileMaker.getProjectile(projectileType, position, attackPower);
        projectile.setTarget(enemy);

        return projectile;
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

    public void draw(final Graphics2D g2d, final int tileSize) {

        int towerSize = (int) (tileSize * drawScale);
        double towerPosX = position.getX();
        double towerPosY = position.getY();

        final int towerOffset = tileSize/2 - towerSize/2; // the ratio to keep a tower centered on a tile
        int drawX = (int) towerPosX * tileSize + towerOffset;
        int drawY = (int) towerPosY * tileSize + towerOffset;
        g2d.setColor(getColor());
        g2d.fillRect(drawX, drawY, towerSize, towerSize);

        if(selected){
            // Shows the range for a set number of ticks
            if(rangeTickCounter>30){
                selected = false;
                rangeTickCounter = 0;
            }
            int pixelRange = range*tileSize;

            int circleX = (int)towerPosX * tileSize - pixelRange + tileSize/2;
            int circleY = (int)towerPosY * tileSize - pixelRange + tileSize/2;

            g2d.drawOval(circleX, circleY, range*tileSize*2, range*tileSize*2);
            rangeTickCounter++;
        }
        g2d.setColor(Color.white);
        //g2d.drawString("5",drawPosX+tileSize/2, drawPosY+tileSize/2);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, tileSize/3));
        int textPosX = (int) towerPosX * tileSize + tileSize / 3;
        int textPosY = (int) towerPosY * tileSize + tileSize /2;
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
        range += 1;
        level++;
    }



    public int getUpgradeCost() {
        return upgradeCost;
    }

}