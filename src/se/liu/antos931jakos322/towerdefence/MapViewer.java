package se.liu.antos931jakos322.towerdefence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	JPanel mainPanel = new JPanel();

	JPanel gamePanel = new JPanel(new GridLayout(0,1));
	JPanel menuPanel = new JPanel(new GridLayout(4,0));

	menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	gamePanel.setBorder(BorderFactory.createLineBorder(Color.RED));
	mainPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));

	String[] towerStrings = {" ","Arrow tower", "Cannon tower", "Money tower"};

	JComboBox<String> towerDropDown = new JComboBox<>(towerStrings);
	towerDropDown.setSelectedIndex(0);

	//menupanel
	menuPanel.add(new Button("tester"));
	menuPanel.add(new Button("tester2"));
	menuPanel.add(towerDropDown);

	//gamepanel
	gamePanel.add(mapComponent);
	MouseEvent mouseListener = new MouseEvent(towerDropDown);
	gamePanel.addMouseListener(mouseListener);

	mainPanel.add(gamePanel);
	mainPanel.add(menuPanel);


	frame.setLayout(new BorderLayout());
	map.addListener(mapComponent);
	frame.add(mainPanel);

	frame.pack();
	frame.setVisible(true);



    }
    public class MouseEvent implements MouseListener{
	private JComboBox<String> dropDown;

	public MouseEvent(JComboBox<String> dropDown) {
	    this.dropDown = dropDown;
	}

	@Override public void mouseClicked(final java.awt.event.MouseEvent e) {
	    System.out.println(e);
	    //System.out.println(e.getLocationOnScreen());
	    Point clickedPoint = e.getPoint();
	    // we get the tilesize and translate from pixel coordinates to map coordinates
	    int tileSize = MapComponent.getTileSize();
	    int mapPosX = clickedPoint.x/tileSize;
	    int mapPosY = clickedPoint.y/tileSize;

	    Point blockPoint = new Point(mapPosX,mapPosY);

	    System.out.println(e.getPoint() + " Point");
	    String currentSelectedTower = (String) dropDown.getSelectedItem();

	    if (currentSelectedTower.equals("Arrow tower")){
	        map.addTower(new ArrowTower(blockPoint));
	    }
	}

	@Override public void mousePressed(final java.awt.event.MouseEvent e) {

	}

	@Override public void mouseReleased(final java.awt.event.MouseEvent e) {

	}

	@Override public void mouseEntered(final java.awt.event.MouseEvent e) {

	}

	@Override public void mouseExited(final java.awt.event.MouseEvent e) {

	}
    }

}
