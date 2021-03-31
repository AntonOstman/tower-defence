package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;
import java.util.EnumMap;
import java.util.Random;

public class Tile
{
    private Entity entity;
    private tileType type;
    private Color tileColor;
    private final static EnumMap<tileType, Color> TILE_COLOR = createColorMap();

    public Tile(final Entity entity, final tileType type) {
	this.entity = entity;
	this.type = type;
	this.tileColor = randomNuance(TILE_COLOR.get(type));

    }

    public tileType getType() {
	return type;
    }

    public void drawTile(final Graphics2D g, int x, int y){

    }

    @Override public String toString() {
	return type + " ";
    }

    public Color getTileColor() {
	return tileColor;
    }

    public Color randomNuance(Color color){
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

    private static EnumMap<tileType, Color> createColorMap(){

        EnumMap<tileType, Color> tileColors = new EnumMap<>(tileType.class);

        tileColors.put(tileType.grass , Color.GREEN);
	tileColors.put(tileType.roado, Color.BLACK);

        return tileColors;
    }

}
