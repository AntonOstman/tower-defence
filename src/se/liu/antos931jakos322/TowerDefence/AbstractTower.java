package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;

public abstract class AbstractTower implements Entity
{
    private Point pos;

    public AbstractTower(final Point pos) {
        this.pos = pos;
    }

    @Override public void draw() {

    }
}
