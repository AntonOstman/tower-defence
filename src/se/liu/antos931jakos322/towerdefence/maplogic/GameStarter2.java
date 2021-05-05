package se.liu.antos931jakos322.towerdefence.maplogic;

import se.liu.antos931jakos322.towerdefence.userinterface.GameViewer;

import java.io.IOException;

public class GameStarter2
{
    public void startGame(int mapIndex) {



	Map map = new Map(); // move to gamehandelr?
	try {
	    map.loadMap(mapIndex);
	}
	catch (IOException e){
	    // not handeled correctly
	    e.printStackTrace();
	}

	GameHandler gameHandler = new GameHandler(map);
	GameViewer viewer = new GameViewer(gameHandler);
	gameHandler.startGame();
	viewer.show();

    }


}
