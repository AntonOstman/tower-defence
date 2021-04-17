package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

/**
 *
 * An Entity is an object that acts as something on the map
 *  example:
 *      a tower or enemy object
 *
 */
public interface Entity
{
    public void draw(final Graphics2D g2d, final int tileSize);
    public Point getPosition();
}
