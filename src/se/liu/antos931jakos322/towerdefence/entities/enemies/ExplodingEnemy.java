package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

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
	SpeedEnemy s1 = new SpeedEnemy();
	s1.setPosition(new Point2D.Double(position.getX()+1, position.getY()+1));
	s1.setPathProgress(pathProgress);
	enemies.add(s1);
	SpeedEnemy s2 = new SpeedEnemy();
	s2.setPosition(new Point2D.Double(position.getX()-2, position.getY()+1));
	s2.setPathProgress(pathProgress);
	enemies.add(s2);
	SpeedEnemy s3 = new SpeedEnemy();
	s3.setPosition(new Point2D.Double(position.getX()+2, position.getY()-1));
	s3.setPathProgress(pathProgress);
	enemies.add(s3);
	SpeedEnemy s4 = new SpeedEnemy();
	s4.setPosition(new Point2D.Double(position.getX()-1, position.getY()-2));
	s4.setPathProgress(pathProgress);
	enemies.add(s4);

	return enemies;
    }
}
