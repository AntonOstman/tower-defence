package se.liu.antos931jakos322.towerdefence.entities.projectiles;

import java.awt.*;

public class MissileProjectile extends Projectile
{
    private static final double SIZE = 0.20;
    private static final double SPEED = 0.26;
    private static final int PENETRATION_AMOUNT = 2;
    private static final Color COLOR = Color.CYAN;


    public MissileProjectile() {
	super(COLOR, SIZE, SPEED, PENETRATION_AMOUNT);
    }
}
