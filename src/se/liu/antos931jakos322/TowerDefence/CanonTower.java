package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;

public class CanonTower extends AbstractTower
{


    public CanonTower(final Point pos) {
	super(pos, Color.YELLOW);
	this.attackPower = 5;
    }

    @Override public boolean isFatalAttack(final Enemy enemy) {


        return super.isFatalAttack(enemy);
    }
}
