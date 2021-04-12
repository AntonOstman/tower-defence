package se.liu.antos931jakos322.towerdefence;


/**
 *
 * A tile which
 *
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;
import java.util.List;

public class Tile
{
    private List<Entity> entities;
    private TileType type;
    private Color tileColor;
    private int posX;
    private int posY;
    private EnumMap<TileType, Color> TILE_COLOR;

    public Tile(int posX, int posY, final Entity[] entities, final TileType type) {
	this.entities = new ArrayList<>();
	this.type = type;
	this.TILE_COLOR = createColorMap();
	this.tileColor = randomNuance(TILE_COLOR.get(type));
	this.posX = posX;
	this.posY = posY;
    }

    public TileType getType() {
	return type;
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }
    public void drawTile(final Graphics2D g2d, final int MARGIN, final int TILE_SIZE){

	g2d.setColor(tileColor);
	g2d.fillRect(posX * (MARGIN + TILE_SIZE), posY * (MARGIN+TILE_SIZE), TILE_SIZE, TILE_SIZE);

    }

    @Override public String toString() {
	return type + " ";
    }

    public Color getTileColor() {
	return tileColor;
    }

    public Color randomNuance(Color color){
        // changes the given colors nuances a bit

	Random rnd = new Random();
	int randomInt = rnd.nextInt(50);
	int newRed = 0;
	int newGreen = 0;
	int newBlue = 0;
	if (color.getRed() - randomInt < 0) {
	    newRed = color.getRed() + randomInt;
	}
	else{newRed = color.getRed() - randomInt;}

	if (color.getGreen() - randomInt < 0) {
	    newGreen = color.getGreen() + randomInt;
	}
	else{newGreen = color.getGreen() - randomInt;}

	if (color.getBlue() - randomInt < 0) {
	    newBlue = color.getBlue() + randomInt;
	}
	else{ newBlue = color.getBlue() - randomInt;}

	Color newColor = new Color(newRed, newGreen, newBlue);

	return newColor;
    }

    private static EnumMap<TileType, Color> createColorMap(){

        EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);

        tileColors.put(TileType.GRASS, Color.GREEN);
	tileColors.put(TileType.ROADO, Color.BLACK);

        return tileColors;
    }

}
