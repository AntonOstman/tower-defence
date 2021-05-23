package se.liu.antos931jakos322.towerdefence.entities.projectiles;

import java.util.EnumMap;


/**
 *
 * ProjectileGetter is used to return depending on what projectiletype is given
 *  Example:
 *  	getProjectile(projectiletype) returns the a new projectile object of the desired type
 *
 */


public class ProjectileGetter
{

    public static Projectile getProjectile(ProjectileType type){
	EnumMap<ProjectileType, Projectile> projectileMap = new EnumMap<>(ProjectileType.class);

	projectileMap.put(ProjectileType.ARROW, new ArrowProjectile());
	projectileMap.put(ProjectileType.EXPLODING, new ExplodingProjectile());
	projectileMap.put(ProjectileType.MACHINE, new BulletProjectile());
	projectileMap.put(ProjectileType.STICKY, new StickyProjectile());
	projectileMap.put(ProjectileType.PENETRATING, new PenetratingProjectiles());


	return projectileMap.get(type);
    }

}
