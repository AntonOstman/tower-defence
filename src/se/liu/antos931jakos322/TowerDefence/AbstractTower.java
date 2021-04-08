package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;

public abstract class AbstractTower implements Entity
{
    protected Point pos;
    protected Color color;
    public AbstractTower(final Point pos, Color color) {
        this.pos = pos;
        this.color = Color.blue;
    }

    @Override public void draw(final Graphics2D g2d, final int TILE_SIZE) {

    }
}
