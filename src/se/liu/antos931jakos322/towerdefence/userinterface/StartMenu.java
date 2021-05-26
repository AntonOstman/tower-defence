package se.liu.antos931jakos322.towerdefence.userinterface;

import com.google.gson.JsonSyntaxException;
import se.liu.antos931jakos322.towerdefence.gamelogic.GameHandler;
import se.liu.antos931jakos322.towerdefence.gamelogic.GameMap;
import se.liu.antos931jakos322.towerdefence.gamelogic.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * StartMenu opens a window where the user can click on on button
 * to choose which map to play on, the class then starts a game
 * on the choosen map.
 */

public class StartMenu implements GameListener
{
    // ---------- in response the the inspection about similar children ------
    //------- While GameComponent and menuComponent could have an abstract class in this instance it feels redundat -------
    // ------ it will be one class with gameHandler and the method gamechanged. ------
    // ------ in the future if adding more componentes who uses this code it would make sense more sense. ------
    // ----- Otherwise it is just bloating the project with classes -------
    // -----also because Startmenu who uses the gamelistner interface implements its own gameChanged and does not extend JComponent -----
    private JFrame frame;
    private GameHandler gameHandler;
    private GameViewer viewer;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private GameMap gameMap;


    public StartMenu() {
        this.gameMap =  new GameMap();
	this.frame = null;
	this.gameHandler = null;
	this.viewer = null;
    }

    public void createStartMenu(){

        readNewMap(gameMap);

	Color background = new Color(100,100,100);

	JPanel mainPanel = new JPanel(new GridLayout(2, 1));
	JPanel header = new JPanel();
	JPanel mapSelect = new JPanel(new GridLayout(2, 4));

	JLabel label = new JLabel("Select a map to play");
	label.setSize(new Dimension(200, 200));

	label.setFont(new Font("TimesRoman", Font.PLAIN, 50));
	label.setForeground(Color.WHITE);
	header.setBackground(background);
	header.add(label);


	for (int i = 0; i < gameMap.getNumberOfMaps(); i++) {
	    ButtonEvent buttonListener = new ButtonEvent(i);
	    gameMap.loadMap(i);
	    JButton b = new JButton();

	    final int bufferedImageWidth = 105;
	    final int bufferedImageHeight = 105;
	    BufferedImage lineImage = new BufferedImage(bufferedImageWidth, bufferedImageHeight,
							BufferedImage.TYPE_INT_RGB);

	    Graphics2D bg2d = lineImage.createGraphics();

	    int mapY = gameMap.getDimension().x;
	    int mapX = gameMap.getDimension().y;
	    final int tileSize = 7;
	    final int margin = 0;
	    for (int y = 0; y < mapY; y++) {
		for (int x = 0; x < mapX; x++) {
		    Tile currentTile = gameMap.getTile(new Point(x, y));
		    currentTile.draw(bg2d, margin, tileSize);
		}
	    }
	    b.addActionListener(buttonListener);
	    b.setIcon(new ImageIcon(lineImage));
	    b.setSize(new Dimension(105, 105));
	    b.setPreferredSize(new Dimension(105, 105));
	    b.setBorderPainted(false);
	    b.setBackground(background);
	    mapSelect.add(b);
	}



	mainPanel.add(header);
	mainPanel.add(mapSelect);
	mapSelect.setBackground(background);


	frame = new JFrame();
	frame.setPreferredSize(new Dimension(700, 500));

	frame.setLayout(new BorderLayout());
	frame.add(mainPanel);
	frame.pack();
	frame.setVisible(true);
    }


    public void readNewMapError(Exception e, GameMap gameMap){

	String errorMessage = e + "\n Error reading maps.json file. \n Do you want to try again?";
	int userAnswer = JOptionPane.showConfirmDialog(null, errorMessage);
	if (userAnswer == JOptionPane.YES_OPTION){
	    readNewMap(gameMap);
	}
	else {System.exit(1);}
    }

    public void readNewMap(GameMap gameMap){

	try {
	    gameMap.readMap();
	    logger.fine("succesfully loaded maps.json file");

	} catch (IOException ioException) {
	    // attempt ask the user to try again
	    // write what has happend and the stacktrace to the log file
	    // the stacktrace is also found in the console
	    String ioError = " could not find or read maps.json file, asking user to try again";
	    logger.log(Level.WARNING, ioError, ioException);
	    readNewMapError(ioException, gameMap);
	}
	catch (JsonSyntaxException jsonSyntaxException){
	    // handled the same way as ioExceltion with the difference being non valid text was inside the file
	    String jsonError = "maps.json does not contain valid json syntax, asking user to try again";
	    logger.log(Level.WARNING, jsonError, jsonSyntaxException);
	    readNewMapError(jsonSyntaxException, gameMap);
	}

    }

    public void startGame(int mapIndex) {

	gameMap.loadMap(mapIndex);

	gameHandler = new GameHandler(gameMap);
	viewer = new GameViewer(gameHandler, GAME_SCALE);
	gameHandler.addListener(this);
	viewer.createInterface();

    }

    @Override public void gameChanged() {
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
	    }
	else {
	    System.exit(0);
	    }
	}
    }

    public class ButtonEvent extends AbstractAction{

	private int index;
	public ButtonEvent(int i) {
	    this.index = i;
	}

	@Override public void actionPerformed(final ActionEvent e) {

	    startGame(index);
	    frame.setVisible(false);
	}
    }

}
