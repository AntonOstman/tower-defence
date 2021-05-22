package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExplodingEnemy extends Enemy
{

    private static final int HEALTH = 150;
    private static final double SPEED = 0.03;
    private static final Color COLOR = Color.ORANGE;
    private static final double SIZE = 0.7;
    private static final int DAMAGE = 10;
    private final static Random RND = new Random();


    public ExplodingEnemy() {
	super(HEALTH, SPEED, COLOR, SIZE, DAMAGE);
    }

    @Override public List<Enemy> split(){
	List<Enemy> enemies = new ArrayList<>();
	SpeedEnemy s1 = new SpeedEnemy();
	SpeedEnemy s2 = new SpeedEnemy();
	SpeedEnemy s3 = new SpeedEnemy();
	SpeedEnemy s4 = new SpeedEnemy();

	s1.setPosition(new Point2D.Double(position.getX()+randomPos(), position.getY()+randomPos()));
	s2.setPosition(new Point2D.Double(position.getX()+randomPos(), position.getY()+randomPos()));
	s3.setPosition(new Point2D.Double(position.getX()+randomPos(), position.getY()+randomPos()));
	s4.setPosition(new Point2D.Double(position.getX()+randomPos(), position.getY()+randomPos()));

	s1.setPathProgress(pathProgress);
	s2.setPathProgress(pathProgress);
	s3.setPathProgress(pathProgress);
	s4.setPathProgress(pathProgress);

	enemies.add(s1);
	enemies.add(s2);
	enemies.add(s3);
	enemies.add(s4);

	return enemies;
    }

    public int randomPos(){
	final int span = 5;
	final int minPosition = -3;
	int randomPos = RND.nextInt(span) + minPosition;
	return randomPos;
    }

}
