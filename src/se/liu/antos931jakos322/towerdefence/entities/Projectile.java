package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;
import java.util.List;

public abstract class Projectile extends EntityAbstract
{

    private int attackPower;
    private Enemy target;
    private double k; // k as in the value in the formula y = kx + m
    private double m;
    private Point startPoint;
    private Point targetStartDrawPoint;
    private Point direction;


    protected Projectile(final Color color, final double drawScale, final int speed, Point position) {
	super(color, drawScale, speed);
	this.attackPower = 1;
	this.target = null;
	this.k = 0;
	this.m = 0;
	this.startPoint = position;
	this.targetStartDrawPoint = null;

    }



    public void createProjectilePath(){

        //k = (double) (y2 - position.y) / (x2 - position.x);
        //m = position.y - k * (position.x);
    }

    public void move(){

	Point myDraw = new Point(startPoint.x*50,startPoint.y*50);

	double deltaX = targetStartDrawPoint.x - myDraw.x;
	double deltaY = targetStartDrawPoint.y - myDraw.y;

	double drawPosy = getDrawPosY() + direction.y * 0.1;
	double drawPosx = getDrawPosX() + direction.x * 0.1;



	setDrawPosY((int) drawPosy);
	setDrawPosX((int) drawPosx);
        //drawMove2(direction.x, direction.y);
	Point position = new Point(getDrawPosX()/50,getDrawPosY()/50);
	setPosition(position);

	//target.takeDamage(attackPower); // temporary fix to keep the game playable

    }

    public void attack(List<Enemy> enemies){

        Enemy firstEnemy = enemies.get(0);
        firstEnemy.takeDamage(attackPower);

    }


    public void setTarget(final Enemy target) {
	this.target = target;
	int directionX = target.getDrawPosX() - startPoint.x * 50;
	int directionY = target.getDrawPosY() - startPoint.y * 50;
	setDrawPosY(startPoint.y*50);
	setDrawPosX(startPoint.x*50);

	direction = new Point(directionX, directionY);
	targetStartDrawPoint = new Point(target.getDrawPosX(), target.getDrawPosY());
	//targetStartPoint = target.getPosition();
	createProjectilePath();
    }

    public int getAttackPower() {
	return attackPower;
    }
}
