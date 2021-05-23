package se.liu.antos931jakos322.towerdefence.entities.projectiles;

import java.awt.*;

public class PenetratingProjectiles extends Projectile
{

    private static final double SIZE = 0.35;
    private static final double SPEED = 0.15;
    private static final int PENETRATION_AMOUNT = 10;
    private final static Color COLOR = Color.DARK_GRAY;

    public PenetratingProjectiles() {
	super(COLOR, SIZE, SPEED, PENETRATION_AMOUNT);
    }



}
