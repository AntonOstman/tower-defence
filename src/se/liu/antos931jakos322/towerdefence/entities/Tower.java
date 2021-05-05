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

        Projectile projectile = PROJECTILE_MAKER.getProjectile(projectileType, position, attackPower);
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

    @Override public void draw(final Graphics2D g2d, final int tileSize) {

        int towerSize = (int) (tileSize * drawScale);

        final int towerOffset = tileSize/2 - towerSize/2; // the ratio to keep a tower centered on a tile
        drawPosX = getPosition().x * tileSize + towerOffset;
        drawPosY = getPosition().y * tileSize + towerOffset;
        g2d.setColor(getColor());
        g2d.fillRect(drawPosX, drawPosY, towerSize, towerSize);

        if(selected){
            // Shows the range for a set number of ticks
            if(rangeTickCounter>30){
                selected = false;
                rangeTickCounter = 0;
            }

            int pixelRange = range*tileSize;
            g2d.drawOval(getPosition().x * tileSize -pixelRange/2 + tileSize/2,
                         getPosition().y * tileSize -pixelRange/2 + tileSize/2,
                         range*tileSize, range*tileSize);
            rangeTickCounter++;
        }
        g2d.setColor(Color.white);
        //g2d.drawString("5",drawPosX+tileSize/2, drawPosY+tileSize/2);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, tileSize/3));
        g2d.drawString(String.valueOf(level), getPosition().x * tileSize + tileSize / 3,
                       getPosition().y* tileSize + 2*tileSize/3);
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
