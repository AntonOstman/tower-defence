package se.liu.antos931jakos322.towerdefence.entities.projectiles;

import java.awt.*;

public class BulletProjectile extends Projectile
{
    private static final double SIZE = 0.15;
    private static final double SPEED = 0.5;
    private static final int PENETRATION_AMOUNT = 1;
    private static final Color COLOR = Color.PINK;

    public BulletProjectile()
    {
	super(COLOR, SIZE, SPEED, PENETRATION_AMOUNT);
    }
}
