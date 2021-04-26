package se.liu.antos931jakos322.towerdefence.entities;

import se.liu.antos931jakos322.towerdefence.maplogic.TileType;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

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

    public TowerType[] getAllTowers(){

	// return TowerType.values(); // this should be used in the final implementaion
        // hard coded only to test
	return new TowerType[] { TowerType.ARROW, TowerType.CANON, TowerType.MONEY,TowerType.ARROW,TowerType.ARROW,TowerType.ARROW };
    }

    public Tower getTower(TowerType type){
	EnumMap<TowerType,Tower> towerMap = new EnumMap<>(TowerType.class);
	towerMap.put(TowerType.ARROW,createArrowTower());
	towerMap.put(TowerType.CANON,createCanonTower());
	towerMap.put(TowerType.MONEY,createCanonTower());

	return towerMap.get(type);
    }
    private Tower createArrowTower(){
	int arrowAttackpower = 10;
        int arrowCost = 10;
        int arrowRange = 5;
        Tower arrowTower = new ArrowTower(arrowCost,arrowAttackpower,arrowRange);
        return arrowTower;
    }

    private Tower createCanonTower(){
	int canonAttackpower = 5;
	int canonCost = 5;
	int canonRange = 10;
        Tower canonTower = new CanonTower(canonCost,canonAttackpower,canonRange);
        return canonTower;
    }

    private Tower createMoneyTower(){
	int canonAttackpower = 5;
	int canonCost = 5;
	int canonRange = 10;
	Tower canonTower = new CanonTower(canonCost,canonAttackpower,canonRange);
	return canonTower;


    }


}
