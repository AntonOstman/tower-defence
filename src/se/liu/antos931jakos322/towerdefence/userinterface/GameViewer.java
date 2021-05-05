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
public class GameViewer
{
    private GameHandler gameHandler;
    private JFrame frame;
    private TowerType selectedTower;
    private ButtonGroup buttonGroup;
    private JTextArea towerDescription;
    private Tower clickedTower;

    public GameViewer(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.selectedTower = null;
	this.frame = null;
	this.towerDescription = null;
	this.buttonGroup = null;
	this.clickedTower = null;
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
	MenuComponent menuComponent = new MenuComponent(gameHandler);
	GameComponent gameComponent = new GameComponent(gameHandler);

	// create the panels of the UI
	JPanel mainPanel = new JPanel();
	JPanel gamePanel = new JPanel(new GridLayout(1,1));
	JPanel mainMenuPanel = new JPanel(new GridLayout(4,1));
	JPanel towerUpgradesPanel = new JPanel(new BorderLayout());
	JPanel towerDescriptionPanel = new JPanel(new GridLayout(1,1));
	JPanel textPanel = new JPanel(new BorderLayout());
	JScrollPane scrollableInteractivePanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	// create the interactive panel
	final int unlimitedRows = 0;
	GridLayout interactivePanelLayout = new GridLayout(unlimitedRows, 2);
	JPanel interactivePanel = new JPanel(interactivePanelLayout);
	final int margin = 10;
	interactivePanelLayout.setHgap(margin);
	interactivePanelLayout.setVgap(margin);


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

	// create and set the buttons for placing towers
	buttonGroup = new ButtonGroup(); // create button group to deselect buttons when antoher is clicked
	TowerMaker towerMaker = new TowerMaker();
	List<TowerType> towerList = towerMaker.getAllTowers();
	towerDescription = new JTextArea("non selected");

	// iterate over all towerstypes that exist and get the information about certain towers from towermaker
	// and create buttons with that information
	for (TowerType towerType: towerList) {
	    ButtonEvent buttonLiserner = new ButtonEvent(towerType,"button clicked");
	    Color buttonColor = towerMaker.getTower(towerType).getColor();
	    JToggleButton b = new JToggleButton();
	    b.setBackground(buttonColor);
	    b.addActionListener(buttonLiserner);
	    buttonGroup.add(b);
	    interactivePanel.add(b);
	    UIManager.put(b.isSelected(),Color.BLACK);
	}


	textPanel.add(menuComponent);
	final Color backGroundColor = new Color(92, 184, 92);

	//gamepanel has the map compopnent and shows the running game
	gamePanel.add(gameComponent);
	gamePanel.setBorder(BorderFactory.createLineBorder(Color.black));
	gamePanel.addMouseListener(new MouseEvent(towerDescription));

	//tower description panel config
	towerDescriptionPanel.add(towerDescription);
	towerDescriptionPanel.setBackground(backGroundColor);
	//towerDesripction.setBorder(BorderFactory.createLineBorder(Color.black));

	// interactive panel configuareuon
	interactivePanel.setBackground(backGroundColor);
	scrollableInteractivePanel.getViewport().add(interactivePanel);
	// upgrade panel configuratoin
	towerUpgradesPanel.setBackground(Color.blue);
	JButton upgradeButton = new JButton(new ButtonEvent(TowerType.NONE, "upgrade"));
	upgradeButton.setText("Upgrade selected tower");
	towerUpgradesPanel.add(upgradeButton);

	// now add all panels we have created to the main
	mainMenuPanel.add(menuComponent);
	mainMenuPanel.add(towerDescriptionPanel);
	mainMenuPanel.add(scrollableInteractivePanel);
	mainMenuPanel.add(towerDescriptionPanel);
	mainMenuPanel.add(towerUpgradesPanel);
	mainMenuPanel.setPreferredSize(new Dimension(200,500));
	mainMenuPanel.setBackground(backGroundColor);


	mainPanel.add(gamePanel);
	mainPanel.add(mainMenuPanel);
	mainPanel.setBackground(backGroundColor);

	frame.setLayout(new BorderLayout());
	gameHandler.addListener(menuComponent);
	gameHandler.addListener(gameComponent);
	frame.add(mainPanel);

	frame.pack();
	frame.setVisible(true);

    }

    public class ButtonEvent extends AbstractAction{

        private TowerType towerType;
	private String action;

	public ButtonEvent(TowerType towerType, String action) {
	    this.towerType = towerType;
	    this.action = action;
	}


	@Override public void actionPerformed(final ActionEvent e) {
	    if (action.equals("upgrade")){
	        // player is trying to upgrade a tower
	        if(clickedTower != null) {
		    if (gameHandler.isTowerUpgradable(clickedTower)) {
			gameHandler.upgradeTower(clickedTower);
			towerDescription.setText(clickedTower.getDescription());
		    }

		}
	    }
	    else if (action.equals("button clicked")) {
	        // player is trying to press a tower on the menu
		selectedTower = towerType;
		TowerMaker towerMaker = new TowerMaker();

		String towerDesc = towerMaker.getTower(towerType).getDescription();
		towerDescription.setText(towerDesc);
	    }
	    else if (action.equals("pause game")){
	        gameHandler.pause();
	    }

	}
    }

    public class MouseEvent extends MouseAdapter
    {
	private JTextArea textArea;
	public MouseEvent(JTextArea textArea) {
	    this.textArea = textArea;
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
	    // if no tower is selected the player is trying to get info from a tower placed on the map
	    if (selectedTower == TowerType.NONE) {
		displayTowerInfo(mapPoint);
		return;
	    }
	    // if a tower is selected then the player is trying to place a tower on the map
	    placeTower(mapPoint);

	}
	private void displayTowerInfo(Point clickedPoint){

	    Tower clickedTow = gameHandler.getTowerOnPoint(clickedPoint);

	    textArea.setText(clickedTow.getDescription());
	    clickedTower = clickedTow;

	}

	private void placeTower(Point clickedPoint){

	    TowerMaker towerMaker = new TowerMaker();
	    // if no tower is selcted then we cannot place tower
	    // then check if the game allows placing the tower on the requested spot
	    Tower newTower = towerMaker.getTower(selectedTower);
	    newTower.setPosition(clickedPoint);
	    boolean canPlaceTower = gameHandler.canAffordAndPlaceTower(newTower);

	    // if selcted tower is none we cannot place tower
	    // after check if we can place tower
	    if (!canPlaceTower){
		// if not say tell the player he has done something incorrect
		JOptionPane.showMessageDialog(frame,"Not enough money, incorrect placement or no tower selected");
	    }

	    else{
		// otherwise place the tower for where the player has requested
		gameHandler.addTower(newTower);
		// lastely deselct the tower button and set the selcted tower to nothing,
		buttonGroup.clearSelection();
		selectedTower = TowerType.NONE;
		//if the player wants to place another
		// he needs to press the tower button again to select tower
	    }
	}
    	private void upgradeTower(Point clickedPoint){
	    Tower tower = gameHandler.getTowerOnPoint(clickedPoint);
	    gameHandler.upgradeTower(tower);
	}

    }

}
