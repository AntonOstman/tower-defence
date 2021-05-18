package se.liu.antos931jakos322.towerdefence.userinterface;

import se.liu.antos931jakos322.towerdefence.gamelogic.GameHandler;
import se.liu.antos931jakos322.towerdefence.gamelogic.GameMap;
import se.liu.antos931jakos322.towerdefence.gamelogic.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

public class StartMenu implements GameListener
{
    private JFrame frame;
    private GameHandler gameHandler;
    private GameViewer viewer;
    private final static int TILE_SIZE = 20;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    GameMap gameMap = new GameMap();



    public StartMenu() {
	this.frame = null;
	this.gameHandler = null;
	this.viewer = null;
    }

    public void createStartMenu(){

        startLogger();
        Color background = new Color(100,100,100);


	JPanel mainPanel = new JPanel(new GridLayout(2, 1));
	//mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

	JPanel header = new JPanel();
	//header.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	//JPanel empty = new JPanel();
	//empty.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
	JPanel mapSelect = new JPanel(new GridLayout(2, 4));
	//mapSelect.setBorder(BorderFactory.createLineBorder(Color.BLUE));

	JLabel label = new JLabel("Click a map to play");
	label.setSize(new Dimension(200, 200));

	label.setFont(new Font("TimesRoman", Font.PLAIN, 50));
	label.setForeground(Color.WHITE);
	header.setBackground(background);
	header.add(label);

	//GameMap gameMap = new GameMap();
	readNewMap(gameMap);
	for (int j = 0; j < 2; j++) {
	    for (int i = 0; i < 3; i++) {
	        ButtonEvent buttonListener = new ButtonEvent(i);
	        gameMap.loadMap(i);
		JButton b = new JButton();
		final int bufferedImageWidth = 105;
		final int bufferedImageHeight = 105;
		BufferedImage lineImage = new BufferedImage(bufferedImageWidth, bufferedImageHeight,
							    BufferedImage.TYPE_INT_RGB);

		Graphics2D bg2d = lineImage.createGraphics();

		int mapY = gameMap.getHeight();
		int mapX = gameMap.getWidth();
		for (int y = 0; y < mapY; y++) {
		    for (int x = 0; x < mapX; x++) {
			Tile currentTile = gameMap.getTile(new Point(x, y));
			currentTile.drawTile(bg2d, 0, 7);
		    }
		}
		b.addActionListener(buttonListener);
		b.setIcon(new ImageIcon(lineImage));
		b.setSize(new Dimension(105, 105));
		b.setPreferredSize(new Dimension(105, 105));
		b.setBorderPainted(false);
		b.setBackground(background);
		//b.setVisible(false);
		mapSelect.add(b);
	    }
	}


	mainPanel.add(header);
	mainPanel.add(mapSelect);


	frame = new JFrame();
	frame.setPreferredSize(new Dimension(700, 500));

	frame.setLayout(new BorderLayout());
	frame.add(mainPanel);
	frame.pack();
	frame.setVisible(true);
    }

    public class ButtonEvent extends AbstractAction{

	private int index;
	public ButtonEvent(int i) {
	    this.index = i;
	}

	@Override public void actionPerformed(final ActionEvent e) {
	    System.out.println(index);

	    startGame(index);

	    frame.setVisible(false);
	}
    }

    public void startLogger() {

	FileHandler fileTxt = null;
        try {
	    fileTxt = new FileHandler("Logging.txt");
	}
	catch (IOException e){
	    String loadErrorMessage = "error starting logger";
	    int answer = JOptionPane.showConfirmDialog(null, loadErrorMessage);
	    if (answer == JOptionPane.YES_OPTION){
	        startLogger();
	        return;
	    }
	    else {
	        System.exit(0);
	    }
        }
        SimpleFormatter formatterTxt = new SimpleFormatter();
	//XMLFormatter formatterTxt = new XMLFormatter();
	fileTxt.setFormatter(formatterTxt);
	LOGGER.setLevel(Level.FINE);
	LOGGER.addHandler(fileTxt);
    }

    public void readNewMap(GameMap gameMap){


	try {
	    gameMap.readMap();
	    LOGGER.fine("succesfully loaded maps file");

	} catch (IOException e) {

	    LOGGER.log(Level.WARNING, " could not find or read maps.json file, attempting to create a new one" , e);
		try {
		    gameMap.createMap();
		    LOGGER.fine("succesfully created new maps file");
		    readNewMap(gameMap);

		} catch (IOException ioException) {
		    LOGGER.log(Level.WARNING,  " could not create maps.json file, asking user to try again", e);

		    //ioException.printStackTrace();
		    String createErrorMesage = ioException + "\n Error creating game map. \n Do you want to try again?";
		    int createAnswer = JOptionPane.showConfirmDialog(null, createErrorMesage);
		    if (createAnswer == JOptionPane.YES_OPTION){
			readNewMap(gameMap);
		    }
		    else {System.exit(1);}

	    }



	}


    }

    public void startGame(int mapIndex) {

	//GameMap gameMap = new GameMap(); // move to gamehandelr?
	gameMap.loadMap(mapIndex);


	gameHandler = new GameHandler(gameMap, TILE_SIZE);
	viewer = new GameViewer(gameHandler, TILE_SIZE);
	gameHandler.addListener(this);
	gameHandler.startGame();
	viewer.show();

    }

    @Override public void mapChanged() {
	boolean gameOver = gameHandler.isGameOver();
	if (gameOver) {
	    int option = JOptionPane.showOptionDialog(null, "Do you want to play again?", "Choose",
						      JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
						      null, null, null);
	    if (option == JOptionPane.YES_OPTION) {
	        gameHandler = null;
	        viewer.dispose();
	        viewer = null;
	        frame.setVisible(true);


		// do something
		// the best is probably to have the logic for game over in gameviewer
		// though we need a tick function in gameViwer which chechs if the game is over
		// or something
	    }
	else {
	    System.exit(0);
	    }

	}


    }
}
