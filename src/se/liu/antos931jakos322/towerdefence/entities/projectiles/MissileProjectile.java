package se.liu.antos931jakos322.towerdefence.entities.projectiles;

import java.awt.*;

/**
 *
 * MissileProjectile is a projectile that is comparatively average when looking at other projectiles.
 * It moves at a medium speed, has a medium size and it penetrates only two enemies.
 * MissileProjectile also implements a new move() method to imitate a target heatseaking behaviour.
 * otherwise extends Projectile with no extra features and works as a "stat holder"
 *
 */



public class MissileProjectile extends Projectile
{
    private static final double SIZE = 0.20;
    private static final double SPEED = 0.26;
    private static final int PENETRATION_AMOUNT = 2;
    private static final Color COLOR = Color.CYAN;


    @Override public void move() {
        super.move();
        movePosition = targetEntity.getPosition();
    }

    public MissileProjectile() {
	super(COLOR, SIZE, SPEED, PENETRATION_AMOUNT);
    }
}
