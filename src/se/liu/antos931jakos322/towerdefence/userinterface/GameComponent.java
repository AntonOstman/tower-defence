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
    private final int mapWidth;
    private final int mapHeight;
    private int gameScale;
    private final static int MARGIN = 0;

    public GameComponent(GameHandler gameHandler, int gameScale) {
	this.gameHandler = gameHandler;
	Point mapDimensions = gameHandler.getMapDimensions();
	this.mapHeight = mapDimensions.y;
	this.mapWidth = mapDimensions.x;
	this.gameScale = gameScale;
    }


    public Dimension getPreferredSize(){

	int gameWidth = mapWidth * (MARGIN + gameScale);
	int gameHeight = mapHeight * (MARGIN + gameScale);
        return new Dimension(gameWidth, gameHeight);

    }

    @Override public void gameChanged() {
	repaint();
    }

    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);

	final Graphics2D g2d = (Graphics2D) g;

	for (int y = 0; y < mapHeight; y++) {
	    for (int x = 0; x < mapWidth; x++) {
		Tile currentTile = gameHandler.getMapTile(new Point(x, y));
		currentTile.drawTile(g2d, MARGIN, gameScale);
	    }
	}

	for(int i = 0; i < gameHandler.getProjectileAmount(); i++ ){
	    gameHandler.getProjectile(i).draw(g2d, gameScale);
	}
	for(int i = 0; i < gameHandler.getEnemyAmount(); i++){
	    gameHandler.getEnemy(i).draw(g2d, gameScale);
	}
	for (int i = 0; i < gameHandler.getTowerAmount(); i++) {
	    gameHandler.getTower(i).draw(g2d, gameScale);
	}

    }

}
