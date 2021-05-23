package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

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

    public BigBossEnemy() {
	super(HEALTH, SPEED, COLOR, SIZE, DAMAGE);
    }

    @Override public java.util.List<Enemy> split(){
        List<Enemy> enemies = new ArrayList<>();

        final int distance = 2;
        final int numberOfSpawnedEnemeies = 2;
        for (int i = 0; i < numberOfSpawnedEnemeies; i++) {
            BossEnemy b = new BossEnemy();
            b.setPosition(new Point2D.Double(position.getX() + splitRandomPos(distance), position.getY() + splitRandomPos(distance)));
            b.setPathProgress(pathProgress);
            enemies.add(b);
        }

        return enemies;
    }
}
