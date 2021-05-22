package se.liu.antos931jakos322.towerdefence.entities.towers;

import se.liu.antos931jakos322.towerdefence.entities.projectiles.ProjectileType;

import java.awt.*;

/**
 *  CanonTower extends Tower and has no extra functionallity
 *  CanonTower is therefore a "stat holder" class
 *
 *  CanonTower has low attack speed, extra high damage, medium range and uses Canon projectiles
 *
 *
 */

public class CanonTower extends Tower
{
    private final static int ATTACK_POWER = 5;
    private final static int COST = 6;
    private final static int RANGE = 3;
    private final static int UPGRADE_COST = 1;
    private final static int ATTACK_SPEED = 20;
    private final static double SIZE = 0.6;
    private final static TowerType TOWER_TYPE = TowerType.CANON;
    private final static Color COLOR = Color.ORANGE;
    private final static ProjectileType PROJECTILE_TYPE = ProjectileType.CANON;

    public CanonTower()
    {
	super(TOWER_TYPE, COLOR, COST, ATTACK_POWER, RANGE, UPGRADE_COST, PROJECTILE_TYPE, ATTACK_SPEED, SIZE);
    }

    @Override public void activate() {

    }
}
