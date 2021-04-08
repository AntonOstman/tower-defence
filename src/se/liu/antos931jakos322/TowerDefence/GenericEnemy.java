package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;

public class GenericEnemy extends Enemy
{
    private int level;
    private int[] path;
    private Point point;


    public void draw(final Graphics2D g2d, final int TILE_SIZE) {
        g2d.setColor(Color.RED);
        g2d.fillOval(point.x, point.y, TILE_SIZE, TILE_SIZE);
    }



    public GenericEnemy(Point point) {
        this.point = point;
    }

}
