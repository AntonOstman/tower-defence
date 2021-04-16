package se.liu.antos931jakos322.towerdefence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * Starts the game and controls what happens in the game
 *
 */

public class GameHandler
{


    public static void main(String[] args) {

    final int timerDelay = 50;
    final int enemySpawnRate = 50;
    final int waveDensity = timerDelay * enemySpawnRate; // keeps the ratio when timerDelay is changed


    Map map = new Map(10,10);
    map.hardCodedMap();
    map.hardRoad();
    MapViewer viewer = new MapViewer(map);
    map.addEnemy(new GenericEnemy(new Point(9, 9)));

    viewer.show();

    final Action doOneStep = new AbstractAction() {
        @Override public void actionPerformed(final ActionEvent e) {
            map.tick();
        }
    };

    final Timer tickTimer = new Timer(timerDelay, doOneStep);
    tickTimer.start();

    final Action createEnemy = new AbstractAction() {
        @Override public void actionPerformed(final ActionEvent e) {
            //Enemy enemy =

            map.addEnemy(new GenericEnemy(new Point(-1,-1)));
        }
    };

    final Timer enemyTimer = new Timer(waveDensity, createEnemy);
    enemyTimer.start();
    }

    public void createWave(int numberOfEnemies){

    }

}
