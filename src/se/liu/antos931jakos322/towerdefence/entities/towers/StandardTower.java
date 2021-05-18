package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;

/**
 *
 * This tower represents a normal tower without any special methods or attribues
 * This tower can be set to have different colors, damage values etc..
 * StandardTower is a skeleton tower which can be used to create other different towers in the game
 * Example:
 * 	StandardTower is used in the game to create a MachineGunTower which sends projectiles faster than other towers
 * 	but each projectile does less damage.
 *
 */

public class StandardTower extends Tower
{

    public StandardTower(final TowerType towerType, final Color color, final int cost, final int attackPower, final int range,
			 final int upgradeCost, final ProjectileType projectileType, final int attackSpeed)
    {
	super(towerType, color, cost, attackPower, range, upgradeCost, projectileType, attackSpeed);
    }
}
