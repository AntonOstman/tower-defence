package se.liu.antos931jakos322.towerdefence.entities.towers;

/**
 *
 * AirplaneTower is a class extending the absract class Tower
 * AirplaneTower can move in circles while still acting as a Tower
 *
 */


import java.awt.*;
import java.awt.geom.Point2D;

public class AirplaneTower extends Tower
{

    private double angle;
    private final static int ATTACK_POWER = 5;
    private final static int COST = 7;
    private final static int RANGE = 5;
    private final static int UPGRADE_COST = 1;
    private final static int ATTACK_SPEED = 1;
    private final static double SPEED = 0.03;
    private final static double SIZE = 0.6;
    private final static Color COLOR = Color.red;
    private final static TowerType TOWER_TYPE = TowerType.AIRPLANE;
    private final static ProjectileType PROJECTILE_TYPE = ProjectileType.MACHINE;

    public AirplaneTower()
    {
	super(TOWER_TYPE, COLOR, COST, ATTACK_POWER, RANGE, UPGRADE_COST, PROJECTILE_TYPE, ATTACK_SPEED, SPEED, SIZE);
	this.angle = 0;
    }


    @Override public void activate() {
	move();
    }



    @Override public void move() {
	final int radius = 4;
	angle += SPEED; // in this context speed is a angle speed
	// use sin and cos combined with the changing angle to circle the tower
	// around where it was placed
        double newX = startPosition.getX() + radius * Math.sin(angle);
        double newY = startPosition.getY() + radius * Math.cos(angle);

        Point2D newPos = new Point2D.Double(newX,newY);
	position = newPos;
    }
}
