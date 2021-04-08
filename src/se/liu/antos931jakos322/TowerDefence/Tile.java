package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;
import java.util.List;

public class Tile
{
    private List<Entity> entities;
    private tileType type;
    private Color tileColor;
    private int posX;
    private int posY;

    private final static EnumMap<tileType, Color> TILE_COLOR = createColorMap();

    public Tile(int posX, int posY, final Entity[] entities, final tileType type) {
	this.entities = new ArrayList<>();
	this.type = type;
	this.tileColor = randomNuance(TILE_COLOR.get(type));
	this.posX = posX;
	this.posY = posY;
    }

    public tileType getType() {
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
