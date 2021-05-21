package se.liu.antos931jakos322.towerdefence.userinterface;

import se.liu.antos931jakos322.towerdefence.gamelogic.GameHandler;
import se.liu.antos931jakos322.towerdefence.gamelogic.Tile;

import javax.swing.*;
import java.awt.*;

/**
 *
 * MapComponent controls the main drawing of objects that exist on the game map
 * MapComponent draws objects by using their own drawing methods
 * That means MapComponent only controls how objects are drawn in relation to eachother but does not contain information
 * on how to draw indivudual objects.
 *
 * MapComponent also implements gameListener which can be used to let mapComponent know of changes made to the game.
 * In which case it needs act on those changes to update the UI.
 *
 */

public class GameComponent extends JComponent implements GameListener
{
    private GameHandler gameHandler;
    private final int mapX;
    private final int mapY;
    private int gameScale;
    private final static int MARGIN = 0;

    public GameComponent(GameHandler gameHandler, int gameScale) {
	this.gameHandler = gameHandler;
	this.mapY = gameHandler.getGameMap().getDimensions().y;
	this.mapX = gameHandler.getGameMap().getDimensions().x;
	this.gameScale = gameScale;
    }


    public Dimension getPreferredSize(){

	int width = mapX*(MARGIN + gameScale);
	int height = mapY*(MARGIN + gameScale);
        return new Dimension(width, height);

    }

    @Override public void gameChanged() {
	repaint();
    }

    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);

	final Graphics2D g2d = (Graphics2D) g;

	for (int y = 0; y < mapY; y++) {
	    for (int x = 0; x < mapX; x++) {
		Tile currentTile = gameHandler.getGameMap().getTile(new Point(x, y));
		currentTile.drawTile(g2d, MARGIN, gameScale);
	    }
	}

	for (int i = 0; i < gameHandler.getTowerAmount(); i++) {
	    gameHandler.getTower(i).draw(g2d, gameScale);
	}
	for(int i = 0; i < gameHandler.getEnemyAmount(); i++){
	    gameHandler.getEnemy(i).draw(g2d, gameScale);
	}
	for(int i = 0; i < gameHandler.getProjectileAmount(); i++ ){
	    gameHandler.getProjectile(i).draw(g2d, gameScale);
	}

    }

}
