package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;

public class ArrowTower extends Tower
{


    private static final int ATTACKPOWER = 10;
    private static final int COST = 7;
    private static final int RANGE = 5;
    private static final int UPGRADE_COST = 1;
    private static final int ATTACK_SPEED = 8;
    private static final Color COLOR = Color.BLUE;
    private static final double SIZE = 0.6;
    private static final ProjectileType PROJECTILE_TYPE = ProjectileType.ARROW;
    private static final TowerType TOWER_TYPE = TowerType.ARROW;

    public ArrowTower()
    {
	super(TOWER_TYPE, COLOR, COST, ATTACKPOWER, RANGE, UPGRADE_COST, PROJECTILE_TYPE, ATTACK_SPEED, SIZE);
    }

    @Override public void activate() {

    }
}
