package se.liu.antos931jakos322.towerdefence.entities.projectiles;

import java.awt.*;

/**
 *
 * CanonProjectile is a projectile that is comparatively strong when looking at other projectiles.
 * It moves at a slow speed, has a large size and it penetrates 10 enemies.
 * BulletProjectiles extends Projectile with no extra features and works as a "stat holder"
 *
 */



public class CanonProjectile extends Projectile
{
    private static final double SIZE = 0.35;
    private static final double SPEED = 0.15;
    private static final int PENETRATION_AMOUNT = 10;
    private final static Color COLOR = Color.DARK_GRAY;


    public CanonProjectile() {
        super(COLOR, SIZE, SPEED, PENETRATION_AMOUNT);
    }
}
