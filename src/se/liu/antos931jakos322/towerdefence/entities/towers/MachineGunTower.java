package se.liu.antos931jakos322.towerdefence.entities.towers;

import se.liu.antos931jakos322.towerdefence.entities.projectiles.ProjectileType;

import java.awt.*;

/**
 *  BulletProjectile extends Tower and has no extra functionallity
 *  BulletProjectile is therefore a "stat holder" class
 *
 *  MachineGunTower has high attack speed, low damage, high range and uses Bullet projectiles
 *
 */

public class MachineGunTower extends Tower
{

    private final static int ATTACK_POWER = 3;
    private final static int COST = 7;
    private final static int RANGE = 7;
    private final static int UPGRADE_COST = 1;
    private final static int ATTACK_SPEED = 1;
    private final static Color COLOR = Color.PINK;
    private final static TowerType TOWER_TYPE = TowerType.MACHINE_GUN;
    private final static double SIZE = 0.6;
    private final static ProjectileType PROJECTILE_TYPE = ProjectileType.MACHINE;

    public MachineGunTower()
    {
	super(TOWER_TYPE, COLOR, COST, ATTACK_POWER, RANGE, UPGRADE_COST, PROJECTILE_TYPE, ATTACK_SPEED, SIZE);
    }

    @Override public void activate() {

    }
}
