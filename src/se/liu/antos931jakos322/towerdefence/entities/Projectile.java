package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

public abstract class Projectile extends EntityAbstract
{

    private int attackPower;
    private Enemy target;
    private double k; // k as in the value in the formula y = kx + m
    private double m;

    protected Projectile(final Color color, final double drawScale, final int speed, Point position) {
	super(color, drawScale, speed);
	this.attackPower = 1;
	this.target = null;
	this.k = 0;
	this.m = 0;
	this.position = position;

    }

    @Override public Point getPosition() {
	return position;
    }


    public void createProjectilePath(){
        int y2 = target.getDrawPosY();
        int x2 = target.getDrawPosX();

        k = (double) (y2 - position.y) / (x2 - position.x);
        m = position.y -k * (position.x);
    }

    public void attack(){
	target.takeDamage(attackPower); // temporary fix to keep the game playable

    }

    public void setTarget(final Enemy target) {
	this.target = target;
	createProjectilePath();

    }
}
