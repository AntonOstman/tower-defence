package se.liu.antos931jakos322.towerdefence.other;

import java.awt.geom.Point2D;

/**
 *
 * Class with various helper functions
 * The helper functions do directly have anyhting with the game to do
 * example:
 * 	Pythagoras therom is a general mathematical formula
 *
 */


public class HelperFunctions
{

//    public static double pythagoras(double a, double b){
//	// returns the integer value of pythagoras therom for 2 integers
//	double c = Math.pow((a), 2) + Math.pow((b), 2);
//
//	return c;
//    }


    public static boolean isNear(Point2D position1, Point2D position2, double distance){

	double deltaPositionX = position2.getX() - position1.getX();
	double deltaPositionY = position2.getY() - position1.getY();
	double distanceFrom = Math.hypot(deltaPositionX, deltaPositionY);
	if (distanceFrom < distance){
	    return true;
	}
	return false;
    }
}
