package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 *
 * TowerMaker is a factory class used for the creation of towers
 * TowerMaker contains the specific properties of all towers
 * such as attackdamage range and so on..
 *
 *  Example:
 *  	getTower(TowerType) returns the a new Tower object of the desired type
 *
 */
public class TowerMaker
{
    // add creating tower logic here to in a good way be able to choose what
    // tower to create when for eg. getting a string "arrow"
    // instead of if arrow do this, if cannon do this....

    final static double TOWER_SIZE = 0.6;

    public static List<TowerType> getAllTowers(){

	// return TowerType.values(); // this should be used in the final implementaion
	List<TowerType> towerList = new ArrayList<>();
	for(TowerType towerType: TowerType.values()){
	    if (towerType != TowerType.NONE){
	        towerList.add(towerType);
	    }
	}


	return towerList;
	// hard coded only to test
    }

    public static Tower getTower(TowerType type){
	EnumMap<TowerType,Tower> towerMap = new EnumMap<>(TowerType.class);
	towerMap.put(TowerType.ARROW,createArrowTower());
	towerMap.put(TowerType.CANON,createCanonTower());
	towerMap.put(TowerType.MACHINE_GUN, createMachineGunTower());
	towerMap.put(TowerType.AIRPLANE, createAirplaneTower());

	return towerMap.get(type);
    }

    private static Tower createArrowTower(){
	final int attackpower = 10;
        final int cost = 7;
        final int range = 5;
	final int upgradeCost = 1;
	final int attackSpeed = 8;
	final Color arrowColor = Color.BLUE;
        Tower arrowTower = new StandardTower(TowerType.ARROW, arrowColor, cost, attackpower, range, upgradeCost,
					     ProjectileType.BULLET, attackSpeed, TOWER_SIZE);
        return arrowTower;
    }

    private static Tower createAirplaneTower(){
	final int attackpower = 10;
	final int cost = 7;
	final int range = 5;
	final int upgradeCost = 1;
	final int attackSpeed = 1;
	final double speed = 0.03;
	final Color arrowColor = Color.red;
	Tower arrowTower = new AirplaneTower(TowerType.AIRPLANE, arrowColor, cost, attackpower, range, upgradeCost,
					     ProjectileType.MACHINE, attackSpeed, speed,TOWER_SIZE);
	return arrowTower;
    }
    private static Tower createCanonTower(){
	final int canonAttackpower = 5;
	final int canonCost = 7;
	final int canonRange = 3;
	final int upgradeCost = 1;
	final int attackSpeed = 20;
	final Color color = Color.ORANGE;
	Tower canonTower = new StandardTower(TowerType.CANON, color, canonCost, canonAttackpower, canonRange, upgradeCost,
					     ProjectileType.CANON, attackSpeed, TOWER_SIZE);
        return canonTower;
    }

    private static Tower createMachineGunTower(){
	final int attackPower = 3;
	final int cost = 7;
	final int range = 6;
	final int upgradeCost = 1;
	final int attackSpeed = 1;
	final Color machineGunColor = Color.PINK;
	Tower machineGunTower = new StandardTower(TowerType.MACHINE_GUN, machineGunColor, cost, attackPower, range, upgradeCost,
						  ProjectileType.MACHINE, attackSpeed, TOWER_SIZE);
	return machineGunTower;


    }


}
