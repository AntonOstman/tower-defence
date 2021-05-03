package se.liu.antos931jakos322.towerdefence.entities;




import java.awt.*;

/**
 * An abstract class with the core elements of an enemy
 * An enemy object wanders the map path with the intent of damaging the player
 *
 */

public abstract class Enemy extends EntityAbstract implements Entity
{
    protected int level;
    protected int health;
    protected int pathPosition;
    protected final int maxHealth;
    protected final int rewardMoney;

    protected Enemy(final int maxHealth, final int speed, final Color color, final double size) {
	super(color, size, speed);
	this.position = new Point(-1000,-1000);
	this.health = maxHealth;
	this.maxHealth = maxHealth;
	this.pathPosition = 0;
    	this.rewardMoney = 5;
    }

    public void draw(final Graphics2D g2d, final int tileSize) {

	g2d.setColor(color);

	final int size = (int) (tileSize * drawScale); // less size is bigger enemy
	final int enemyOffset = tileSize/2 - size/2; // should be changed to fit all sizes
	drawPosX = (int) (drawX * tileSize) + enemyOffset;
	drawPosY = (int) (drawY * tileSize) + enemyOffset;

	g2d.fillOval(getDrawPosX(), getDrawPosY(), size, size);

    }

    public int getHealth(){
	return health;
    }

    public int moveAndTakeDamage(Point nextTile, Point lastTile){

	// path index is used moves the actual point position while the rest is to keep the enemy walking "smooth"

	double difX = nextTile.x - position.x;
	double difY = nextTile.y - position.y;

	drawX =  (position.x + (difX / speed) * moveAmount);
	drawY =  (position.y + (difY / speed) * moveAmount);

        if(moveAmount < speed){ moveAmount++; }
        else{
	    if(nextTile.equals(lastTile)){ return 1; }
	    moveAmount = 1;
	    pathPosition += 1;
	    position = nextTile;
	}
        return 0;
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

    public int getPath() {
	return pathPosition;
    }

}
