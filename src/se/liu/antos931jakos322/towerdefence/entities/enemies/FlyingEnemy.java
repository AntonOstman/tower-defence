package se.liu.antos931jakos322.towerdefence.entities.enemies;

import se.liu.antos931jakos322.towerdefence.other.HelperFunctions;

import java.awt.*;

public class FlyingEnemy extends Enemy
{
    private static final int HEALTH = 200;
    private static final double SPEED = 0.1;
    private static final Color COLOR = Color.WHITE;
    private static final double SIZE = 0.5;
    private static final int DAMAGE = 3;

    public FlyingEnemy() {
	super(HEALTH, SPEED, COLOR, SIZE, DAMAGE);
    }

    @Override public void move() {
        // Skipp all positions to move to except for the last
	if(position == null){
	    position = movePosition;
	} else if (pathProgress == lastPosition){
	    super.move();
	} else {
	    pathProgress ++;
	}
	// finished flag is set because pathprogress is at last position
	// keep it at finished if we are close to finished, else the enemy are not finished.
	final double distance = 0.2;
	if(HelperFunctions.isNear(position, movePosition, distance)){
	    return;
	}
	finished = false;


    }
}