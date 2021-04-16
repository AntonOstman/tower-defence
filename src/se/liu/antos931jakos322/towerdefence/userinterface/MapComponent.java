package se.liu.antos931jakos322.towerdefence.userinterface;

import se.liu.antos931jakos322.towerdefence.entities.Enemy;
import se.liu.antos931jakos322.towerdefence.maplogic.Map;
import se.liu.antos931jakos322.towerdefence.maplogic.MapListener;
import se.liu.antos931jakos322.towerdefence.maplogic.Tile;
import se.liu.antos931jakos322.towerdefence.entities.Tower;

import javax.swing.*;
import java.awt.*;

/**
 * A graphics component used for drawing the map and objects on it.
 *
 *
 */

public class MapComponent extends JComponent implements MapListener
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

	int width = mapX*( MARGIN + TILE_SIZE);
	int height = mapY*(MARGIN + TILE_SIZE);
        return new Dimension(width, height);

    }

    @Override public void mapChanged() {
	repaint();
    }

    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);

	final Graphics2D g2d = (Graphics2D) g;

	for (int y = 0; y < mapY; y++) {
	    for (int x = 0; x < mapX; x++) {
		Tile currentTile = map.getTile(x, y);
		currentTile.drawTile(g2d, MARGIN, TILE_SIZE);
	    }
	}
	for (Tower tower: map.getTowers()) {
	    tower.draw(g2d, TILE_SIZE);
	}
	for(Enemy enemy : map.getEnemies()){
	    enemy.draw(g2d, TILE_SIZE);
	}

	g2d.setColor(Color.BLACK);
	g2d.setFont(new Font("serif", Font.PLAIN, 40));
	g2d.drawString(String.valueOf(map.getHealth()), 30, 30);
    }

    public static int getTileSize() {
	return TILE_SIZE;
    }
}
