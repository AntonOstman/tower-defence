package se.liu.antos931jakos322.towerdefence.maplogic;



import se.liu.antos931jakos322.towerdefence.entities.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import java.util.List;
/**
 *
 * Uses a tileType to decide which type of tile itself is.
 * Depending on what type of tile a tile is it is drawn in different ways
 *
 */

public class Tile
{
    private TileType tileType;
    private Color tileColor;
    private int posX;
    private int posY;
    private Map<TileType, Color> tileColorEnum;
    private final static Random RND = new Random();


    public Tile(int posX, int posY, final Entity[] entities, final TileType tileType) {
	this.tileType = tileType;
	this.tileColorEnum = createColorMap();
	this.tileColor = randomNuance();
	this.posX = posX;
	this.posY = posY;
    }

    public TileType getTileType() {
	return tileType;
    }

    public void drawTile(final Graphics2D g2d, final int margin, final int tileSize){

	g2d.setColor(tileColor);
	g2d.fillRect(posX * (margin + tileSize), posY * (margin + tileSize), tileSize, tileSize);

    }

    @Override public String toString() {
	return tileType + " ";
    }

    public Color getTileColor() {
	return tileColor;
    }

    public Color randomNuance(){
        // changes the nuances of the tile color
	EnumMap<TileType, Color> tileColorEnum = createColorMap();
	Color color = tileColorEnum.get(tileType);

	int randomInt = RND.nextInt(50);

	int newRed = 0;
	int newBlue = 0;
	int newGreen = 0;

	/*

		int newRed = color.getRed();
	int newBlue = color.getBlue();
	int newGreen = color.getGreen();

	List<Integer> colors = new ArrayList<>();

	colors.add(newRed);
	colors.add(newBlue);
	colors.add(newGreen);

	for (int newColor: colors) {
	    int randomInt = RND.nextInt(50);
	    if(newColor - randomInt >= 0){
	        newColor -= randomInt;
	    }
	}


	 */

	if (color.getRed() - randomInt < 0) {
	     newRed = color.getRed() + randomInt;
	}
	else{
	     newRed = color.getRed() - randomInt;
	}

	if (color.getGreen() - randomInt < 0) {
	     newGreen = color.getGreen() + randomInt;
	}
	else{
	     newGreen = color.getGreen() - randomInt;
	}

	if (color.getBlue() - randomInt < 0) {
	     newBlue = color.getBlue() + randomInt;
	}
	else{
	     newBlue = color.getBlue() - randomInt;
	}

	Color newColor = new Color(newRed, newGreen, newBlue);

	return newColor;
    }

    private static EnumMap<TileType, Color> createColorMap(){

        EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);

        tileColors.put(TileType.GRASS, Color.GREEN);
	tileColors.put(TileType.ROADO, Color.BLACK);

        return tileColors;
    }

    public int getPosX() {
	return posX;
    }

    public int getPosY() {
	return posY;
    }
}
