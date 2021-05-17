package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.EnumMap;

public class ProjectileMaker
{


    public static Projectile getProjectile(ProjectileType type, Point2D start, int attackPower){
	EnumMap<ProjectileType, Projectile> projectileMap = new EnumMap<>(ProjectileType.class);

	projectileMap.put(ProjectileType.BULLET, createBulletProjectile(start, attackPower));
	projectileMap.put(ProjectileType.CANON, createCanonProjectile(start, attackPower));
	projectileMap.put(ProjectileType.MACHINE, createMachineProjectile(start, attackPower));
	Projectile projectile = projectileMap.get(type);
	projectile.setPosition(start);
	return projectile;
    }
    private static Projectile createBulletProjectile(Point2D start, int attackPower){
	final double drawScale = 0.23;
	final double projectileSpeed = 0.4;
	final int penetrationAmount = 2;

	Projectile projectile = new BulletProjectile(Color.red, drawScale, projectileSpeed, start,penetrationAmount, attackPower);
	return projectile;
    }

    private static Projectile createCanonProjectile(Point2D start, int attackPower){

        final double drawScale = 0.35;
        final double projectileSpeed = 0.15;
        final int penetrationAmount = 10;

	Projectile projectile = new BulletProjectile(Color.lightGray, drawScale, projectileSpeed, start,penetrationAmount, attackPower);
	return projectile;
    }


    private static Projectile createMachineProjectile(Point2D start, int attackPower){
	final double drawScale = 0.15;
	final double projectileSpeed = 0.5;
	final int penetrationAmount = 1;

	Projectile projectile = new BulletProjectile(Color.MAGENTA, drawScale, projectileSpeed, start,penetrationAmount, attackPower);
	return projectile;
    }
}
