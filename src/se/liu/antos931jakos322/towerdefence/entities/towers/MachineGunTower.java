package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;

public class MachineGunTower extends Tower
{

    final static int ATTACK_POWER = 3;
    final static int COST = 7;
    final static int RANGE = 6;
    final static int UPGRADE_COST = 1;
    final static int ATTACK_SPEED = 1;
    final static Color COLOR = Color.PINK;
    final static TowerType TOWER_TYPE = TowerType.MACHINE_GUN;
    final static double SIZE = 0.6;
    final static ProjectileType PROJECTILE_TYPE = ProjectileType.MACHINE;

    public MachineGunTower()
    {
	super(TOWER_TYPE, COLOR, COST, ATTACK_POWER, RANGE, UPGRADE_COST, PROJECTILE_TYPE, ATTACK_SPEED, SIZE);
    }

    @Override public void activate() {

    }
}
