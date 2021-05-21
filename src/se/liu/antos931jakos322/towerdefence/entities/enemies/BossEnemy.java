package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.awt.*;

public class BossEnemy extends Enemy
{
    private static final int HEALTH = 1000;
    private static final double SPEED = 0.05;
    private static final Color COLOR = Color.PINK;
    private static final double SIZE = 0.9;
    private static final int DAMAGE = 10;

    public BossEnemy() {
	super(HEALTH, SPEED, COLOR, SIZE, DAMAGE);
    }
}
