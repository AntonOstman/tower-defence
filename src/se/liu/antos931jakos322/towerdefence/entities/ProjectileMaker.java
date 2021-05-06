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

	Projectile projectile = new BulletProjectile(Color.red, 0.23, 2, start,2, attackPower);
	return projectile;
    }

    private Projectile createCanonProjectile(Point start, int attackPower){

	Projectile projectile = new BulletProjectile(Color.lightGray, 0.35, 0.5, start,10, attackPower);
	return projectile;
    }


    private Projectile createMachineProjectile(Point start, int attackPower){

	Projectile projectile = new BulletProjectile(Color.MAGENTA, 0.15, 3, start,1, attackPower);
	return projectile;
    }
}
