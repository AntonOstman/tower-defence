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

    public AirplaneTower(final TowerType towerType, final Color color, final int cost, final int attackPower, final int range,
			 final int upgradeCost, final ProjectileType projectileType, final int attackSpeed, final double speed, final double size)
    {
	super(towerType, color, cost, attackPower, range, upgradeCost, projectileType, attackSpeed, speed, size);
	this.angle = 0;
    }


    @Override public void activate() {
	move();
    }



    @Override public void move() {
	speed = 0.03;
	final int radius = 4;
	angle += speed; // in this context speed is a angle speed
	// use sin and cos combined with the changing angle to circle the tower
	// around where it was placed
        double newX = startPosition.getX() + radius * Math.sin(angle);
        double newY = startPosition.getY() + radius * Math.cos(angle);

        Point2D newPos = new Point2D.Double(newX,newY);
	position = newPos;
    }
}
