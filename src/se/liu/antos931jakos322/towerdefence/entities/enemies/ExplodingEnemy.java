package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.awt.*;

/**
 * ExplodingEnemy is an enemy type that extends the abstract class Enemy.
 * ExplodingEnemy have low health but when health reaches 0, 4 speedEnemy spawns.
 */
public class ExplodingEnemy extends Enemy
{

    private static final int HEALTH = 150;
    private static final double SPEED = 0.03;
    private static final Color COLOR = Color.ORANGE;
    private static final double SIZE = 0.7;
    private static final int DAMAGE = 10;

    private static final int NUMBER_OF_SPLITS = 4;
    private static final EnemyType SPLIT_TYPE = EnemyType.SPEED;
    private static final int SPLIT_DISTANCE = 5;



    public ExplodingEnemy() {
        super(HEALTH, SPEED, COLOR, SIZE, DAMAGE, NUMBER_OF_SPLITS, SPLIT_TYPE, SPLIT_DISTANCE);
    }





}
