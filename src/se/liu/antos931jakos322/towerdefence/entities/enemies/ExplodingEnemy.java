package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * ExplodingEnemy is an enemy type that extends the abstract class Enemy.
 * ExplodingEnemy have low health but when health reaches 0, 4 speedEnemy spawns.
 */
public class ExplodingEnemy extends Enemy
{

    private static final int HEALTH = 150;
    private static final double SPEED = 0.03;
    private static final Color COLOR = Color.ORANGE;
    private static final double SIZE = 0.7;
    private static final int DAMAGE = 10;



    public ExplodingEnemy() {
	super(HEALTH, SPEED, COLOR, SIZE, DAMAGE);
    }

    @Override public List<Enemy> split(){
	List<Enemy> enemies = new ArrayList<>();
	
	final int distance = 5;
	final int numberOfSpawnedEnemeies = 4;
	for (int i = 0; i < numberOfSpawnedEnemeies; i++) {
	    SpeedEnemy s = new SpeedEnemy();
	    s.setPosition(new Point2D.Double(position.getX() + splitRandomPos(distance), position.getY() + splitRandomPos(distance)));
	    s.setPathProgress(pathProgress);
	    enemies.add(s);
	}

	return enemies;
    }



}
