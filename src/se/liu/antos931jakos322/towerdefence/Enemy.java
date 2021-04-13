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
    protected Point point;
    protected int health;
    protected int pathIndex;
    protected int n = 0;
    protected final int maxHealth;

    protected double drawX;
    protected double drawY;


    protected Color color;


    protected Enemy(final Point point) {
	this.point = point;
	this.health = 10000;
	this.pathIndex = 0;
	this.color = Color.red;
	this.path = new ArrayList<>();
	this.maxHealth = health;
    }

    public void draw(final Graphics2D g2d, final int tileSize) {
	g2d.setColor(color);

	g2d.fillOval((int) (drawX * tileSize) + 10, (int) (drawY * tileSize) + 10, tileSize / 2, tileSize / 2);

    }

    public int getHealth(){
	return health;
    }

    public int moveAndTakeDamage(List<Point> path){
	int moveSmoothness = 10;
	if(pathIndex == path.size()-1){
	    return 1;
	}
        point = path.get(pathIndex);


	double difX =  path.get(pathIndex+1).x - point.x;
	double difY =  path.get(pathIndex+1).y - point.y;
	drawX =  (point.x + (difX/moveSmoothness)*n);
	drawY =  (point.y + (difY/moveSmoothness)*n);

        if(n < moveSmoothness){ n++; }
        else{
            n = 1;
	    pathIndex += 1;
        }
        return 0;
    }

    public Point getPoint() {
	return point;
    }

    public void takeDamage(int damage){
	health -= damage;

	double procentageHP = (double) health / maxHealth;
	System.out.println(procentageHP);
	double inverceProcentageHP = 1 - procentageHP;

	color = new Color(255, (int) (inverceProcentageHP * 255), (int) (inverceProcentageHP * 255));




    }

}
