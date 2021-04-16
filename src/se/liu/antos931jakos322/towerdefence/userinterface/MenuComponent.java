package se.liu.antos931jakos322.towerdefence.userinterface;

import se.liu.antos931jakos322.towerdefence.maplogic.Map;
import se.liu.antos931jakos322.towerdefence.maplogic.MapListener;

import javax.swing.*;
import java.awt.*;

public class MenuComponent extends JComponent implements MapListener
{
    private Map map;
    private int textSize;

    public MenuComponent(final Map map) {
	this.map = map;
	this.textSize = 20;
    }

    @Override public void mapChanged() {
	repaint();
    }


    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
	String health = "Life: " + map.getHealth();
	String currentMoney = "Money: " + map.getMoney();
	g2d.setColor(Color.BLACK);
	g2d.setFont(new Font("serif", Font.PLAIN, textSize));

	g2d.drawString(health, 0, 30);
    	g2d.drawString(currentMoney,0,60);
    }
}
