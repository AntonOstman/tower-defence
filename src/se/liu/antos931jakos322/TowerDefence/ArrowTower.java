package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;

public class ArrowTower extends AbstractTower
{
    public ArrowTower(final Point pos) {
	super(pos, Color.blue);
    }

    @Override public void draw(final Graphics2D g2d, final int TILE_SIZE) {
	g2d.setColor(Color.blue);
	g2d.fillRect(pos.x*TILE_SIZE, pos.y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
}
