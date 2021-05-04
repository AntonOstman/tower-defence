package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

/**
 *
 * A ArrowTower is as type of Tower that has single target damage
 */

public class ArrowTower extends Tower
{

    public ArrowTower(final TowerType towerType, final Color color, final int cost, final int attackPower, final int range,
		      final int upgradeCost, final ProjectileType projectileType, final int attackSpeed)
    {
	super(towerType, color, cost, attackPower, range, upgradeCost, projectileType, attackSpeed);
    }
}
