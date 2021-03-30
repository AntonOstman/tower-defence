package se.liu.antos931jakos322.TowerDefence;

public class mapViewer
{
private Map map;
    public mapViewer(Map map) {
        this.map = map;
    }

    public void viewMap(){
        int mapX = map.getDimension().x;
	int mapY = map.getDimension().y;
        for (int y = 0; y < mapY; y++) {
	    for (int x = 0; x < mapX; x++) {
		System.out.print(map.getTile(x, y));
	    }
	    System.out.println();
	}
}
}
