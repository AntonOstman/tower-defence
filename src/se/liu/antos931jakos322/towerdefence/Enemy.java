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


    protected Color color;


    protected Enemy(final Point point) {
	this.point = point;
	this.health = 100;
	this.pathIndex = 0;
	this.color = Color.red;
	this.path = new ArrayList<>();
    }

    public void draw(final Graphics2D g2d, final int tileSize) {
	g2d.setColor(color);
	g2d.fillOval(point.x * tileSize, point.y * tileSize, tileSize / 2, tileSize / 2);
    }

    public int getHealth(){
	return health;
    }

    public void move(List<Point> path){
        point = path.get(pathIndex);
	pathIndex += 1;
    }

    public Point getPoint() {
	return point;
    }

    public void takeDamage(int damage){
	health -= damage;
	System.out.println(health);
    }

}
