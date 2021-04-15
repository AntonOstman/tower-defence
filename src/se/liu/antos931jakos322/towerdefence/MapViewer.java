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
    private JComboBox towerDropDown;
    private MapComponent mapComponent;

    //
    public MapViewer(Map map) {
        this.map = map;
        this.frame = new JFrame();
	this.mapComponent = new MapComponent(map);
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
	MapComponent mapComponent1 = new MapComponent(map);
	//System.out.println(map);
	JPanel mainPanel = new JPanel();
	//mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

	JPanel gamePanel = new JPanel(new GridLayout(0,1));
	JPanel menuPanel = new JPanel(new GridLayout(4,0));
	//show borders for debuggiung
	menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	gamePanel.setBorder(BorderFactory.createLineBorder(Color.RED));
	mainPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));

	String[] towerStrings = {" ","Arrow tower", "Cannon tower", "Money tower"};

	JComboBox towerDropDown = new JComboBox(towerStrings);
	towerDropDown.setSelectedIndex(0);
	//towerDropDown.addActionListener();

	//menupanel
	menuPanel.add(new Button("tester"));
	menuPanel.add(new Button("tester2"));
	menuPanel.add(towerDropDown);
	//gamepalen
	gamePanel.add(mapComponent);
	MouseEvent mouseListener = new MouseEvent(towerDropDown);
	gamePanel.addMouseListener(mouseListener);
	//menuPanel.add(mapComponent1);

	mainPanel.add(gamePanel);
	mainPanel.add(menuPanel);


	frame.setLayout(new BorderLayout());
	map.addListener(mapComponent);
	map.addListener(mapComponent1);
	frame.add(mainPanel);

	//frame.add(mapComponent, BorderLayout.CENTER);
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
	    int tileSize = MapComponent.getTileSize();
	    Point blockPoint = new Point(clickedPoint.x/tileSize,clickedPoint.y/tileSize);

	    System.out.println(e.getPoint() + " Point");
	    String currentSelectedTower = (String) dropDown.getSelectedItem();

	    if (currentSelectedTower == "Arrow tower"){
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
