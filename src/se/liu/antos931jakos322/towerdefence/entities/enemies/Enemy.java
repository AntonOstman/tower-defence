package se.liu.antos931jakos322.towerdefence.entities.enemies;




import se.liu.antos931jakos322.towerdefence.entities.Entity;
import se.liu.antos931jakos322.towerdefence.other.HelperFunctions;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

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
    protected boolean finished;
    private Point2D lastPosition;

    protected Enemy(final int maxHealth, final double speed, final Color color, final double size, int damage) {
	super(color, size, speed);
	this.health = maxHealth;
	this.maxHealth = maxHealth;
	this.pathPosition = 0;
    	this.rewardMoney = 5;
    	this.damage = damage;
    	this.finished = false;
    	this.lastPosition = null;

    }

    public void takeDamage(int damage){
	if (health - damage < 0) { health = 0; }
	else { health -= damage; }

    }

    public void draw(final Graphics2D g2d, final int tileSize) {
	//super.draw(g2d, tileSize);

	g2d.setColor(color);
	int drawPositionX = (int) (position.getX() * tileSize);
	int drawPositionY = (int) (position.getY() * tileSize);

	final int size = (int) (tileSize * this.size);
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


     @Override public void move(){
	// Gives Enemy a starting position
	if(position == null){
	    position = movePosition;
	    pathPosition += 1;
	}
	super.move();

	// now the question is why does gamehandler know distances below for isNear?
	 // is that not gameMaps job to know its own distances? i dont know what is best
	final double distance = 0.2;
	if(HelperFunctions.isNear(position, movePosition, distance)){
	    // If this is the last block --> Enemy is done with the path
	    if (movePosition.equals(lastPosition)){
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

    public void setLastPosition(Point2D position){
	lastPosition = position;
    }
    public int getDamage(){ return damage; }

    public int getPathPosition() { return pathPosition; }

    public void setMovePosition(final Point2D movePosition) {
	this.movePosition = movePosition;
    }
    public List<Enemy> split(){
        return new ArrayList<>();
    }

    public void setPathPosition(final int pathPosition) {
	this.pathPosition = pathPosition;
    }
}
