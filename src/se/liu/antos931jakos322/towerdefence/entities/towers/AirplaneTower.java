package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;
import java.awt.geom.Point2D;

public class AirplaneTower extends Tower
{

    private double angle;
    private Point2D startPoint;

    public AirplaneTower(final TowerType towerType, final Color color, final int cost, final int attackPower, final int range,
			 final int upgradeCost, final ProjectileType projectileType, final int attackSpeed)
    {
	super(towerType, color, cost, attackPower, range, upgradeCost, projectileType, attackSpeed);
	this.angle = 0;
	this.startPoint = null;
    }


    @Override public void activate() {
	move();
        super.activate();
    }

    @Override public void setPosition(final Point2D position) {
	startPoint = position;
        super.setPosition(position);
    }

    @Override public void draw(final Graphics2D g2d, final int tileSize) {
        super.draw(g2d, tileSize);

    }

    @Override public void move() {
	speed = 0.03;
	final int radius = 4;
	angle += speed; // in this context speed is a angle speed
        double newX = startPoint.getX() + radius * Math.sin(angle);
        double newY = startPoint.getY() + radius * Math.cos(angle);

        Point2D newPos = new Point2D.Double(newX,newY);
	position = newPos;
        //
	//super.move(newPos);
    }
}
