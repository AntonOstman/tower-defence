package se.liu.antos931jakos322.TowerDefence;

import javax.swing.*;
import java.awt.*;

public class MapComponent extends JComponent
{
    private Map map;
    final int mapX;
    final int mapY;
    final static int TILE_SIZE = 20;
    final static int MARGIN = 0;

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
	        Color tileColor = currentTile.getTileColor();
		g2d.setColor(tileColor);
		g2d.fillRect(x * (MARGIN + TILE_SIZE), y * (MARGIN+TILE_SIZE), TILE_SIZE, TILE_SIZE);
	    }
	}
    }

}
