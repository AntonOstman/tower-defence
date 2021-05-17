package se.liu.antos931jakos322.towerdefence.gamelogic;



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
        // changes the nuances of the tile color
	EnumMap<TileType, Color> tileColorEnum = createColorMap();
	Color color = tileColorEnum.get(tileType);


	List<Integer> colors = new ArrayList<>();

	// add the colors in order red green blue
	colors.add(color.getRed());
	colors.add(color.getGreen());
	colors.add(color.getBlue());

	final int colorSpan = 50; // the span in which the colors can differs for a tile
	final int randomInt = RND.nextInt(colorSpan);
	final int colorAmount = 3;

	for (int i = 0; i < colorAmount; i++) {
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
	// we added red first, then green then blue to the colors list.
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

}
