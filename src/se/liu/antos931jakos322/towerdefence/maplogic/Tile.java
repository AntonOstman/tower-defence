package se.liu.antos931jakos322.towerdefence.maplogic;



import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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
    private int posX;// should be changed into a point
    private int posY; // should be changed into a point
    private final static Random RND = new Random();


    public Tile(int posX, int posY, final TileType tileType) {
	this.tileType = tileType;
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


	int oldRed = color.getRed();
	int oldBlue = color.getBlue();
	int oldGreen = color.getGreen();

	List<Integer> colors = new ArrayList<>();

	colors.add(oldRed);
	colors.add(oldGreen);
	colors.add(oldBlue);
	int colorSpan = 50; // the span in which the colors can differs for a tile
	int randomInt = RND.nextInt(colorSpan);

	for (int i = 0; i < 3; i++) {
	    int oldColor = colors.get(i);
	    int newColor;
	    if(oldColor - randomInt >= 0){
		newColor = oldColor - randomInt;
	    }
	    else{
		newColor = oldColor + randomInt;
	    }
	    colors.set(i, newColor);
	}

	int newRed = colors.get(0);
	int newGreen = colors.get(1);
	int newBlue = colors.get(2);

	Color newColor = new Color(newRed, newGreen, newBlue);

	return newColor;
    }

    private static EnumMap<TileType, Color> createColorMap(){

        EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);

        tileColors.put(TileType.GRASS, Color.GREEN);
	tileColors.put(TileType.ROAD, Color.BLACK);

        return tileColors;
    }

    public int getPosX() {
	return posX;
    }

    public int getPosY() {
	return posY;
    }
}
