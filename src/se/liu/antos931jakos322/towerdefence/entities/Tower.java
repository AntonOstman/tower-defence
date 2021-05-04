package se.liu.antos931jakos322.towerdefence.entities;

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
    protected int attackSpeed;
    protected int attackSpeedCharge;
    protected TowerType towerType;
    protected ProjectileType projectileType;
    protected final static ProjectileMaker PROJECTILE_MAKER = new ProjectileMaker();


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

        Projectile projectile = PROJECTILE_MAKER.getProjectile(projectileType, position, attackPower);
        projectile.setTarget(enemy);

        return projectile;
    }

    public int getRange() {
        return range;
    }

    public int getCost() {
        return cost;
    }

    @Override public void draw(final Graphics2D g2d, final int tileSize) {

        int towerSize = (int) (tileSize * drawScale);

        final int towerOffset = tileSize/2 - towerSize/2; // the ratio to keep a tower centered on a tile
        drawPosX = getPosition().x * tileSize + towerOffset;
        drawPosY = getPosition().y * tileSize + towerOffset;
        g2d.setColor(getColor());
        g2d.fillRect(drawPosX, drawPosY, towerSize, towerSize);
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
    }



    public int getUpgradeCost() {
        return upgradeCost;
    }

}
