package se.liu.antos931jakos322.towerdefence.entities;


/**
 * An abstract class with the core elements of an enemy
 * An enemy object wanders the map path with the intent of damaging the player
 *
 */


import se.liu.antos931jakos322.towerdefence.entities.Entity;

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
    protected final int rewardMoney;
    protected double drawX;
    protected double drawY;
    protected int speed = 10;
    protected double enemyScale = 0.4;

    protected Color color;

    protected Enemy(final Point position) {

        this.position = position;
	this.health = 100;
	this.pathIndex = 0;
	this.color = Color.red;
	this.path = new ArrayList<>();
	this.maxHealth = health;
    	this.rewardMoney = 5;
    }

    public void draw(final Graphics2D g2d, final int tileSize) {
	g2d.setColor(color);

	final int size = (int) (tileSize * enemyScale); // less size is bigger enemy
	final int enemyOffset = tileSize/2 - size/2; // should be changed to fit all sizes

	int drawPosX = (int) (drawX * tileSize) + enemyOffset;
	int drawPosY = (int) (drawY * tileSize) + enemyOffset;

	g2d.fillOval(drawPosX, drawPosY, size, size);

    }

    public int getHealth(){
	return health;
    }

    public int moveAndTakeDamage(List<Point> path){

	if(pathIndex == path.size()-1){
	    return 1;
	}
	position = path.get(pathIndex);

	// path index is used moves the actual point position while the rest is to keep the enemy walking "smooth"
	double difX = path.get(pathIndex+1).x - position.x;
	double difY = path.get(pathIndex+1).y - position.y;
	drawX =  (position.x + (difX / speed) * moveAmount);
	drawY =  (position.y + (difY / speed) * moveAmount);

        if(moveAmount < speed){ moveAmount++; }
        else{
	    moveAmount = 1;
	    pathIndex += 1;
        }
        return 0;
    }

    public Point getPosition() {
	return position;
    }

    public int getRewardMoney() {
	return rewardMoney;
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
