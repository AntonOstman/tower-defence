package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.awt.*;
/**
 * BigBossEnemy is an enemy type that extends the abstract class Enemy.
 * BossEnemy have alot of health (more than BossEnemy) but moves slow.
 */
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
