package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.util.EnumMap;

public class EnemyGetter
{
    public static Enemy getEnemy(EnemyType type){
	EnumMap<EnemyType,Enemy> enemyMap = new EnumMap<>(EnemyType.class);
	enemyMap.put(EnemyType.BIG_BOSS, new BigBossEnemy());
	enemyMap.put(EnemyType.BOSS, new BossEnemy());
	enemyMap.put(EnemyType.SPEED, new SpeedEnemy());
	enemyMap.put(EnemyType.EXPLODING, new ExplodingEnemy());
	enemyMap.put(EnemyType.STANDARD, new StandardEnemy());

	return enemyMap.get(type);
    }
}
