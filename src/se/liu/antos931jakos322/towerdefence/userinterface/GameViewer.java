package se.liu.antos931jakos322.towerdefence.userinterface;

import se.liu.antos931jakos322.towerdefence.entities.towers.Tower;
import se.liu.antos931jakos322.towerdefence.entities.towers.TowerGetter;
import se.liu.antos931jakos322.towerdefence.entities.towers.TowerType;
import se.liu.antos931jakos322.towerdefence.gamelogic.GameHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
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
    private JTextArea towerDescription;
    private Tower clickedTower;
    private int gameScale;

    /**
     * Constructs a GameViewer object with
     *
     * @param gameHandler the object that controls the game
     * @param gameScale the graphical scale of the game
     */

    public GameViewer(GameHandler gameHandler, int gameScale) {
        this.gameHandler = gameHandler;
        this.selectedTower = TowerType.NONE;
	this.frame = null;
	this.towerDescription = null;
	this.clickedTower = null;
	this.gameScale = gameScale;
    }

    /**
     * creates the buttons,
     * Also shows the frame after completion.
     *
     */

    public void createInterface(){
	final Color backGroundColor = new Color(92, 184, 92);
	MenuComponent menuComponent = new MenuComponent(gameHandler, gameScale);
	GameComponent gameComponent = new GameComponent(gameHandler, gameScale);

	gameHandler.addListener(menuComponent);
	gameHandler.addListener(gameComponent);


	// create the panels of the UI In the gridLayout the first agrument represent amout of rows and seconds amout of coloumns
	// in the gridlayout
	// 0 stands for unlimited amount
	JPanel mainPanel = new JPanel();
	JPanel gamePanel = new JPanel(new GridLayout(1,1));
	JPanel mainMenuPanel = new JPanel(new GridLayout(5,1));
	JPanel pauseAndQuitPanel = new JPanel(new GridLayout(2, 0));
	JPanel towerUpgradesPanel = new JPanel(new BorderLayout());
	JPanel towerDescriptionPanel = new JPanel(new GridLayout(1,1));
	JPanel textPanel = new JPanel(new BorderLayout());
	JScrollPane scrollableInteractivePanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JScrollPane textScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	// create the interactive panel
	final int unlimitedRows = 0;
	GridLayout interactivePanelLayout = new GridLayout(unlimitedRows, 2);
	JPanel interactivePanel = new JPanel(interactivePanelLayout);
	final int margin = 10;
	interactivePanelLayout.setHgap(margin);
	interactivePanelLayout.setVgap(margin);


	//menupanel has the information the player needs and is used for choosing towers

	// create and set the buttons for placing towers
	ButtonGroup buttonGroup = new ButtonGroup(); // create button group to deselect buttons when antoher is clicked
	List<TowerType> towerTypes = TowerGetter.getAllTowers();
	towerDescription = new JTextArea("No tower selected");

	for (TowerType towerType: towerTypes) {
	    ButtonEvent buttonEvent = new ButtonEvent(towerType, ButtonType.MENU);
	    JToggleButton b = new JToggleButton();
	    final int bufferedImageWidth = gameScale/2;
	    final int bufferedImageHeight = gameScale/2;
	    BufferedImage lineImage = new BufferedImage(bufferedImageWidth, bufferedImageHeight,
							BufferedImage.TYPE_INT_RGB);
	    Graphics2D bg2d = lineImage.createGraphics();
	    Tower tower = TowerGetter.getTower(towerType);
	    double towerOffset = (1/2.0 - tower.getSize()/2);
	    tower.setPosition(new Point2D.Double(-towerOffset,-towerOffset));
	    tower.draw(bg2d, gameScale);
	    b.addActionListener(buttonEvent);
	    b.setIcon(new ImageIcon(lineImage));
	    b.setBorderPainted(false);
	    b.setBackground(backGroundColor);
	    buttonGroup.add(b);
	    interactivePanel.add(b);
	    UIManager.put(b.isSelected(),Color.BLACK);
	}

	textPanel.add(menuComponent);


	//gamepanel has the map compopnent and shows the running game
	gamePanel.add(gameComponent);
	gamePanel.setBorder(BorderFactory.createLineBorder(Color.black));
	gamePanel.addMouseListener(new MouseEvent(towerDescription, buttonGroup));

	//tower description panel config
	textScrollPane.getViewport().add(towerDescription);
	towerDescriptionPanel.add(textScrollPane);
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
	JButton pauseButton = new JButton();
	pauseButton.setAction(new ButtonEvent(ButtonType.PAUSE, pauseButton));

	// the game starts paused so start with the startgame text
	pauseButton.setText("Start game");
	quitButton.setText("Quit game");
	pauseAndQuitPanel.add(pauseButton);
	pauseAndQuitPanel.add(quitButton);

	// now add all panels we have created to the mainMenuPanel
	mainMenuPanel.add(menuComponent);
	mainMenuPanel.add(towerDescriptionPanel);
	mainMenuPanel.add(scrollableInteractivePanel);
	mainMenuPanel.add(towerDescriptionPanel);
	mainMenuPanel.add(towerUpgradesPanel);
	mainMenuPanel.add(pauseAndQuitPanel);
	int mainMenuWidth = gameScale * 4;
	int mainMenuHeight = gameScale * 10;
	mainMenuPanel.setPreferredSize(new Dimension(mainMenuWidth, mainMenuHeight));
	mainMenuPanel.setBackground(backGroundColor);

	// add the panel controlling the game and the menus to the main panel
	mainPanel.add(gamePanel);
	mainPanel.add(mainMenuPanel);
	mainPanel.setBackground(backGroundColor);

	frame = new JFrame();
	frame.setLayout(new BorderLayout());
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
	private JButton jButton;

	/**
	 * Constructs a ButtonEvent
	 *
	 */
	public ButtonEvent(TowerType towerType, ButtonType buttonType) {
	    this.towerType = towerType;
	    this.buttonType = buttonType;
	    this.jButton = null;
	}

	/**
	 * Constructs a ButtonEvent with the type of button
	 *
	 * @param buttonType type of button
	 */

	public ButtonEvent(ButtonType buttonType) {
	    this.towerType = TowerType.NONE;
	    this.buttonType = buttonType;
	    this.jButton = null;
	}

	/**
	 * Constructs a ButtonEvent with a buttonType and a jButton object
	 *
	 * @param buttonType the type of button
	 * @param jButton the used button
	 */
	public ButtonEvent(ButtonType buttonType, JButton jButton) {
	    this.towerType = TowerType.NONE;
	    this.buttonType = buttonType;
	    this.jButton = jButton;
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
		String towerDesc = TowerGetter.getTower(towerType).getDescription();
		towerDescription.setText(towerDesc);

		break;
		case PAUSE:
	     	if (gameHandler.isGamePaused()){
		    jButton.setText("Pause game");
		    gameHandler.startGame();
		}
	     	else{
		    jButton.setText("Start game");
		    gameHandler.pauseGame();
	     	}

		    break;
		case QUIT:
		System.exit(0);
		default:
		     throw new IllegalArgumentException("button did not have valid action");
	    }

	}
    }


    public class MouseEvent extends MouseAdapter
    {
	private JTextArea textArea;
	private ButtonGroup buttonGroup;

	public MouseEvent(JTextArea textArea, ButtonGroup buttonGroup){
	    this.textArea = textArea;
	    this.buttonGroup = buttonGroup;
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
	        gameHandler.deselectTower(clickedTower);
	    }
	    Tower newClickedTower = gameHandler.getTowerNearPosition(clickedPoint);
	    // if there is no tower on the point exit
	    if (newClickedTower == null){ return;}
	    textArea.setText(newClickedTower.getDescription());
	    clickedTower = newClickedTower;
	    gameHandler.selectTower(clickedTower);
	}


	private void placeTower(Point clickedPoint){

	    Tower newTower = TowerGetter.getTower(selectedTower);
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
		// lastely deselct the tower button and set the selcted tower to nothing
		buttonGroup.clearSelection();
		selectedTower = TowerType.NONE;
		//if the player wants to place another
		// he needs to press the tower button again to select tower
	    }
	}


    }

}
