package se.liu.antos931jakos322.towerdefence.entities.projectiles;

import se.liu.antos931jakos322.towerdefence.entities.Entity;
import se.liu.antos931jakos322.towerdefence.entities.enemies.Enemy;
import se.liu.antos931jakos322.towerdefence.other.HelperFunctions;
import java.awt.*;
import java.awt.geom.Point2D;


/**
 * Projectile is the abstract class for all projectiles in the game
 * Projectile represents an object that moves and can do damage to enemies
 * Projectiles have various unique properties:
 * attackPower - represents how much damage the projectile does on a hit
 * PenetrationAmount - represents how many times the projectile can do damage before being "destroyed"
 *
 * Example use:
 * 	Projectile gets created by another entity that tells projectile what to target
 * 	when the projectile is near the target it does damage to it.
 *
 */


public abstract class Projectile extends Entity
{

    private Point2D startPosition;
    private int penetrationAmount;


    protected Projectile(final Color color, final double size, double speed, int penetrationAmount) {
	super(color, size, speed, 0);
	this.startPosition = null;
	this.penetrationAmount = penetrationAmount;
    }

    /**
     * Move moves the projectile forwards towards the designated moveposition.
     * To keep the projectile from stopping the movePosition is increased with the delta x and y coordinates
     *
     */


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

    /**
     * canAttack returns wheter a projectile can attack an enemy
     *
     * @param enemy the enemy object that is being checked if it is possible to attack
     * @return true or false if an attack is possible or not
     */

    public boolean canAttack(Enemy enemy){
        // the projectile hit range is increased with a constant to make sure it actually hits when near an enemy
        int hitRangeScale = 2;
        double hitRange = size * hitRangeScale;
	if (HelperFunctions.isNear(position, enemy.getPosition(), hitRange)) {
	return true;
	}
	else{ return false;}
    }

    /**
     * attacks an enemy
     *
     * @param enemy the enemy object to attack
     */

    public void attack(Enemy enemy){
	enemy.takeDamage(attackPower);
	penetrationAmount -= 1;

    }

    /**
     * Sets movePosition to the enemy target position
     * Also saves the start positoin in startPosition
     *
     * @param target the enemy object to target
     */
    public void setTarget(final Enemy target) {
	this.movePosition = target.getPosition();
	this.position = startPosition;
    }

    /**
     * draws the projectile as a circle with the specified size and color fields
     * with an offset to center the projectile
     *
     * @param g2d grapchis object
     * @param gameScale the scale of the game graphics
     */

    public void draw(final Graphics2D g2d, final int gameScale){
	// drawPosX could be changed to a double and then in this draw method we cast to int
	// problem with a direct int is for example we cannot go "between" tiles only straight to it

	g2d.setColor(color);

	final int size = (int) (gameScale * this.size);
	final int offset = gameScale / 2 - size / 2;

	int drawPositionX = (int) (position.getX() * gameScale) + offset;
	int drawPositionY = (int) (position.getY() * gameScale) + offset;

	g2d.fillOval(drawPositionX, drawPositionY, size, size);
    }

    public int getPenetrationAmount() {
	return penetrationAmount;
    }

}
