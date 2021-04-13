package se.liu.antos931jakos322.towerdefence;

import java.awt.*;

/**
 *
 * An Entity is an object that in some way exists in the map
 *
 */
public interface Entity
{
    public void draw(final Graphics2D g2d, final int tileSize);
    public Point getPosition();
}
