package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;
import java.awt.geom.Point2D;

public class BulletProjectile extends Projectile
{

    public BulletProjectile(final Color color, final double drawScale, final double projectileSpeed, final Point2D position,
			    final int penetrationAmount, final int attackPower)
    {
	super(color, drawScale, projectileSpeed, position, penetrationAmount, attackPower);
    }
}
