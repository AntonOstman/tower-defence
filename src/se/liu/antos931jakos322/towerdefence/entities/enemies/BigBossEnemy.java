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
        BossEnemy e1 = new BossEnemy();
        e1.setPosition(new Point2D.Double(position.getX() + 1, position.getY() + 1));
        e1.setPathProgress(pathProgress);
        enemies.add(e1);
        BossEnemy e2 = new BossEnemy();
        e2.setPosition(new Point2D.Double(position.getX()-1, position.getY()-1));
        e2.setPathProgress(pathProgress);
        enemies.add(e2);

        return enemies;
    }
}
