package se.liu.antos931jakos322.towerDefence;

import java.awt.*;

/**
 *
 * A canon tower does damage to all enemies on a tile
 *
 */

public class CanonTower extends Tower
{


    public CanonTower(final Point pos) {
	super(pos, Color.YELLOW);
	this.attackPower = 5;
    }

    @Override public boolean isFatalAttack(final Enemy enemy) {

        return super.isFatalAttack(enemy);
    }
}
