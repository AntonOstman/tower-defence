package se.liu.antos931jakos322.towerdefence.gamelogic;


import java.awt.*;
import java.util.EnumMap;
import java.util.Random;

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
    private Point position;
    private final static Random RND = new Random();


    public Tile(Point position, final TileType tileType) {
	this.tileType = tileType;
	this.tileColor = randomNuance();
	this.position = position;
    }

    public TileType getTileType() {
	return tileType;
    }

    public void drawTile(final Graphics2D g2d, final int margin, final int tileSize){

	g2d.setColor(tileColor);
	g2d.fillRect(position.x * (margin + tileSize), position.y * (margin + tileSize), tileSize, tileSize);

    }

    @Override public String toString() {
	return tileType + " ";
    }


    public Color randomNuance(){
        Color oldColor = getStandardTileColor(tileType);
        return new Color(getRandomColorChannel(oldColor.getRed()),
			 getRandomColorChannel(oldColor.getGreen()),
			 getRandomColorChannel(oldColor.getBlue()));
    }

    private int getRandomColorChannel(int oldColor){
	final int colorSpan = 50;
	final int randomInt = RND.nextInt(colorSpan);
	if(oldColor - randomInt >= 0){ return oldColor - randomInt; }
	else{ return oldColor + randomInt; }

    }

    private static Color getStandardTileColor(TileType tileType){
	EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);

	tileColors.put(TileType.GRASS, Color.GREEN);
	tileColors.put(TileType.ROAD, Color.BLACK);

        return tileColors.get(tileType);
    }
}
