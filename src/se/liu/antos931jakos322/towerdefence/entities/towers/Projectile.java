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

    protected Projectile(final Color color, final double drawScale, double projectileSpeed, Point2D position, int penetrationAmount, int attackPower) {
	super(color, drawScale);
	this.projectileSize = drawScale*2 ;
	this.attackPower = attackPower;
	this.deltaDirection = null;
	this.startPoint = position; // the start position is always a normal point
	this.projectileSpeed = projectileSpeed;
	this.penetrationAmount = penetrationAmount;
    }


    public void move(){
	move2(deltaDirection, projectileSpeed);
    }


    public void attack(List<Enemy> enemies){

        Enemy firstEnemy = enemies.get(0);
        firstEnemy.takeDamage(attackPower);
	penetrationAmount -= 1;
    }

    //sets the direction for the projectile by calculating the change in x and y
    public void setTarget(final Enemy target) {
	double directionX = target.getDrawPosX() - startPoint.getX();
	double directionY = target.getDrawPosY() - startPoint.getY();

	// sets the start position for the projectile
	drawPosY = startPoint.getY();
	drawPosX = startPoint.getX();
	//deltaDirection = new Point2D(directionX, directionY);

	deltaDirection = new Point2D.Double(directionX,directionY);

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
