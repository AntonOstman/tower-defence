package se.liu.antos931jakos322.towerdefence.userinterface;

import se.liu.antos931jakos322.towerdefence.entities.Tower;
import se.liu.antos931jakos322.towerdefence.entities.TowerMaker;
import se.liu.antos931jakos322.towerdefence.entities.TowerType;
import se.liu.antos931jakos322.towerdefence.maplogic.GameHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.util.List;
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
    private TowerType selectedTower;

    public MapViewer(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.selectedTower = null;
	this.frame = null;

    }

    public void viewMapText(){
        int mapX = gameHandler.getMap().getDimensions().x;
	int mapY = gameHandler.getMap().getDimensions().y;
        for (int y = 0; y < mapY; y++) {
	    for (int x = 0; x < mapX; x++) {
		//System.out.print(map.getTile(x, y));
	    }
	    //System.out.println();
	}
}
    public void show(){

        frame = new JFrame();
	se.liu.antos931jakos322.towerdefence.userinterface.MenuComponent menuComponent = new MenuComponent(gameHandler);
	GameComponent mapComponent = new GameComponent(gameHandler);

	// create the panels of the UI
	JPanel mainPanel = new JPanel();
	JPanel gamePanel = new JPanel(new GridLayout(0,1));
	JPanel mainMenuPanel = new JPanel(new GridLayout(3,0));

	GridLayout interPanelLayout = new GridLayout(2, 4);
	interPanelLayout.setHgap(10);
	interPanelLayout.setVgap(10);
	JPanel interactivePanel = new JPanel(interPanelLayout);
	JPanel towerDescriptionPanel = new JPanel(new BorderLayout());
	JPanel textPanel = new JPanel(new BorderLayout());

	/*
	// set borders on all panels for easier debugging
	interactivePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	gamePanel.setBorder(BorderFactory.createLineBorder(Color.RED));
	mainPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	mainMenuPanel.setBorder(BorderFactory.createLineBorder(Color.cyan));
	textPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
	*/
	// should be changed to be more generic, take all types from towermaker?


	//menupanel has the information the player needs and is used for choosing towers


	ButtonGroup buttonGroup = new ButtonGroup();

	TowerMaker towerMaker = new TowerMaker();
	List<TowerType> towerList = towerMaker.getAllTowers();
	JLabel towerDesripction = new JLabel("non selected");

	for (TowerType towerType: towerList) {

	    ButtonEvent buttonLiserner = new ButtonEvent(towerType, towerDesripction);

	    Color buttonColor = towerMaker.getTower(towerType).getColor();
	    JToggleButton b = new JToggleButton();
	    b.setPreferredSize(new Dimension(50,50));
	    b.setBackground(buttonColor);
	    b.addActionListener(buttonLiserner);
	    buttonGroup.add(b);
	    interactivePanel.add(b);
	    UIManager.put(b.isSelected(),Color.BLACK);
	}


	textPanel.add(menuComponent);
	final Color backGroundColor = new Color(92, 184, 92);

	//gamepanel has the map compopnent and shows the running game
	gamePanel.add(mapComponent);
	gamePanel.setBorder(BorderFactory.createLineBorder(Color.black));

	//tower description panel config
	towerDescriptionPanel.add(towerDesripction);
	towerDescriptionPanel.setBackground(backGroundColor);
	//towerDesripction.setBorder(BorderFactory.createLineBorder(Color.black));

	// interactive panel configuareuon
	interactivePanel.setBackground(backGroundColor);

	// now add all panels we have created to the main
	mainMenuPanel.add(menuComponent);
	mainMenuPanel.add(towerDescriptionPanel);
	mainMenuPanel.add(interactivePanel);
	mainMenuPanel.setPreferredSize(new Dimension(200,500));
	mainMenuPanel.setBackground(backGroundColor);


	mainPanel.add(gamePanel);
	mainPanel.add(mainMenuPanel);
	mainPanel.setBackground(backGroundColor);

	frame.setLayout(new BorderLayout());
	gameHandler.addListener(menuComponent);
	gameHandler.addListener(mapComponent);
	frame.add(mainPanel);

	frame.pack();
	frame.setVisible(true);



    }

    public class ButtonEvent extends AbstractAction{

        private TowerType towerType;
	private JLabel towerDescriptionLabel;
	public ButtonEvent(TowerType towerType, JLabel towerDescriptionLabel) {
	    this.towerType = towerType;
	    this.towerDescriptionLabel = towerDescriptionLabel;
	}


	@Override public void actionPerformed(final ActionEvent e) {
	    selectedTower = towerType;
	    TowerMaker towerMaker = new TowerMaker();
	    String towerDesc = towerMaker.getTower(towerType).getDescription();

	    towerDescriptionLabel.setText(towerDesc);
	}
    }

    public class MouseEvent extends MouseAdapter
    {
	private JComboBox<TowerType> dropDown;

	public MouseEvent(JComboBox<TowerType> dropDown) {
	    this.dropDown = dropDown;
	}

	@Override public void mouseClicked(final java.awt.event.MouseEvent e) {
	    System.out.println(e);
	    //System.out.println(e.getLocationOnScreen());
	    Point clickedPoint = e.getPoint();
	    // we get the tilesize and translate from pixel coordinates to map coordinates
	    int tileSize = GameComponent.getTileSize();
	    int mapPosX = clickedPoint.x/tileSize;
	    int mapPosY = clickedPoint.y/tileSize;

	    Point mapPoint = new Point(mapPosX, mapPosY);

	    System.out.println(e.getPoint() + " Point");

	    TowerMaker towerMaker = new TowerMaker();
	    Tower newTower = towerMaker.getTower(selectedTower);


	    newTower.setPosition(mapPoint);

	    boolean canPlaceTower = gameHandler.canAffordAndPlaceTower(newTower);

	    if (!canPlaceTower){
		JOptionPane.showMessageDialog(frame,"Not enough Money or wrong placement");
	    }
	    else{
	           gameHandler.addTower(newTower);
	    }
	}

    }

}
