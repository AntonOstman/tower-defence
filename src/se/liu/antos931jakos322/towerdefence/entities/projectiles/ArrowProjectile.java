package se.liu.antos931jakos322.towerdefence.entities.projectiles;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 *
 * ArrowProjectile is a projectile that is comparatively average when looking at other projectiles.
 * It moves at a medium speed, has a medium size and it penetrates only two enemies.
 * ArrowProjectile extends a Projectile with no extra features and works as a "stat holder"
 *
 */

public class ArrowProjectile extends Projectile
{
    private static final double SIZE = 0.23;
    private static final double SPEED = 0.4;
    private static final int PENETRATION_AMOUNT = 2;
    private static final Color COLOR = Color.RED;

    public ArrowProjectile() {
        super(COLOR, SIZE, SPEED, PENETRATION_AMOUNT);
    }
}
