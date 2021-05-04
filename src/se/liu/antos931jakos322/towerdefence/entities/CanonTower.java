package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

/**
 *
 * A canon tower does damage to all enemies on a tile
 *
 */

public class CanonTower extends Tower
{
    public CanonTower(final TowerType towerType, final Color color, final int cost, final int attackPower, final int range,
		      final int upgradeCost, final ProjectileType projectileType, final int attackSpeed)
    {
	super(towerType, color, cost, attackPower, range, upgradeCost, projectileType, attackSpeed);
    }
}
