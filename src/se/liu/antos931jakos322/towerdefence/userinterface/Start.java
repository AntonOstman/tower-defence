package se.liu.antos931jakos322.towerdefence.userinterface;


public class Start implements GameListener
{

    public static void main(String[] args) {
	StartMenu startMenu = new StartMenu();
	startMenu.createStartMenu();
    }

    @Override public void mapChanged() {

    }
}
