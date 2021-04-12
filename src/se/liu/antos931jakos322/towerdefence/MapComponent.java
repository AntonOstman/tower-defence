package se.liu.antos931jakos322.towerdefence;

import javax.swing.*;
import java.awt.*;

/**
 * A graphics component used for drawing the map and objects on it.
 *
 *
 */

public class MapComponent extends JComponent
{
    private Map map;
    private final int mapX;
    private final int mapY;
    private final static int TILE_SIZE = 50;
    private final static int MARGIN = 0;

    public MapComponent(final Map map) {
	this.map = map;
	this.mapY = map.getHeight();
	this.mapX = map.getWidth();
    }

    public Dimension getPreferredSize(){

        return new Dimension(mapX*TILE_SIZE + 100,mapY*TILE_SIZE +100);

    }

    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);

	final Graphics2D g2d = (Graphics2D) g;



	for (int y = 0; y < mapY; y++) {
	    for (int x = 0; x < mapX; x++) {
		Tile currentTile = map.getTile(x,y);
		currentTile.drawTile(g2d,MARGIN,TILE_SIZE);
	    }
	}
	for (Tower tower: map.getTowers()) {
	    tower.draw(g2d, TILE_SIZE);
	}
	for(Enemy enemy : map.getEnemies()){
	    enemy.draw(g2d, TILE_SIZE);
	}
    }

}
