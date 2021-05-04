package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

public class CanonProjectile extends Projectile
{

    public CanonProjectile(final Color color, final double drawScale, final double projectileSpeed, final Point position,
			   final int penetrationAmount, final int attackPower)
    {
	super(color, drawScale, projectileSpeed, position, penetrationAmount, attackPower);
    }
}
