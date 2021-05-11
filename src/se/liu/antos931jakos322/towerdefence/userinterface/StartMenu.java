package se.liu.antos931jakos322.towerdefence.userinterface;

import se.liu.antos931jakos322.towerdefence.maplogic.GameHandler;
import se.liu.antos931jakos322.towerdefence.maplogic.Map;
import se.liu.antos931jakos322.towerdefence.maplogic.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StartMenu implements GameListener
{
    private JFrame frame;
    private GameHandler gameHandler;
    private GameViewer viewer;
    public StartMenu() {
	this.frame = null;
	this.gameHandler = null;
	this.viewer = null;
    }

    public void createStartMenu(){
        Color background = new Color(100,100,100);

	frame = new JFrame();
	frame.setPreferredSize(new Dimension(700, 500));

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

	Map map = new Map();
	for (int j = 0; j < 2; j++) {
	    for (int i = 0; i < 3; i++) {
	        ButtonEvent buttonListener = new ButtonEvent(i);
		try {
		    map.loadMap(i);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		JButton b = new JButton();
		final int bufferedImageWidth = 105;
		final int bufferedImageHeight = 105;
		BufferedImage lineImage = new BufferedImage(bufferedImageWidth, bufferedImageHeight,
							    BufferedImage.TYPE_INT_RGB);

		Graphics2D bg2d = lineImage.createGraphics();

		int mapY = map.getHeigth();
		int mapX = map.getWidth();
		for (int y = 0; y < mapY; y++) {
		    for (int x = 0; x < mapX; x++) {
			Tile currentTile = map.getTile(new Point(x, y));
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

    public void startGame(int mapIndex) {

	Map map = new Map(); // move to gamehandelr?
	try {
	    map.loadMap(mapIndex);
	}
	catch (IOException e){
	    // not handeled correctly
	    e.printStackTrace();
	}


	gameHandler = new GameHandler(map);
	viewer = new GameViewer(gameHandler);
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
