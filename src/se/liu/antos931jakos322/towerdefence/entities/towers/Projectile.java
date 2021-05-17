package se.liu.antos931jakos322.towerdefence.entities.towers;

import se.liu.antos931jakos322.towerdefence.entities.Entity;
import se.liu.antos931jakos322.towerdefence.entities.enemies.Enemy;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class Projectile extends Entity
{

    private int attackPower;
    private Point2D startPoint;
    private Point2D deltaDirection;
    private double projectileSpeed;
    private int penetrationAmount;
    private double projectileSize;
    private Point2D targetPosition;


    protected Projectile(final Color color, final double drawScale, double projectileSpeed, Point2D position, int penetrationAmount, int attackPower) {
	super(color, drawScale, projectileSpeed);
	this.targetPosition = null;
	this.projectileSize = drawScale*2 ;
	this.attackPower = attackPower;
	this.deltaDirection = null;
	this.startPoint = position; // the start position is always a normal point
	this.penetrationAmount = penetrationAmount;
    }


    public void move(){
	move(targetPosition);
	// we increase the target position with the deltax and deltay to keep the projectile from stopping once it reaches the position
	Point2D deltaPos = new Point2D.Double(targetPosition.getX() - position.getX(), targetPosition.getY() - position.getY());
	Point2D newPos = new Point2D.Double(targetPosition.getX() + deltaPos.getX(),targetPosition.getY() + deltaPos.getY());
	targetPosition = newPos;
    }


    public void attack(List<Enemy> enemies){

        Enemy firstEnemy = enemies.get(0);
        firstEnemy.takeDamage(attackPower);
	penetrationAmount -= 1;
    }

    //sets the direction for the projectile by calculating the change in x and y
    public void setTarget(final Enemy target) {
	this.targetPosition = target.getPosition();
	this.position = startPoint;
    }

    public void draw(final Graphics2D g2d, final int tileSize){
	// drawPosX could be changed to a double and then in this draw method we cast to int
	// problem with a direct int is for example we cannot go "between" tiles only straight to it

	g2d.setColor(color);

	final int size = (int) (tileSize * drawScale);
	final int offset = tileSize / 2 - size / 2;

	int drawPositionX = (int) (position.getX() * tileSize) + offset;
	int drawPositionY = (int) (position.getY() * tileSize) + offset;

	g2d.fillOval(drawPositionX, drawPositionY, size, size);
    }

    public double getProjectileSize() {
	return projectileSize;
    }

    public int getPenetrationAmount() {
	return penetrationAmount;
    }

    public int getAttackPower() {
	return attackPower;
    }
}
