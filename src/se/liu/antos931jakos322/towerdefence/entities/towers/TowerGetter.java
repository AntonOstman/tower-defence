package se.liu.antos931jakos322.towerdefence.entities.towers;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 *
 * TowerGetter returns a new Tower object of the desired towerType
 *  Example:
 *  	TowerGetter.getTower(TowerType) returns the a new Tower object of the desired type
 *
 */
public class TowerGetter
{

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
	EnumMap<TowerType,Tower> towerMap = new EnumMap<>(TowerType.class);
	towerMap.put(TowerType.ARROW, new ArrowTower());
	towerMap.put(TowerType.CANON, new CanonTower());
	towerMap.put(TowerType.MACHINE_GUN, new MachineGunTower());
	towerMap.put(TowerType.AIRPLANE, new AirplaneTower());

	return towerMap.get(type);
    }



}
