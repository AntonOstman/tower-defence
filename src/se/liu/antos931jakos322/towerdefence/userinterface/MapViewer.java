package se.liu.antos931jakos322.towerdefence.userinterface;

import se.liu.antos931jakos322.towerdefence.entities.Tower;
import se.liu.antos931jakos322.towerdefence.entities.TowerMaker;
import se.liu.antos931jakos322.towerdefence.entities.TowerType;
import se.liu.antos931jakos322.towerdefence.maplogic.GameHandler;
import se.liu.antos931jakos322.towerdefence.maplogic.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 *
 * Starts the GUI on which the player interacts with and can view the game
 * Controls the frame and panels contained in the frame
 *
 */
public class MapViewer
{
    private GameHandler gameHandler;
    private JFrame frame;

    //
    public MapViewer(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.frame = new JFrame();
    }

    public void viewMapText(){
        int mapX = gameHandler.getMap().getDimension().x;
	int mapY = gameHandler.getMap().getDimension().y;
        for (int y = 0; y < mapY; y++) {
	    for (int x = 0; x < mapX; x++) {
		//System.out.print(map.getTile(x, y));
	    }
	    //System.out.println();
	}
}
    public void show(){
        se.liu.antos931jakos322.towerdefence.userinterface.MenuComponent menuComponent = new MenuComponent(gameHandler);
	MapComponent mapComponent = new MapComponent(gameHandler);

	JPanel mainPanel = new JPanel();
	JPanel gamePanel = new JPanel(new GridLayout(0,1));
	JPanel mainMenuPanel = new JPanel(new GridLayout(2,0));
	JPanel interactivePanel = new JPanel(new GridLayout(4, 0));
	JPanel textPanel = new JPanel(new BorderLayout());

	interactivePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	gamePanel.setBorder(BorderFactory.createLineBorder(Color.RED));
	mainPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	mainMenuPanel.setBorder(BorderFactory.createLineBorder(Color.cyan));
	textPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

	TowerType[] towerTypes = {TowerType.NONE, TowerType.ARROW,TowerType.CANON, TowerType.MONEY};

	JComboBox<TowerType> towerDropDown = new JComboBox<>(towerTypes);
	towerDropDown.addItem(TowerType.ARROW);
	towerDropDown.setSelectedIndex(0);

	//menupanel has the information the player needs and is used for choosing towers
	interactivePanel.add(new Button("tester"));
	interactivePanel.add(new Button("tester2"));

	interactivePanel.add(towerDropDown);
	textPanel.add(menuComponent);

	//gamepanel has the map compopnent and shows the running game
	gamePanel.add(mapComponent);
	MouseEvent mouseListener = new MouseEvent(towerDropDown);
	gamePanel.addMouseListener(mouseListener);


	mainMenuPanel.add(interactivePanel);
	mainMenuPanel.add(menuComponent);
	mainPanel.add(gamePanel);
	mainPanel.add(mainMenuPanel);


	frame.setLayout(new BorderLayout());
	gameHandler.addListener(menuComponent);
	gameHandler.addListener(mapComponent);
	frame.add(mainPanel);

	frame.pack();
	frame.setVisible(true);



    }
    public class MouseEvent implements MouseListener{
	private JComboBox<TowerType> dropDown;

	public MouseEvent(JComboBox<TowerType> dropDown) {
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

	    Point mapPoint = new Point(mapPosX, mapPosY);

	    System.out.println(e.getPoint() + " Point");
	    TowerType currentSelectedTower = (TowerType) dropDown.getSelectedItem();

	    TowerMaker towerMaker = new TowerMaker();
	    Tower newTower = towerMaker.getTower(currentSelectedTower);

	    newTower.setPosition(mapPoint);

	    boolean canPlaceTower = gameHandler.canAffordAndPlaceTower(newTower);

	    if (!canPlaceTower){
		JOptionPane.showMessageDialog(frame,"Not enough Money or wrong placement");
	    }
	    else{
	           gameHandler.addTower(newTower);
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
