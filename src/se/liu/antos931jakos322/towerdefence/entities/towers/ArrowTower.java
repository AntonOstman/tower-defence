package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;

public class ArrowTower extends Tower
{


    static final int ATTACKPOWER = 10;
    static final int COST = 7;
    static final int RANGE = 5;
    static final int UPGRADE_COST = 1;
    static final int ATTACK_SPEED = 8;
    static final Color COLOR = Color.BLUE;
    static final double SIZE = 0.6;
    static final ProjectileType PROJECTILE_TYPE = ProjectileType.ARROW;
    static final TowerType TOWER_TYPE = TowerType.ARROW;

    public ArrowTower()
    {
	super(TOWER_TYPE, COLOR, COST, ATTACKPOWER, RANGE, UPGRADE_COST, PROJECTILE_TYPE, ATTACK_SPEED, SIZE);
    }

    @Override public void activate() {

    }
}
