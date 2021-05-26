package se.liu.antos931jakos322.towerdefence.gamelogic;

import se.liu.antos931jakos322.towerdefence.entities.EntityFactory;
import se.liu.antos931jakos322.towerdefence.entities.enemies.Enemy;
import se.liu.antos931jakos322.towerdefence.entities.enemies.EnemyType;


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
/**
 * WaveMaker creates the enemies and returns the enemies that will
 * be spawned on the map. The class returnes the enemies in waves that depend
 * on the tickcount. WaveMaker should be activated every tick.
 */
public class WaveMaker
{
    private int waveLevel;
    private int tickCounter;
    private boolean activeWave;
    private int activeWaveCounter;
    private int waveTimer;
    private int waveActiveTime;
    private List<Enemy> enemies;
    private AbstractMap<EnemyType, List<Integer>> spawningRate;

    public WaveMaker() {

        this.waveLevel = 1;
        this.tickCounter = 1;
        this.activeWave = false;
        this.activeWaveCounter = 0;
        this.waveTimer = 400;               // After how many ticks a new wave is made
        this.tickCounter = waveTimer-1;    // The first wave start after 10 tick
        this.waveActiveTime = 100;          // the wave duration
        this.enemies = new ArrayList<>();
        this.spawningRate = createEnumSpawningrate();

    }

    private static AbstractMap<EnemyType, List<Integer>> createEnumSpawningrate(){
        AbstractMap<EnemyType, List<Integer>> spawningRate = new EnumMap<>(EnemyType.class);
        // In the arrayList, the number represent the spawningrate in diffrent phases. Starting at index 0.
        spawningRate.put(EnemyType.STANDARD,    Arrays.asList(2, 3, 4, 5));
        spawningRate.put(EnemyType.SPEED,       Arrays.asList(0, 1, 4, 5));
        spawningRate.put(EnemyType.FLYING,      Arrays.asList(0, 1, 2, 3));
        spawningRate.put(EnemyType.EXPLODING,   Arrays.asList(0, 1, 2, 3));
        spawningRate.put(EnemyType.BOSS,        Arrays.asList(0, 0, 0, 1));

        return spawningRate;
    }
    /**
     * update is activated every tick. update spawn enemies in waves.
     *
     * @return a list of enemies that will be added to the game
     */
    public List<Enemy> update(){
        enemies.clear();
        // if tickcounter is divisible by the wait time ->
        // new wave is started after the wavetimer delay.
        if(tickCounter % waveTimer == 0){
            activeWave = true;
        }
        // if there currently is an active wave -> handle the wave
        if(activeWave){
            // if the waveCounter is ticked up to how long time the wave should be
            // then we set that there is no active wave and reset the waveCounter
            if(activeWaveCounter == waveActiveTime){
                waveLevel++;
                activeWaveCounter = 0;
                activeWave = false;

            }
            // otherwise if there is an active wave we increase the counter and create the wave
            // for the wave the game is currently on
            else {
                activeWaveCounter++;
                // during a wave createWave is activated every tick
                return createWave(waveLevel);
            }
        }
        // tick up how far the waveMaker has been active and return the enemies of the wave.
        // the enemies can also be an empty list if there are no enemies
        tickCounter ++;
        return enemies;
    }

    public int getWaveLevel() {
        return waveLevel;
    }

    private List<Enemy> createWave(int waveLevel){
        // phases:
        //      1. Generic
        //      2. Generic + speedy + flying + exploding
        //      3. Generic + speedy + flying + exploding
        //      4. Endless (all types)

        spawnEnemy( getSpawningRate(EnemyType.STANDARD), EnemyType.STANDARD);
        spawnEnemy( getSpawningRate(EnemyType.SPEED), EnemyType.SPEED);
        spawnEnemy( getSpawningRate(EnemyType.FLYING), EnemyType.FLYING);
        spawnEnemy( getSpawningRate(EnemyType.EXPLODING), EnemyType.EXPLODING);
        spawnEnemy( getSpawningRate(EnemyType.BOSS), EnemyType.BOSS);



        return enemies;
    }

    /**
     * Uses the Enummap spawningrate to get the spawningrate for enemies in diffrent phases.
     * Increase the spawningrate for every level in a phase.
     *
     * @param enemyType is the enemy the method will return the spawningrate
     * @return how many enemies that of enemytype that will spawn in this level
     */
    private int getSpawningRate(EnemyType enemyType) {
        // The diffrent phases. Starting from index 0. To add a phase you need to add a number in the list in the enummap spawningrate
        final List<Integer> phases = Arrays.asList(0, 5, 10, 15);

        for (int i = phases.size() - 2; i >= 0; i--) {
            if (waveLevel >= phases.get(i)) {
                // Is the enummap have a 0 as spawningrate -> no enemies of specified type will spawn this phase.

                int spawns = spawningRate.get(enemyType).get(i);
                if( spawns != 0){
                    return spawningRate.get(enemyType).get(i)  + waveLevel - phases.get(i);
                }
            }
        }
        return 0;
    }

    /**
     * Inputs the number of enemies to add during a wave, then adds the enemies evenly
     * during the wave duration.
     *
     * @param amount is how many enemies that will be added during the wave
     * @param enemyType what type of enemy to add
     */
    private void spawnEnemy(int amount, EnemyType enemyType){
        if(amount != 0){
            int spawnSpeed = waveActiveTime/amount;
            if(spawnSpeed == 0){spawnSpeed = 1;}
            if( activeWaveCounter % spawnSpeed == 0){
                enemies.add(EntityFactory.getEnemy(enemyType));
            }
        }
    }

}
