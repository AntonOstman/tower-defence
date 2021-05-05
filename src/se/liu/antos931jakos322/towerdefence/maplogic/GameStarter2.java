package se.liu.antos931jakos322.towerdefence.maplogic;

import se.liu.antos931jakos322.towerdefence.userinterface.GameViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class GameStarter2
{
    public void startGame(int mapIndex) {

	final int timerDelay = 30;
	final int enemySpawnRate = 50;
	final int waveDensity = timerDelay * enemySpawnRate; // keeps the ratio when timerDelay is changed

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

	viewer.show();



	final Action doOneStep = new AbstractAction() {
	    @Override public void actionPerformed(final ActionEvent e) {
		gameHandler.tick();
	    }
	};

	final Timer tickTimer = new Timer(timerDelay, doOneStep);
	tickTimer.start();

	final Action createEnemy = new AbstractAction() {
	    @Override public void actionPerformed(final ActionEvent e) {
		//Enemy enemy =

		//map.addEnemy(new GenericEnemy(new Point(-1,-1)));
	    }
	};

	final Timer enemyTimer = new Timer(waveDensity, createEnemy);
	enemyTimer.start();
    }


}
