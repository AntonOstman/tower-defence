package se.liu.antos931jakos322.towerdefence;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Starts the GUI on which the player interacts with and can view the game
 *
 *
 */
public class MapViewer
{
    private Map map;
    private JFrame frame;

    //
    public MapViewer(Map map) {
        this.map = map;
        this.frame = new JFrame();

    }

    public void viewMapText(){
        int mapX = map.getDimension().x;
	int mapY = map.getDimension().y;
        for (int y = 0; y < mapY; y++) {
	    for (int x = 0; x < mapX; x++) {
		//System.out.print(map.getTile(x, y));
	    }
	    //System.out.println();
	}
}
    public void show(){
	MapComponent mapComponent = new MapComponent(map);
	//System.out.println(map);
	frame.setLayout(new BorderLayout());
	map.addListener(mapComponent);
	frame.add(mapComponent, BorderLayout.CENTER);
	frame.pack();
	frame.setVisible(true);

    }


}
