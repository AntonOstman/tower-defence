package se.liu.antos931jakos322.TowerDefence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameTester
{


    public static void main(String[] args) {


    Map map = new Map(10,10);
    map.hardCodedMap();
    map.hardRoad();
    MapViewer viewer = new MapViewer(map);
    map.addEnemy(new GenericEnemy(new Point(9, 9)));
    map.addTower(new CanonTower(new Point(4,3)));

    map.addEnemy(new GenericEnemy(new Point(1,4)));
    map.addTower(new ArrowTower(new Point(2,3)));

    //map.addEnemy(new GenericEnemy(new Point(3,3)));
    map.addTower(new ArrowTower(new Point(4,6)));

    //viewer.viewMapText();
    // hejsan
    viewer.show();

    final Action DoOneStep = new AbstractAction() {
        @Override public void actionPerformed(final ActionEvent e) {
            map.tick();
            viewer.show();
        }
    };

    final Timer tickTimer = new Timer(500,DoOneStep);
    tickTimer.start();
    }

}
