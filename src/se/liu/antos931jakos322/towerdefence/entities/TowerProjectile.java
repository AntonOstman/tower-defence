package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

public abstract class TowerProjectile extends EntityAbstract
{

    private int attackPower;


    public TowerProjectile(final Color color, final double drawScale, final int speed) {
	super(color, drawScale, speed);
	this.attackPower = 5;
    }

    @Override public Point getPosition() {
	return null;
    }




    public void attack(Enemy enemy, Point startPosition){
        position = startPosition;
        drawMove(position, enemy.getPosition());
        enemy.takeDamage(attackPower);


    }


}
