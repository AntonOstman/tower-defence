package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

/**
 *
 * A canon tower does damage to all enemies on a tile
 *
 */

public class CanonTower extends Tower
{

    public CanonTower(int cost, int attackPower, int range,int upgradeCost) {
	super(TowerType.CANON, Color.YELLOW, cost, attackPower, range, upgradeCost);
    }

}
