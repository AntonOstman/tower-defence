package se.liu.antos931jakos322.TowerDefence;

public class GameTester
{
    public static void main(String[] args) {
    Map map = new Map(10,10);
    map.hardCodedMap();
    map.hardRoad();
    MapViewer viewer = new MapViewer(map);
    //viewer.viewMapText();
    viewer.show();

    }
}
