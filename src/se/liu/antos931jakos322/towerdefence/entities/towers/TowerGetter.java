package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 *
 * TowerGetter returns a new Tower object of the desired towerType
 *  Example:
 *  	getTower(TowerType) returns the a new Tower object of the desired type
 *
 */
public class TowerGetter
{

    /**
     * Returns a list of towerTypes that can be used in the game
     *
     * @return list with all tower types
     */
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

    /**
     * Returns a tower of the specified towerType
     *
     * @param type the towerType to get
     * @return a new Tower object of the specified towerType
     */
    public static Tower getTower(TowerType type){
	EnumMap<TowerType,Tower> towerMap = new EnumMap<>(TowerType.class);
	towerMap.put(TowerType.ARROW, new ArrowTower());
	towerMap.put(TowerType.CANON, new CanonTower());
	towerMap.put(TowerType.MACHINE_GUN, new MachineGunTower());
	towerMap.put(TowerType.AIRPLANE, new AirplaneTower());

	return towerMap.get(type);
    }



}
