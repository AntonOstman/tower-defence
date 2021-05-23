package se.liu.antos931jakos322.towerdefence.entities.projectiles;

import se.liu.antos931jakos322.towerdefence.entities.Entity;

import java.awt.*;

/**
 *
 * CanonProjectile is a projectile that is comparatively strong when looking at other projectiles.
 * It moves at a slow speed, has a large size and it penetrates 10 enemies.
 * BulletProjectiles extends Projectile with no extra features and works as a "stat holder"
 *
 */



public class ExplodingProjectile extends Projectile
{
    private static final double SIZE = 0.35;
    private static final double SPEED = 0.15;
    private static final int PENETRATION_AMOUNT = 10;
    private final static Color COLOR = Color.DARK_GRAY;
    private final static int EXPLOSION_TIME = 10;
    private int explosionTimer;
    private boolean isExploding;

    public ExplodingProjectile() {
        super(COLOR, SIZE, SPEED, PENETRATION_AMOUNT);
        this.explosionTimer = 1;
        this.isExploding = false;
    }

    @Override public void move() {
        super.move();
        if(explosionTimer == 0){
            penetrationAmount = 0;
        }
        else if(isExploding){
            explosionTimer--;
        }
    }

    @Override public void attack(final Entity entity) {
        super.attack(entity);
        if(!isExploding) {
            movePosition = position;
            explosionTimer = EXPLOSION_TIME;
            isExploding = true;
            this.color = Color.ORANGE;
            size = SIZE * 3;
        }

    }
}
