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
    protected int pathProgress;
    protected final int maxHealth;
    protected final int rewardMoney;
    protected boolean finished;
    private int lastPosition;

    protected Enemy(final int maxHealth, final double speed, final Color color, final double size, int attackPower) {
	super(color, size, speed, attackPower);
	this.health = maxHealth;
	this.maxHealth = maxHealth;
	this.pathProgress = 0;
    	this.rewardMoney = 5;
	this.finished = false;
    	this.lastPosition = -1;

    }

    public void takeDamage(int damage){
	if (health - damage < 0) { health = 0; }
	else { health -= damage; }

    }

    @Override public void draw(final Graphics2D g2d, final int gameScale) {


	g2d.setColor(color);
	int drawPositionX = (int) (position.getX() * gameScale);
	int drawPositionY = (int) (position.getY() * gameScale);

	final int size = (int) (gameScale * this.size);
	final int offset = gameScale / 2 - size / 2;
	double percentageHealth = (double) health / maxHealth;
	g2d.fillOval(drawPositionX + offset, drawPositionY + offset, size, size);

	// below is for the healthbar
	// first we add a red bar to background...
	final int healthBarHeight = gameScale/5;
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
	    pathProgress += 1;
	}
	super.move();

	// now the question is why does gamehandler know distances below for isNear?
	 // is that not gameMaps job to know its own distances? i dont know what is best
	final double distance = 0.2;
	if(HelperFunctions.isNear(position, movePosition, distance)){
	    // If this is the last block --> Enemy is done with the path
	    if (pathProgress == lastPosition){
	        finished = true;
	        return;
	    }
	    pathProgress += 1;

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

    public void setLastPosition(int position){
	lastPosition = position;
    }

    public int getPathProgress() { return pathProgress; }

    public List<Enemy> split(){
        return new ArrayList<>();
    }

    public void setPathProgress(final int pathProgress) {
	this.pathProgress = pathProgress;
    }
}
