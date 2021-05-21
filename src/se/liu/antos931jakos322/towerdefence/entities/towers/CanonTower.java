package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;

public class CanonTower extends Tower
{
    private final static int ATTACK_POWER = 5;
    private final static int COST = 7;
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
