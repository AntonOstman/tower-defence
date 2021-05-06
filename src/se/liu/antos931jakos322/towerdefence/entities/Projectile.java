package se.liu.antos931jakos322.towerdefence.entities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class Projectile extends EntityAbstract
{

    private int attackPower;
    private Point startPoint;
    private Point2D deltaDirection;
    private double projectileSpeed;
    private int penetrationAmount;

    protected Projectile(final Color color, final double drawScale, double projectileSpeed, Point position, int penetrationAmount, int attackPower) {
	super(color, drawScale);
	this.attackPower = attackPower;
	this.deltaDirection = null;
	this.startPoint = position;
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
	double directionX = target.getDrawPosX() - startPoint.x * TILE_SIZE;
	double directionY = target.getDrawPosY() - startPoint.y * TILE_SIZE;

	// sets the start position for the projectile
	drawPosY = startPoint.y * TILE_SIZE;
	drawPosX = startPoint.x * TILE_SIZE;
	//deltaDirection = new Point2D(directionX, directionY);

	deltaDirection = new Point2D.Double(directionX,directionY);

    }



    public int getPenetrationAmount() {
	return penetrationAmount;
    }

    public int getAttackPower() {
	return attackPower;
    }
}
