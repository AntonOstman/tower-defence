package se.liu.antos931jakos322.towerdefence.entities.projectiles;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.EnumMap;


/**
 *
 * ProjectileGetter is a classed used to return a projectile object
 *  Example:
 *  	getProjectile(projectiletype) returns the a new projectile object of the desired type
 *
 */


public class ProjectileGetter
{


    public static Projectile getProjectile(ProjectileType type){
	EnumMap<ProjectileType, Projectile> projectileMap = new EnumMap<>(ProjectileType.class);

	projectileMap.put(ProjectileType.ARROW, new ArrowProjectile());
	projectileMap.put(ProjectileType.CANON, new CanonProjectile());
	projectileMap.put(ProjectileType.MACHINE, new BulletProjectile());
	projectileMap.put(ProjectileType.MISSILE, new MissileProjectile());

	return projectileMap.get(type);
    }

}
