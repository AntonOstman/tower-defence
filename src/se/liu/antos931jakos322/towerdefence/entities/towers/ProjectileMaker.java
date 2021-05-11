package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.EnumMap;

public class ProjectileMaker
{

    public ProjectileType[] getAllProjectiles(){

    return ProjectileType.values(); // this should be used in the final implementaion

    }

    public Projectile getProjectile(ProjectileType type, Point2D start, int attackPower){
	EnumMap<ProjectileType, Projectile> projectileMap = new EnumMap<>(ProjectileType.class);

	projectileMap.put(ProjectileType.BULLET, createBulletProjectile(start, attackPower));
	projectileMap.put(ProjectileType.CANON, createCanonProjectile(start, attackPower));
	projectileMap.put(ProjectileType.MACHINE, createMachineProjectile(start, attackPower));
	Projectile projectile = projectileMap.get(type);
	projectile.setPosition(start);
	return projectile;
    }
    private Projectile createBulletProjectile(Point2D start, int attackPower){
	double drawScale = 0.23;
	double projectileSpeed = 0.5;
	int penetrationAmount = 2;

	Projectile projectile = new BulletProjectile(Color.red, drawScale, projectileSpeed, start,penetrationAmount, attackPower);
	return projectile;
    }

    private Projectile createCanonProjectile(Point2D start, int attackPower){

        double drawScale = 0.35;
        double projectileSpeed = 0.2;
        int penetrationAmount = 10;

	Projectile projectile = new BulletProjectile(Color.lightGray, drawScale, projectileSpeed, start,penetrationAmount, attackPower);
	return projectile;
    }


    private Projectile createMachineProjectile(Point2D start, int attackPower){
	double drawScale = 0.15;
	double projectileSpeed = 1;
	int penetrationAmount = 1;

	Projectile projectile = new BulletProjectile(Color.MAGENTA, drawScale, projectileSpeed, start,penetrationAmount, attackPower);
	return projectile;
    }
}
