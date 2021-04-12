package se.liu.antos931jakos322.towerDefence;

import java.awt.*;

public class GenericEnemy extends Enemy
{

    public void draw(final Graphics2D g2d, final int tileSize) {
        g2d.setColor(Color.RED);
        g2d.fillOval(point.x * tileSize, point.y * tileSize, tileSize / 2, tileSize / 2);
    }

    public GenericEnemy(final Point point) {
        super(point);
    }



    public void remove(){

    }

}
