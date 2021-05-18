package se.liu.antos931jakos322.towerdefence.entities.enemies;




import se.liu.antos931jakos322.towerdefence.entities.Entity;
import se.liu.antos931jakos322.towerdefence.other.HelperFunctions;

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
    private boolean finished;

    protected Enemy(final int maxHealth, final double speed, final Color color, final double size, int damage) {
	super(color, size, speed);
	this.health = maxHealth;
	this.maxHealth = maxHealth;
	this.pathPosition = 0;
    	this.rewardMoney = 5;
    	this.damage = damage;
    	this.finished = false;
    }

    public void takeDamage(int damage){
	if (health - damage < 0) {
	    health = 0;
	}
	else {
	    health -= damage;
	}
	    double percentageHealth = (double) health / maxHealth;
	    double inverceProcentageHP = 1 - percentageHealth;

	    //color = new Color(255, (int) (inverceProcentageHP * 255), (int) (inverceProcentageHP * 255));
	    color = new Color( 	(int) (color.getRed()	+ (255- color.getRed())   * inverceProcentageHP),
				      (int) (color.getGreen()	+ (255- color.getGreen()) * inverceProcentageHP),
				      (int) (color.getBlue()	+ (255- color.getBlue())  * inverceProcentageHP));
	    // the reason that a red enemy becomes fully white on half hp
	    // is because if the colors value start on 128 128 128
	    // and when precentageHP is 0.5 it will become 128 + 128 ( i think)


    }

    public void draw(final Graphics2D g2d, final int tileSize) {
	//super.draw(g2d, tileSize);

	g2d.setColor(color);
	int drawPositionX = (int) (position.getX() * tileSize);
	int drawPositionY = (int) (position.getY() * tileSize);

	final int size = (int) (tileSize * drawScale);
	final int offset = tileSize / 2 - size / 2;
	double percentageHealth = (double) health / maxHealth;
	g2d.fillOval(drawPositionX + offset, drawPositionY + offset, size, size);

	// below is for the healthbar
	// first we add a red bar to background...
	final int healthBarHeight = tileSize/5;
	g2d.setColor(Color.red);
	g2d.fillRect(drawPositionX + offset,drawPositionY, size , healthBarHeight);

	// then on top of the red bar we add the green representing the current health
	// which gets lower with the remaning percentageHealth
	g2d.setColor(Color.green);
	g2d.fillRect(drawPositionX + offset, drawPositionY, (int) (percentageHealth * size), healthBarHeight);

    }

    public boolean isNear(Point2D position, double distance){

        double deltaPositionX = this.position.getX() - position.getX();
        double deltaPositionY = this.position.getY() - position.getY();
	double distanceFrom = Math.sqrt(HelperFunctions.pythagoras(deltaPositionX, deltaPositionY));
	if (distanceFrom < distance){
	    return true;
	}
	return false;
    }


     public void moveEnemy(Point2D nextTile, Point2D lastTile){
	// Gives Enemy a starting position
	if(position == null){
	    position = nextTile;
	    pathPosition += 1;
	}
//	Point2D delta = new Point2D.Double();
//	delta.setLocation( nextTile.getX()-position.getX(),
//			   nextTile.getY()-position.getY());
	move(nextTile);

	if(isNear(nextTile,0.5)){
	    // If this is the last block --> Enemy is done with the path
	    if (nextTile.equals(lastTile)){
	        finished = true;
	        return;
	    }
	    pathPosition += 1;

	}
	// If all movements have been done for a block

    }

    public boolean isFinished(){
	return finished;
    }

    public int getHealth(){
	return health;
    }

    public int getRewardMoney() { return rewardMoney; }


    public int getDamage(){ return damage; }

    public int getPathPosition() { return pathPosition; }

}
