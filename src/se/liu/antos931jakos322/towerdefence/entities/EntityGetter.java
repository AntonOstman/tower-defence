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

/**
 * EntityGetter is a class with static methods that can return a new Entity object with the Entity specific Enum as argument.
 * Example: EntityGetter.getTower(TowerType.CANON) returns a new CanonTower object.
 * Can also return the list of avaible TowerType's by using EntityGetter.getAllTowers()
 *
 */

public class EntityGetter
{

    public static Projectile getProjectile(ProjectileType type){
	EnumMap<ProjectileType, Projectile> projectiles = new EnumMap<>(ProjectileType.class);

	projectiles.put(ProjectileType.ARROW, new ArrowProjectile());
	projectiles.put(ProjectileType.EXPLODING, new ExplodingProjectile());
	projectiles.put(ProjectileType.BULLET, new BulletProjectile());
	projectiles.put(ProjectileType.STICKY, new StickyProjectile());
	projectiles.put(ProjectileType.PENETRATING, new PenetratingProjectile());


	return projectiles.get(type);
    }
    public static Enemy getEnemy(EnemyType type){
	EnumMap<EnemyType,Enemy> enemies = new EnumMap<>(EnemyType.class);
	enemies.put(EnemyType.BIG_BOSS, new BigBossEnemy());
	enemies.put(EnemyType.BOSS, new BossEnemy());
	enemies.put(EnemyType.SPEED, new SpeedEnemy());
	enemies.put(EnemyType.EXPLODING, new ExplodingEnemy());
	enemies.put(EnemyType.STANDARD, new StandardEnemy());

	return enemies.get(type);
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
	EnumMap<TowerType,Tower> towers = new EnumMap<>(TowerType.class);
	towers.put(TowerType.ARROW, new ArrowTower());
	towers.put(TowerType.CANON, new CanonTower());
	towers.put(TowerType.MACHINE_GUN, new MachineGunTower());
	towers.put(TowerType.AIRPLANE, new AirplaneTower());

	return towers.get(type);
    }


}
