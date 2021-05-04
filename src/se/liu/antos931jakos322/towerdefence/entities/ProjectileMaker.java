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

    public Projectile getProjectile(ProjectileType type, Point start){
	EnumMap<ProjectileType, Projectile> projectileMap = new EnumMap<>(ProjectileType.class);

	projectileMap.put(ProjectileType.BULLET, createBulletProjectile(start));

	Projectile projectile = projectileMap.get(type);
	projectile.setPosition(start);
	return projectile;
    }
    private Projectile createBulletProjectile(Point start){

	Projectile projectile = new BulletProjectile(Color.cyan, 0.2, 6, start);
	return projectile;
    }


}
