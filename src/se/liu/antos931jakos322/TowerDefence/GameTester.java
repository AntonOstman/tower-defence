package se.liu.antos931jakos322.TowerDefence;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameTester
{


    public static void main(String[] args) {


    Map map = new Map(10,10);
    map.hardCodedMap();
    map.hardRoad();
    MapViewer viewer = new MapViewer(map);

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
