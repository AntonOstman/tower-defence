package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class ProjectileMaker
{

    public ProjectileType[] getAllProjectiles(){

    return ProjectileType.values(); // this should be used in the final implementaion

    }

    public Projectile getProjectile(ProjectileType type, Point start, int attackPower){
	EnumMap<ProjectileType, Projectile> projectileMap = new EnumMap<>(ProjectileType.class);

	projectileMap.put(ProjectileType.BULLET, createBulletProjectile(start, attackPower));
	projectileMap.put(ProjectileType.CANON, createCanonProjectile(start, attackPower));
	projectileMap.put(ProjectileType.MACHINE, createMachineProjectile(start, attackPower));
	Projectile projectile = projectileMap.get(type);
	projectile.setPosition(start);
	return projectile;
    }
    private Projectile createBulletProjectile(Point start, int attackPower){
	double drawScale = 0.23;
	double projectileSpeed = 2;
	int penetrationAmount = 2;

	Projectile projectile = new BulletProjectile(Color.red, drawScale, projectileSpeed, start,penetrationAmount, attackPower);
	return projectile;
    }

    private Projectile createCanonProjectile(Point start, int attackPower){

        double drawScale = 0.35;
        double projectileSpeed = 0.5;
        int penetrationAmount = 10;

	Projectile projectile = new BulletProjectile(Color.lightGray, drawScale, projectileSpeed, start,penetrationAmount, attackPower);
	return projectile;
    }


    private Projectile createMachineProjectile(Point start, int attackPower){
	double drawScale = 0.15;
	double projectileSpeed = 3;
	int penetrationAmount = 1;

	Projectile projectile = new BulletProjectile(Color.MAGENTA, drawScale, projectileSpeed, start,penetrationAmount, attackPower);
	return projectile;
    }
}
