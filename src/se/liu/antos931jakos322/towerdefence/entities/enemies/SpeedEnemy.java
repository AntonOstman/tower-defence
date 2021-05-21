package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.awt.*;

public class SpeedEnemy extends Enemy
{
    private static final int HEALTH = 70;
    private static final double SPEED = 0.2;
    private static final Color COLOR = Color.PINK;
    private static final double SIZE = 0.3;
    private static final int DAMAGE = 3;

    public SpeedEnemy() {
	super(HEALTH, SPEED, COLOR, SIZE, DAMAGE);
    }
}
