package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.awt.*;

/**
 * BigBossEnemy is an enemy type that extends the abstract class Enemy.
 * BossEnemy have alot of health (more than BossEnemy) but moves slow.
 */
public class BigBossEnemy extends Enemy
{
    private static final int HEALTH = 1000;
    private static final double SPEED = 0.04;
    private static final Color COLOR = Color.CYAN;
    private static final double SIZE = 1.2;
    private static final int DAMAGE = 30;

    private static final int NUMBER_OF_SPLITS = 2;
    private static final EnemyType SPLIT_TYPE = EnemyType.BOSS;
    private static final int SPLIT_DISTANCE = 2;

    public BigBossEnemy() {
        super(HEALTH, SPEED, COLOR, SIZE, DAMAGE, NUMBER_OF_SPLITS, SPLIT_TYPE, SPLIT_DISTANCE);
    }
}
