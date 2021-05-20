package se.liu.antos931jakos322.towerdefence.userinterface;



import se.liu.antos931jakos322.towerdefence.gamelogic.GameHandler;

import javax.swing.*;
import java.awt.*;
/**
 *
 * MenuComponent draws the graphics of text based information
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



    @Override public void gameChanged() {
	repaint();
    }


    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
	String health = "Life: " + gameHandler.getHealth();
	String currentMoney = "Money: " + gameHandler.getMoney();
	String level = "Level: " + gameHandler.getLevel();
	g2d.setColor(Color.BLACK);
	g2d.setFont(new Font("serif", Font.PLAIN, textSize));
	final int positionX = 0;
	final int healthPositionY = 30;
	final int moneyPositionY = 60;
	final int levelPositionY = 90;
	g2d.drawString(health, positionX, healthPositionY);
    	g2d.drawString(currentMoney, positionX, moneyPositionY);
	g2d.drawString(level, positionX, levelPositionY);
    }
}
