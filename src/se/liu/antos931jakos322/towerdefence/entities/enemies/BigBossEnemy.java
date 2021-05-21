package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.awt.*;

public class BigBossEnemy extends Enemy
{
    private static final int HEALTH = 3000;
    private static final double SPEED = 0.04;
    private static final Color COLOR = Color.CYAN;
    private static final double SIZE = 1.2;
    private static final int DAMAGE = 30;

    public BigBossEnemy() {
	super(HEALTH, SPEED, COLOR, SIZE, DAMAGE);
    }
}
