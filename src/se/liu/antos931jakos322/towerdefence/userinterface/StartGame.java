package se.liu.antos931jakos322.towerdefence.userinterface;


import javax.swing.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * StartGame starts the game.
 * The class creates a start menu with the class StartMenu
 * Also starts and configures the global logger
 */

public class StartGame
{
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {
        // attempt to start the logger. while the logger is not started.
	// the game will not start.
        boolean loggerSuccess = false;
        while(!loggerSuccess){
            loggerSuccess = attemptStartLogger();
	}
        StartMenu startMenu = new StartMenu();
	startMenu.createStartMenu();
    }

    public static boolean attemptStartLogger() {


	try {
	    FileHandler fileTxt = new FileHandler("logging.txt");
	    SimpleFormatter formatterTxt = new SimpleFormatter();
	    fileTxt.setFormatter(formatterTxt);
	    LOGGER.setLevel(Level.FINE);
	    LOGGER.addHandler(fileTxt);
	    return true;
	}
	catch (IOException ioException){
	    // the reason we dont log this exception is because there is no logger file yet.
	    // The log file is what we are trying to create
	    ioException.printStackTrace();
	    String loadErrorMessage = ioException + "\nerror creating logging.txt in source folder do you want to try again?";
	    int answer = JOptionPane.showConfirmDialog(null, loadErrorMessage);
	    if (answer == JOptionPane.YES_OPTION){
		return false;
	    }
	    else {
		System.exit(1);
	    }
	}
	return false;
    }

}
