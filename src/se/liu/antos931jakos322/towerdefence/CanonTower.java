package se.liu.antos931jakos322.towerdefence;

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

    @Override public boolean attackAndReturnIsFatal(final Enemy enemy) {

        return super.attackAndReturnIsFatal(enemy);
    }
}
