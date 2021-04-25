package se.liu.antos931jakos322.towerdefence.userinterface;



import se.liu.antos931jakos322.towerdefence.maplogic.GameHandler;

import javax.swing.*;
import java.awt.*;
/**
 *
 * MenuComponent controls the main text based information in the game
 * Example:
 * 	Draws the current player health and current money
 *
 *
 */
public class MenuComponent extends JComponent implements GameListener
{
    private GameHandler gameHandler;
    private int textSize;

    public MenuComponent(final GameHandler gameHandler) {
        this.gameHandler = gameHandler;
	this.textSize = 20;
    }



    @Override public void mapChanged() {
	repaint();
    }


    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
	String health = "Life: " + gameHandler.getHealth();
	String currentMoney = "Money: " + gameHandler.getMoney();
	g2d.setColor(Color.BLACK);
	g2d.setFont(new Font("serif", Font.PLAIN, textSize));

	g2d.drawString(health, 0, 30);
    	g2d.drawString(currentMoney,0,60);
    }
}
