package se.liu.antos931jakos322.towerdefence.entities.towers;

import se.liu.antos931jakos322.towerdefence.entities.Entity;
import se.liu.antos931jakos322.towerdefence.entities.enemies.Enemy;
import se.liu.antos931jakos322.towerdefence.other.HelperFunctions;
import java.awt.*;
import java.awt.geom.Point2D;


/**
 * Projectile is the abstract class for all projectiles in the game
 * Projectile represents an object that is moving and can do damage to enemies
 * Projectiles have various unique properties:
 * attackPower - represents how much damage the projectile does on a hit
 * PenetrationAmount - represents how many times the projectile can do damage before being "destroyed"
 *
 * Example use:
 * 	Projectile gets created by another entity that tells projectile what to target
 * 	when the projectile is near the something it damages it with attack()
 *
 */


public abstract class Projectile extends Entity
{

    private int attackPower;
    private Point2D startPosition;
    private int penetrationAmount;


    protected Projectile(final Color color, final double size, double speed, Point2D position, int penetrationAmount, int attackPower) {
	super(color, size, speed);
	this.attackPower = attackPower;
	this.startPosition = position;
	this.penetrationAmount = penetrationAmount;
    }


    @Override public void move(){
	super.move();
	double moveX = movePosition.getX();
	double moveY = movePosition.getY();
	final int deltaScale = 10;
	// we increse the delta with a constant to make sure when the projectile is shot near an enemy it does not bug out
	double deltaX = deltaScale*(moveX - position.getX());
	double deltaY = deltaScale*(moveY - position.getY());
	// we increase the target position with the deltax and deltay to keep the projectile from stopping once it reaches the position
	Point2D newPos = new Point2D.Double(movePosition.getX() + deltaX,movePosition.getY() + deltaY);
	movePosition = newPos;
    }

    public boolean canAttack(Enemy enemy){
        // the projectile hit range is increased with a constant to make sure it actually hits when near an enemy
        int hitRangeScale = 2;
        double hitRange = size * hitRangeScale;
	if (HelperFunctions.isNear(position, enemy.getPosition(), hitRange)) {
	return true;
	}
	else{ return false;}
    }

    public void attack(Enemy enemy){
	enemy.takeDamage(attackPower);
	penetrationAmount -= 1;

    }

    //sets the direction for the projectile by calculating the change in x and y
    public void setTarget(final Enemy target) {
	this.movePosition = target.getPosition();
	this.position = startPosition;
    }

    public void draw(final Graphics2D g2d, final int tileSize){
	// drawPosX could be changed to a double and then in this draw method we cast to int
	// problem with a direct int is for example we cannot go "between" tiles only straight to it

	g2d.setColor(color);

	final int size = (int) (tileSize * this.size);
	final int offset = tileSize / 2 - size / 2;

	int drawPositionX = (int) (position.getX() * tileSize) + offset;
	int drawPositionY = (int) (position.getY() * tileSize) + offset;

	g2d.fillOval(drawPositionX, drawPositionY, size, size);
    }

    public int getPenetrationAmount() {
	return penetrationAmount;
    }

}
