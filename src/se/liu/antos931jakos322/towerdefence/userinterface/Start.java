package se.liu.antos931jakos322.towerdefence.userinterface;

import javax.swing.*;
import java.awt.*;

public class Start implements GameListener
{

    public static void main(String[] args) {
	StartMenu startMenu = new StartMenu();
	startMenu.createStartMenu();
    }

    @Override public void mapChanged() {

    }
}
