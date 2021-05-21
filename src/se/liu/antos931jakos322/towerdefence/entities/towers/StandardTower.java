package se.liu.antos931jakos322.towerdefence.entities.towers;

import se.liu.antos931jakos322.towerdefence.entities.projectiles.ProjectileType;

import java.awt.*;

/**
 *
 * This tower represents a normal tower without any special methods or attribues
 * StandardTower is placed on one location and does not move from it
 * StandardTower is a skeleton tower which can be used to create other different towers
 * Example:
 * 	StandardTower can be used in a game to create a MachineGunTower which sends projectiles faster than other towers
 * 	but each projectile does less damage.
 *
 */

public class StandardTower extends Tower
{

    public StandardTower(final TowerType towerType, final Color color, final int cost, final int attackPower, final int range,
			 final int upgradeCost, final ProjectileType projectileType, final int attackSpeed, final double size)
    {
	super(towerType, color, cost, attackPower, range, upgradeCost, projectileType, attackSpeed, size);
    }

    @Override public void activate() {

    }
}
