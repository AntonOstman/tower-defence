package se.liu.antos931jakos322.towerdefence.maplogic;

import se.liu.antos931jakos322.towerdefence.userinterface.MapViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class GameStarter
{
    public static void main(String[] args) {

	final int timerDelay = 50;
	final int enemySpawnRate = 50;
	final int waveDensity = timerDelay * enemySpawnRate; // keeps the ratio when timerDelay is changed

	Map map = new Map(10, 10);
	try {
	    map.loadMap();
	}
	catch (IOException e){
	    e.printStackTrace();
	}


	GameHandler gameHandler = new GameHandler(map);
	MapViewer viewer = new MapViewer(gameHandler);

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
