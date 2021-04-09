package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Enemy implements Entity
{
    protected int level;
    protected int[] path;
    protected Point point;
    protected int health;
    protected int pathIndex;

    public Enemy(final Point point) {
	this.point = point;
	this.health = 100;
	this.pathIndex = 0;
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
