package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;

public abstract class AbstractTower implements Entity
{
    private Point pos;
    private Color color;
    public AbstractTower(final Point pos, Color color) {
        this.pos = pos;
        this.color = Color.blue;
    }

    @Override public void draw() {

    }
}
