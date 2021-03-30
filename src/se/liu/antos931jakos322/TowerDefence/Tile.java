package se.liu.antos931jakos322.TowerDefence;

public class Tile
{
    private Entity entity;
    private tileType type;

    public Tile(final Entity entity, final tileType type) {
	this.entity = entity;
	this.type = type;
    }

    @Override public String toString() {
	return type + " ";
    }
}
