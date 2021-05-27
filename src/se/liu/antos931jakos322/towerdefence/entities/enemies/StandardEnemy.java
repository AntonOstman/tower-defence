package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.awt.*;

/**
 *
 * StandardEnemy extends the abstract class Enemy
 * StandardEnemy is the most basic type of enemy that exists in the game.
 * It has low health and does not have extra features
 *
 */

public class StandardEnemy extends Enemy
{
    private static final int HEALTH = 100;
    private static final double SPEED = 0.1;
    private static final Color COLOR = Color.RED;
    private static final double SIZE = 0.5;
    private static final int DAMAGE = 1;

    public StandardEnemy() {
        super(HEALTH, SPEED, COLOR, SIZE, DAMAGE);
    }
}
