package se.liu.antos931jakos322.towerdefence.userinterface;

import se.liu.antos931jakos322.towerdefence.entities.Enemy;
import se.liu.antos931jakos322.towerdefence.entities.Projectile;
import se.liu.antos931jakos322.towerdefence.maplogic.GameHandler;
import se.liu.antos931jakos322.towerdefence.maplogic.Tile;
import se.liu.antos931jakos322.towerdefence.entities.Tower;

import javax.swing.*;
import java.awt.*;

/**
 * MapComponent draws everything in the game contained in map but does not itself have knowledge how
 * Example
 * 	Does not know how to draw a tower. But uses the towers own draw method to draw it.
 *
 *
 */

public class GameComponent extends JComponent implements GameListener
{
    private GameHandler gameHandler;
    private final int mapX;
    private final int mapY;
    private final static int TILE_SIZE = 50;
    private final static int MARGIN = 0;

    public GameComponent(GameHandler gameHandler) {
	this.gameHandler = gameHandler;
	this.mapY = gameHandler.getMap().getDimensions().y;
	this.mapX = gameHandler.getMap().getDimensions().x;
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
		Tile currentTile = gameHandler.getMap().getTile(new Point(x, y));
		currentTile.drawTile(g2d, MARGIN, TILE_SIZE);
	    }
	}

	for (int i = 0; i < gameHandler.getTowerAmount(); i++) {
	    gameHandler.getTower(i).draw(g2d, TILE_SIZE);
	}
	for(int i = 0; i < gameHandler.getEnemyAmount(); i++){
	    gameHandler.getEnemy(i).draw(g2d, TILE_SIZE);
	}
	for(int i = 0; i < gameHandler.getProjectileAmount(); i++ ){
	    gameHandler.getProjectile(i).draw(g2d, TILE_SIZE);
	}

    }

    public static int getTileSize() {
	return TILE_SIZE;
    }
}
