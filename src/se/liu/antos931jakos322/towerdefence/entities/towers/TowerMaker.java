package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 *
 * A TowerMaker is a factory class used for the creation of towers
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

    public List<TowerType> getAllTowers(){

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

    public Tower getTower(TowerType type){
	EnumMap<TowerType,Tower> towerMap = new EnumMap<>(TowerType.class);
	towerMap.put(TowerType.ARROW,createArrowTower());
	towerMap.put(TowerType.CANON,createCanonTower());
	towerMap.put(TowerType.MACHINEGUN, createMachineGunTower());

	return towerMap.get(type);
    }
    private Tower createArrowTower(){
	int attackpower = 10;
        int cost = 7;
        int range = 5;
	int upgradeCost = 1;
	int attackSpeed = 8;
	Color arrowColor = Color.BLUE;
        Tower arrowTower = new ArrowTower(TowerType.ARROW, arrowColor, cost, attackpower, range, upgradeCost,
					  ProjectileType.BULLET, attackSpeed);
        return arrowTower;
    }

    private Tower createCanonTower(){
	int canonAttackpower = 5;
	int canonCost = 7;
	int canonRange = 3;
	int upgradeCost = 1;
	int attackSpeed = 20;
	Color color = Color.ORANGE;
	Tower canonTower = new ArrowTower(TowerType.CANON, color, canonCost, canonAttackpower, canonRange, upgradeCost,
					  ProjectileType.CANON, attackSpeed);
        return canonTower;
    }

    private Tower createMachineGunTower(){
	int attackPower = 3;
	int cost = 7;
	int range = 6;
	int upgradeCost = 1;
	int attackSpeed = 1;
	Color machinegunColor = Color.PINK;
	Tower machineGunTower = new ArrowTower(TowerType.MACHINEGUN, machinegunColor, cost, attackPower, range, upgradeCost,
					       ProjectileType.MACHINE, attackSpeed);
	return machineGunTower;


    }


}
