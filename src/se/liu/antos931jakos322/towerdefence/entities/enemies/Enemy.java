package se.liu.antos931jakos322.towerdefence.entities.enemies;




import se.liu.antos931jakos322.towerdefence.entities.Entity;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An abstract class with the core elements of an enemy
 * An enemy object wanders the map path with the intent of damaging the player
 *
 */

public abstract class Enemy extends Entity
{
    protected int health;
    protected int pathPosition;
    protected final int maxHealth;
    protected final int rewardMoney;
    protected int damage;

    protected Enemy(final int maxHealth, final int speed, final Color color, final double size, int damage) {
	super(color, size, speed);
	this.health = maxHealth;
	this.maxHealth = maxHealth;
	this.pathPosition = 0;
    	this.rewardMoney = 5;
    	this.damage = damage;
    }

    public void takeDamage(int damage){
	if (health - damage < 0) {
	    health = 0;
	}
	else {
	    health -= damage;
	}

	try {
	    double procentageHP = (double) health / maxHealth;
	    double inverceProcentageHP = 1 - procentageHP;

	    //color = new Color(255, (int) (inverceProcentageHP * 255), (int) (inverceProcentageHP * 255));
	    color = new Color( 	(int) (color.getRed()	+ (255- color.getRed())   * inverceProcentageHP),
				      (int) (color.getGreen()	+ (255- color.getGreen()) * inverceProcentageHP),
				      (int) (color.getBlue()	+ (255- color.getBlue())  * inverceProcentageHP));
	    // the reason that a red enemy becomes fully white on half hp
	    // is because if the colors value start on 128 128 128
	    // and when precentageHP is 0.5 it will become 128 + 128 ( i think)
	}
	catch (IllegalArgumentException e){
	    e.printStackTrace();
	}

    }

    @Override public void draw(final Graphics2D g2d, final int tileSize) {
	super.draw(g2d, tileSize);

	g2d.setColor(color);
	int drawPositionX = (int) (drawPosX * tileSize);
	int drawPositionY = (int) (drawPosY * tileSize);

	// less size is bigger
	final int size = (int) (tileSize * drawScale);
	final int offset = tileSize / 2 - size / 2;
	double procentageHP = (double) health / maxHealth;
	g2d.fillOval(drawPositionX + offset, drawPositionY + offset, size, size);

	// below is for the healthbar
	// first we add a red bar to background...
	int healthBarHeight = 7;
	g2d.setColor(Color.red);
	g2d.fillRect(drawPositionX + offset,drawPositionY, size , healthBarHeight);

	// then on top of the red bar we add the green representing the current health
	// which gets lower with the remaning procentageHP
	g2d.setColor(Color.green);
	g2d.fillRect(drawPositionX + offset,drawPositionY, (int) (procentageHP * size), healthBarHeight);

    }

    public boolean isPathMovementDone(Point2D nextTile, Point2D lastTile){

	// Moves the enemy to "nextTile" over multiple ticks
	if (isMovementDone(nextTile)){
	    // When done with the movement -> change the next tile
	    pathPosition += 1;
	}
	if( position.equals(lastTile)){
	    return true;
	}
        return false;
    }

    public int getHealth(){
	return health;
    }

    public int getRewardMoney() { return rewardMoney; }


    public int getDamage(){ return damage; }

    public int getPathPosition() { return pathPosition; }

}
