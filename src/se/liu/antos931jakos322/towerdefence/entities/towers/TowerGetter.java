package se.liu.antos931jakos322.towerdefence.entities.towers;

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
public class TowerGetter
{

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
	towerMap.put(TowerType.ARROW, new ArrowTower());
	towerMap.put(TowerType.CANON, new CanonTower());
	towerMap.put(TowerType.MACHINE_GUN, new MachineGunTower());
	towerMap.put(TowerType.AIRPLANE, new AirplaneTower());

	return towerMap.get(type);
    }



}
