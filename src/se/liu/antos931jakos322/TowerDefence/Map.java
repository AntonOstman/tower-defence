package se.liu.antos931jakos322.TowerDefence;

import java.awt.*;

public class Map
{
    private Tile[][] tiles;
    private int width, height;

    public Map(final int width, final int height) {

	this.width = width;
	this.height = height;
	this.tiles = new Tile[height][width];
    }

    public void hardCodedMap(){
	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {
		tiles[h][w] = new Tile(null, tileType.grass);
	    }
	}
    }
    public void hardRoad(){
        tiles[5][0] = new Tile(null, tileType.roado);
	tiles[5][1] = new Tile(null, tileType.roado);
	tiles[5][2] = new Tile(null, tileType.roado);
	tiles[5][3] = new Tile(null, tileType.roado);
	tiles[5][4] = new Tile(null, tileType.roado);
	tiles[5][5] = new Tile(null, tileType.roado);
	tiles[6][5] = new Tile(null, tileType.roado);
	tiles[7][5] = new Tile(null, tileType.roado);
	tiles[7][6] = new Tile(null, tileType.roado);
	tiles[7][7] = new Tile(null, tileType.roado);
	tiles[7][8] = new Tile(null, tileType.roado);
	tiles[7][9] = new Tile(null, tileType.roado);

    }

    public Tile getTile(int x, int y){
        return tiles[y][x];
    }

    public Point getDimension() {
	return new Point(width, height);
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    @Override public String toString() {
	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {
		System.out.print(tiles[h][w]);
	    }
	    System.out.println();
	}
    return null;
    }
}
