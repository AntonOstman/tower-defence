package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;
import java.awt.geom.Point2D;

public class StandardProjectile extends Projectile
{

    public StandardProjectile(final Color color, final double drawScale, final double projectileSpeed, final Point2D position,
			      final int penetrationAmount, final int attackPower)
    {
	super(color, drawScale, projectileSpeed, position, penetrationAmount, attackPower);
    }
}
