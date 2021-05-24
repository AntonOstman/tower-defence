package se.liu.antos931jakos322.towerdefence.entities;

import se.liu.antos931jakos322.towerdefence.entities.enemies.BigBossEnemy;
import se.liu.antos931jakos322.towerdefence.entities.enemies.BossEnemy;
import se.liu.antos931jakos322.towerdefence.entities.enemies.Enemy;
import se.liu.antos931jakos322.towerdefence.entities.enemies.EnemyType;
import se.liu.antos931jakos322.towerdefence.entities.enemies.ExplodingEnemy;
import se.liu.antos931jakos322.towerdefence.entities.enemies.SpeedEnemy;
import se.liu.antos931jakos322.towerdefence.entities.enemies.StandardEnemy;
import se.liu.antos931jakos322.towerdefence.entities.projectiles.ArrowProjectile;
import se.liu.antos931jakos322.towerdefence.entities.projectiles.BulletProjectile;
import se.liu.antos931jakos322.towerdefence.entities.projectiles.ExplodingProjectile;
import se.liu.antos931jakos322.towerdefence.entities.projectiles.PenetratingProjectile;
import se.liu.antos931jakos322.towerdefence.entities.projectiles.Projectile;
import se.liu.antos931jakos322.towerdefence.entities.projectiles.ProjectileType;
import se.liu.antos931jakos322.towerdefence.entities.projectiles.StickyProjectile;
import se.liu.antos931jakos322.towerdefence.entities.towers.AirplaneTower;
import se.liu.antos931jakos322.towerdefence.entities.towers.ArrowTower;
import se.liu.antos931jakos322.towerdefence.entities.towers.CanonTower;
import se.liu.antos931jakos322.towerdefence.entities.towers.MachineGunTower;
import se.liu.antos931jakos322.towerdefence.entities.towers.Tower;
import se.liu.antos931jakos322.towerdefence.entities.towers.TowerType;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * EntityGetter is a class with static methods that can return a new Entity object with the Entity specific Enum as argument.
 * Example: EntityGetter.getTower(TowerType.CANON) returns a new CanonTower object.
 * Can also return the list of avaible TowerType's by using EntityGetter.getAllTowers()
 *
 */

public class EntityGetter
{

    public static Projectile getProjectile(ProjectileType type) throws IllegalArgumentException{
	switch (type) {
	    case ARROW: return new ArrowProjectile();
	    case EXPLODING: return new ExplodingProjectile();
	    case BULLET: return new BulletProjectile();
	    case STICKY: return new StickyProjectile();
	    case PENETRATING: return new PenetratingProjectile();
	    default:
	        return null;
	}
    }

    public static Enemy getEnemy(EnemyType type) throws IllegalArgumentException{
	switch (type) {
	    case BIG_BOSS: return new BigBossEnemy();
	    case BOSS: return new BossEnemy();
	    case SPEED: return new SpeedEnemy();
	    case EXPLODING: return new ExplodingEnemy();
	    case STANDARD: return new StandardEnemy();
	    default:
		return null;
	}
    }

    /**
     * Returns a list of the towerTypes that can be used in the game
     *
     * @return list with all towerTypes
     */
    public static List<TowerType> getAllTowers(){

	List<TowerType> towers = new ArrayList<>();
	for(TowerType towerType: TowerType.values()){
	    if (towerType != TowerType.NONE){
		towers.add(towerType);
	    }
	}
	return towers;
    }

    /**
     * Returns a tower of the specified towerType
     *
     * @param type the towerType to get
     * @return a new Tower object of the specified towerType
     */
    public static Tower getTower(TowerType type){

	switch (type) {
	    case ARROW: return new ArrowTower();
	    case CANON: return new CanonTower();
	    case MACHINE_GUN: return new MachineGunTower();
	    case AIRPLANE: return new AirplaneTower();
	    default:
		return null;
	}
    }
}
