package se.liu.antos931jakos322.towerdefence.userinterface;


import se.liu.antos931jakos322.towerdefence.gamelogic.GameHandler;

import javax.swing.*;
import java.awt.*;

/**
 *
 * MenuComponent draws the text graphics for game health, money and wave level
 * MenuComponent implements GameListener which means it can listen to game changes and update the grapchis accordingly
 *
 *
 */
public class MenuComponent extends JComponent implements GameListener
{
    private GameHandler gameHandler;


    public MenuComponent(final GameHandler gameHandler) {
        this.gameHandler = gameHandler;

    }

    @Override public void gameChanged() {
	repaint();
    }


    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
	String health = "Life: " + gameHandler.getHealth();
	String currentMoney = "Money: " + gameHandler.getMoney();
	String level = "Wave level: " + gameHandler.getLevel();
	g2d.setColor(Color.BLACK);
	g2d.setFont(new Font("serif", Font.PLAIN, GAME_SCALE / 2));
	final int positionX = 0;

	final int margin = GAME_SCALE / 2;
	final int healthPositionY = GAME_SCALE;
	final int moneyPositionY = GAME_SCALE + margin;
	final int levelPositionY = GAME_SCALE + margin * 2;
	g2d.drawString(health, positionX, healthPositionY);
    	g2d.drawString(currentMoney, positionX, moneyPositionY);
	g2d.drawString(level, positionX, levelPositionY);
    }
}
