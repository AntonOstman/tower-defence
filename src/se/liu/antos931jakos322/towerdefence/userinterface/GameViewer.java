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
 * GameViewer controls the main user interface of the game.
 * GameViewer takes input from the user in the form of buttons presses and clicks on its panels
 * GameViewer can therefore for example decide where game objects should be placed by calculating the pixel to gamescale relation when
 * the player clicks on the game panel
 *
 * GameViewer contains the menus, the panels containg graphical components, and buttons
 * GameViewer also facilitates the game by interacting with GameHandler by for example
 * telling it where the player is trying to place objects
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


	//menupanel has the information the player needs and is used for choosing towers

	// create and set the buttons for placing towers
	buttonGroup = new ButtonGroup(); // create button group to deselect buttons when antoher is clicked
	List<TowerType> towerTypes = TowerMaker.getAllTowers();
	towerDescription = new JTextArea("No tower selected");

	// iterate over all towerstypes that exist and get the information about certain towers from towermaker
	// and create buttons with that information
	for (TowerType towerType: towerTypes) {
	    ButtonEvent buttonEvent = new ButtonEvent(towerType, ButtonType.MENU);
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
	JButton upgradeButton = new JButton(new ButtonEvent(ButtonType.UPGRADE));
	upgradeButton.setText("Upgrade selected tower");
	towerUpgradesPanel.add(upgradeButton);

	// pause and quit buttons
	JButton quitButton = new JButton(new ButtonEvent(ButtonType.QUIT));
	JButton pauseButton = new JButton(new ButtonEvent(ButtonType.PAUSE));
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
	mainMenuPanel.setPreferredSize(new Dimension(gameScale*4,gameScale*10));
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
	private ButtonType buttonType;


	public ButtonEvent(TowerType towerType, ButtonType buttonType) {
	    this.towerType = towerType;
	    this.buttonType = buttonType;
	}
	public ButtonEvent(ButtonType buttonType) {
	    this.towerType = TowerType.NONE;
	    this.buttonType = buttonType;
	}

	@Override public void actionPerformed(final ActionEvent e) {
	    switch (buttonType){
		case UPGRADE:
	        // player is trying to upgrade a tower
	        if(clickedTower != null) {
		    if (gameHandler.isTowerUpgradable(clickedTower)) {
			gameHandler.upgradeTower(clickedTower);
			towerDescription.setText(clickedTower.getDescription());
		    }

		}
		    break;
		case MENU:
	        // player is trying to press a tower on the menu

		selectedTower = towerType;

		String towerDesc = TowerMaker.getTower(towerType).getDescription();
		towerDescription.setText(towerDesc);

		break;
		case PAUSE:

	     	if (gameHandler.isGamePaused()){
	     	    gameHandler.startGame();
		}
	     	else{
	        gameHandler.pauseGame();
	     	}

		    break;
		case QUIT:
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

	    if (clickedTower != null){
	        clickedTower.setSelected(false);
	    }
	    Tower clickedTow = gameHandler.getTowerOnPoint(clickedPoint);
	    // if there is no tower on the point exit
	    if (clickedTow == null){ return;}
	    textArea.setText(clickedTow.getDescription());
	    clickedTower = clickedTow;
	    clickedTower.setSelected(true);
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
