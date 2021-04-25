package se.liu.antos931jakos322.towerdefence.entities;




import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * An abstract class with the core elements of an enemy
 * An enemy object wanders the map path with the intent of damaging the player
 *
 */

public abstract class Enemy implements Entity
{
    protected int level;
    protected Point position;
    protected int health;
    protected int pathPosition;
    protected int moveAmount = 0;
    protected final int maxHealth;
    protected final int rewardMoney;
    protected double drawX;
    protected double drawY;
    protected int speed;
    protected double enemyScale; // many fields, can any be removed/moved?

    protected Color color;

    protected Enemy(final int maxHealth, final int speed, final Color color, final double size) {

        this.position = new Point(-1, -1);
	this.health = maxHealth;
	this.maxHealth = maxHealth;
	this.color = color;
	this.enemyScale = size;
	this.speed = speed;

	this.pathPosition = 0;
    	this.rewardMoney = 5;
    }

    public void draw(final Graphics2D g2d, final int tileSize) {
	g2d.setColor(color);

	final int size = (int) (tileSize * enemyScale); // less size is bigger enemy
	final int enemyOffset = tileSize/2 - size/2; // should be changed to fit all sizes
	System.out.println(drawX);
	System.out.println(drawY);
	int drawPosX = (int) (drawX * tileSize) + enemyOffset;
	int drawPosY = (int) (drawY * tileSize) + enemyOffset;

	g2d.fillOval(drawPosX, drawPosY, size, size);

    }

    public int getHealth(){
	return health;
    }

    public int moveAndTakeDamage(Point nextTile, Point lastTile){

	if(nextTile.equals(lastTile)){ // since list size is 1 bigger than the list index
	    return 1;
	}

	// path index is used moves the actual point position while the rest is to keep the enemy walking "smooth"
	double difX = nextTile.x - position.x;
	double difY = nextTile.y - position.y;

	drawX =  (position.x + (difX / speed) * moveAmount);
	drawY =  (position.y + (difY / speed) * moveAmount);

        if(moveAmount < speed){ moveAmount++; }
        else{
	    moveAmount = 1;
	    pathPosition += 1;
	    position = nextTile;

	}
        return 0;
    }

    public Point getPosition() {
	return position;
    }

    public int getRewardMoney() {
	return rewardMoney;
    }

    public double getDrawX() {
	return drawX;
    }

    public double getDrawY() {
	return drawY;
    }

    public void takeDamage(int damage) throws IllegalArgumentException{
        if (health - damage < 0) {
	    health = 0;
	}
        else {
            health -= damage;
	}
	double procentageHP = (double) health / maxHealth;

	double inverceProcentageHP = 1 - procentageHP;

	try {

	    //color = new Color(255, (int) (inverceProcentageHP * 255), (int) (inverceProcentageHP * 255));
	    color = new Color( 	(int) (color.getRed()	+ (255- color.getRed())   *inverceProcentageHP),
		    		(int) (color.getGreen()	+ (255- color.getGreen()) *inverceProcentageHP),
	    			(int) (color.getBlue()	+ (255- color.getBlue())  *inverceProcentageHP));

	}
	catch (IllegalArgumentException e){
		e.printStackTrace();
	}

    }

    public int getPath() {
	return pathPosition;
    }
}
