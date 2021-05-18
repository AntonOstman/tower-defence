package se.liu.antos931jakos322.towerdefence.userinterface;

import se.liu.antos931jakos322.towerdefence.entities.towers.Tower;
import se.liu.antos931jakos322.towerdefence.entities.towers.TowerMaker;
import se.liu.antos931jakos322.towerdefence.entities.towers.TowerType;
import se.liu.antos931jakos322.towerdefence.gamelogic.GameHandler;

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
    private int gameScale;

    public GameViewer(GameHandler gameHandler, int gameScale) {
        this.gameHandler = gameHandler;
        this.selectedTower = TowerType.NONE;
	this.frame = null;
	this.towerDescription = null;
	this.buttonGroup = null;
	this.clickedTower = null;
	this.gameScale = gameScale;
    }

    public void show(){

        frame = new JFrame();
	MenuComponent menuComponent = new MenuComponent(gameHandler);
	GameComponent gameComponent = new GameComponent(gameHandler, gameScale);

	// create the panels of the UI In the gridLayout the first agrument represent amout of rows and seconds amout of coloumns
	// in the gridlayout
	JPanel mainPanel = new JPanel();
	JPanel gamePanel = new JPanel(new GridLayout(1,1));
	JPanel mainMenuPanel = new JPanel(new GridLayout(5,1));
	JPanel pauseAndQuitPanel = new JPanel(new GridLayout(2, 0));
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
	List<TowerType> towerTypes = TowerMaker.getAllTowers();
	towerDescription = new JTextArea("No tower selected");

	// iterate over all towerstypes that exist and get the information about certain towers from towermaker
	// and create buttons with that information
	for (TowerType towerType: towerTypes) {
	    ButtonEvent buttonEvent = new ButtonEvent(towerType, "button clicked");
	    Color buttonColor = TowerMaker.getTower(towerType).getColor();
	    JToggleButton b = new JToggleButton();
	    b.setBackground(buttonColor);
	    b.addActionListener(buttonEvent);
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
	JButton upgradeButton = new JButton(new ButtonEvent("upgrade"));
	upgradeButton.setText("Upgrade selected tower");
	towerUpgradesPanel.add(upgradeButton);

	// pause and quit buttons
	JButton quitButton = new JButton(new ButtonEvent("quit game"));
	JButton pauseButton = new JButton(new ButtonEvent("pause game"));
	quitButton.setText("Quit game");
	pauseButton.setText("Pause game");
	pauseAndQuitPanel.add(quitButton);
	pauseAndQuitPanel.add(pauseButton);

	// now add all panels we have created to the main
	mainMenuPanel.add(menuComponent);
	mainMenuPanel.add(towerDescriptionPanel);
	mainMenuPanel.add(scrollableInteractivePanel);
	mainMenuPanel.add(towerDescriptionPanel);
	mainMenuPanel.add(towerUpgradesPanel);
	mainMenuPanel.add(pauseAndQuitPanel);
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

    public void dispose() {
        frame.dispose();
    }

    public class ButtonEvent extends AbstractAction{

        private TowerType towerType;
	private String action;


	public ButtonEvent(TowerType towerType, String action) {
	    this.towerType = towerType;
	    this.action = action;
	}
	public ButtonEvent(String action) {
	    this.towerType = TowerType.NONE;
	    this.action = action;
	}

	@Override public void actionPerformed(final ActionEvent e) {
	    switch (action){
		case("upgrade"):
	        // player is trying to upgrade a tower
	        if(clickedTower != null) {
		    if (gameHandler.isTowerUpgradable(clickedTower)) {
			gameHandler.upgradeTower(clickedTower);
			towerDescription.setText(clickedTower.getDescription());
		    }

		}
		    break;
		case("button clicked"):
	        // player is trying to press a tower on the menu

		selectedTower = towerType;

		String towerDesc = TowerMaker.getTower(towerType).getDescription();
		towerDescription.setText(towerDesc);

		break;
		case("pause game"):

	     	if (gameHandler.isGamePaused()){
	     	    gameHandler.startGame();
		}
	     	else{
	        gameHandler.pauseGame();
	     	}

		    break;
		case("quit game"):
		System.exit(0);
		default:
		     throw new IllegalArgumentException("button did not have valid action string");
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
	    // we get the gameScale and translate from pixel coordinates to map coordinates
	    int mapPosX = clickedPoint.x/gameScale;
	    int mapPosY = clickedPoint.y/gameScale;

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
	    // if there is no tower on the point exit
	    if (clickedTow == null){ return;}
	    textArea.setText(clickedTow.getDescription());
	    clickedTower = clickedTow;

	}


	private void placeTower(Point clickedPoint){

	    Tower newTower = TowerMaker.getTower(selectedTower);
	    newTower.setPosition(clickedPoint);
	    boolean canPlaceTower = gameHandler.canAffordAndPlaceTower(newTower);

	    // check if the game allows placing the selected tower
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


    }

}
