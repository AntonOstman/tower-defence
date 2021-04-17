package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

public class BossEnemy extends Enemy
{
    public BossEnemy(final Point position) {
	super(position);
	enemyScale = 1;
	speed = 20;
	color = Color.GRAY;

    }

}
