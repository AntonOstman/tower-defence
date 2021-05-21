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
public class TowerMaker
{
    // add creating tower logic here to in a good way be able to choose what
    // tower to create when for eg. getting a string "arrow"
    // instead of if arrow do this, if cannon do this....

    private final static double TOWER_SIZE = 0.6;

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
        Tower arrowTower = new ArrowTower();
        return arrowTower;
    }

    private static Tower createAirplaneTower(){

	Tower arrowTower = new AirplaneTower();
	return arrowTower;
    }

    private static Tower createCanonTower(){

	Tower canonTower = new CanonTower();
	return canonTower;
    }

    private static Tower createMachineGunTower(){
	Tower machineGunTower = new MachineGunTower();
	return machineGunTower;


    }


}
