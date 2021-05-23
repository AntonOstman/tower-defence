package se.liu.antos931jakos322.towerdefence.entities.enemies;

import se.liu.antos931jakos322.towerdefence.entities.towers.AirplaneTower;
import se.liu.antos931jakos322.towerdefence.entities.towers.ArrowTower;
import se.liu.antos931jakos322.towerdefence.entities.towers.CanonTower;
import se.liu.antos931jakos322.towerdefence.entities.towers.MachineGunTower;
import se.liu.antos931jakos322.towerdefence.entities.towers.Tower;
import se.liu.antos931jakos322.towerdefence.entities.towers.TowerType;

import java.util.EnumMap;

public class EnemyGetter
{
    public static Enemy getEnemy(EnemyType type){
	EnumMap<EnemyType,Enemy> enemyMap = new EnumMap<>(EnemyType.class);
	enemyMap.put(EnemyType.BigBossEnemy, new BigBossEnemy());
	enemyMap.put(EnemyType.BossEnemy, new BossEnemy());
	enemyMap.put(EnemyType.SpeedEnemy, new SpeedEnemy());
	enemyMap.put(EnemyType.ExplodingEnemy, new ExplodingEnemy());
	enemyMap.put(EnemyType.StandardEnemy, new StandardEnemy());

	return enemyMap.get(type);
    }
}
