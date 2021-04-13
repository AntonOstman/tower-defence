package se.liu.antos931jakos322.towerdefence;


/**
 * An abstract class with the core elements of an enemy
 * An enemy object wanders the map path with the intent of damaging the player
 *
 */


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Enemy implements Entity
{
    protected int level;
    protected List<Point> path;
    protected Point position;
    protected int health;
    protected int pathIndex;
    protected int moveAmount = 0;
    protected final int maxHealth;

    protected double drawX;
    protected double drawY;

    protected Color color;

    protected Enemy(final Point position) {
	this.position = position;
	this.health = 1000;
	this.pathIndex = 0;
	this.color = Color.red;
	this.path = new ArrayList<>();
	this.maxHealth = health;
    }

    public void draw(final Graphics2D g2d, final int tileSize) {
	g2d.setColor(color);
	final int centrationConstant = 10; // should be changed to fit all sizes
	final double enemyScale = 0.5;
	final int size = (int) (tileSize * enemyScale); // less size is bigger enemy

	int drawPosX = (int) (drawX * tileSize) + centrationConstant;
	int drawPosY = (int) (drawY * tileSize) + centrationConstant;

	g2d.fillOval(drawPosX, drawPosY, size, size);

    }

    public int getHealth(){
	return health;
    }

    public int moveAndTakeDamage(List<Point> path){
	int moveSmoothness = 10;
	if(pathIndex == path.size()-1){
	    return 1;
	}
	position = path.get(pathIndex);

	// path index moves the actual position while the rest is to keep the enemy walking "smooth"
	double difX = path.get(pathIndex+1).x - position.x;
	double difY = path.get(pathIndex+1).y - position.y;
	drawX =  (position.x + (difX / moveSmoothness) * moveAmount);
	drawY =  (position.y + (difY / moveSmoothness) * moveAmount);

        if(moveAmount < moveSmoothness){ moveAmount++; }
        else{
	    moveAmount = 1;
	    pathIndex += 1;
        }
        return 0;
    }

    public Point getPosition() {
	return position;
    }

    public void takeDamage(int damage){
        if (health - damage < 0) {
	    health = 0;
	}
        else {
            health -= damage;
	}
	double procentageHP = (double) health / maxHealth;

	double inverceProcentageHP = 1 - procentageHP;

	try {
	    color = new Color(255, (int) (inverceProcentageHP * 255), (int) (inverceProcentageHP * 255));
	}
	catch (IllegalArgumentException e){
	    System.out.println((inverceProcentageHP * 255));
	    System.out.println(inverceProcentageHP);
	    System.out.println(health);
	    System.out.println(maxHealth);
	    System.out.println((double) health / maxHealth + " div");
	}


    }

}
