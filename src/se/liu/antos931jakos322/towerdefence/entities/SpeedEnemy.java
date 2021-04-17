package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

public class SpeedEnemy extends Enemy
{
    public SpeedEnemy(final Point position) {
	super(position);
	enemyScale = 0.3;
	speed = 3;
    }

}
