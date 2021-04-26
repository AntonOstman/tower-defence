package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

/**
 *
 * A ArrowTower is as type of Tower that has single target damage
 */

public class ArrowTower extends Tower
{

    public ArrowTower(int cost, int attackPower, int range) {
	super(TowerType.ARROW, Color.blue, cost, attackPower, range);

    }


}
