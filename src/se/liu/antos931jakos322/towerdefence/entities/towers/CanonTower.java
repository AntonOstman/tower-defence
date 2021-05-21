package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;

public class CanonTower extends Tower
{
    final static int ATTACK_POWER = 5;
    final static int COST = 7;
    final static int RANGE = 3;
    final static int UPGRADE_COST = 1;
    final static int ATTACK_SPEED = 20;
    final static double SIZE = 0.6;
    final static TowerType TOWER_TYPE = TowerType.CANON;
    final static Color COLOR = Color.ORANGE;
    final static ProjectileType PROJECTILE_TYPE = ProjectileType.CANON;

    public CanonTower()
    {
	super(TOWER_TYPE, COLOR, COST, ATTACK_POWER, RANGE, UPGRADE_COST, PROJECTILE_TYPE, ATTACK_SPEED, SIZE);
    }

    @Override public void activate() {

    }
}
