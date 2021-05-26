package se.liu.antos931jakos322.towerdefence.gamelogic;

import se.liu.antos931jakos322.towerdefence.entities.enemies.BossEnemy;
import se.liu.antos931jakos322.towerdefence.entities.enemies.Enemy;
import se.liu.antos931jakos322.towerdefence.entities.enemies.ExplodingEnemy;
import se.liu.antos931jakos322.towerdefence.entities.enemies.FlyingEnemy;
import se.liu.antos931jakos322.towerdefence.entities.enemies.SpeedEnemy;
import se.liu.antos931jakos322.towerdefence.entities.enemies.StandardEnemy;

import java.util.ArrayList;
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

    public WaveMaker() {

        this.waveLevel = 1;
        this.tickCounter = 1;
        this.activeWave = false;
        this.activeWaveCounter = 0;
        this.waveTimer = 400;               // After how many ticks a new wave is made
        this.tickCounter = waveTimer-1;    // The first wave start after 10 tick
        this.waveActiveTime = 100;          // the wave duration
        this.enemies = new ArrayList<>();

    }

    public List<Enemy> update(){

        enemies.clear();
        if(tickCounter % waveTimer == 0){
            activeWave = true;
        }
        if(activeWave){
            if(activeWaveCounter == waveActiveTime){
                waveLevel++;
                activeWaveCounter = 0;
                activeWave = false;
            } else {
                activeWaveCounter++;

                return createWave(waveLevel);
            }
        }
        tickCounter ++;
        return enemies;
    }

    public int getWaveLevel() {
        return waveLevel;
    }

    private List<Enemy> createWave(int waveLevel){
        // phases:
        //      1. Only generic
        //      2. Generic + speedy
        //      3. Generic + speedy + boss
        //      4. Endless (all types)

        // The amount is how many enemies of that type sparn in a wave.
        // The enemy type is determened by the type you send as parameter.
        // Tip! To change spawning rate, change the amount.
        //      To change the spawning change speed, multiply / devide the wavelevel varible.
        Enemy genericEnemy = new StandardEnemy();
        Enemy speedEnemy = new SpeedEnemy();
        Enemy bossEnemy = new BossEnemy();
        Enemy explodingEnemy = new ExplodingEnemy();
        Enemy flyingEnemy = new FlyingEnemy();


        if(waveLevel < 5){
            spawnEnemy( 2 + waveLevel, genericEnemy);
            spawnEnemy(  waveLevel, flyingEnemy);
        } else if(waveLevel < 10){
            spawnEnemy( -4 + waveLevel, flyingEnemy);
            spawnEnemy( -4 + waveLevel, explodingEnemy);
            spawnEnemy( -1 + waveLevel, genericEnemy);
            spawnEnemy( -2 + waveLevel, speedEnemy);
        } else if(waveLevel < 15){
            spawnEnemy( -6 + waveLevel, genericEnemy);
            spawnEnemy( -6 + waveLevel, speedEnemy);
            spawnEnemy( -8 + waveLevel, flyingEnemy);
            spawnEnemy( -8 + waveLevel, explodingEnemy);

        } else{
            spawnEnemy( -10 + waveLevel, bossEnemy);
            spawnEnemy( -10 + waveLevel, genericEnemy);
            spawnEnemy( -10 + waveLevel, speedEnemy);
            spawnEnemy( -10 + waveLevel, bossEnemy);
            spawnEnemy( -14 + waveLevel, explodingEnemy);
        }
        return enemies;
    }

    private void spawnEnemy(int amount, Enemy enemyType){
        int spawnSpeed = waveActiveTime/amount;
        if(spawnSpeed == 0){spawnSpeed = 1;}
        if( activeWaveCounter % spawnSpeed == 0){
            enemies.add(enemyType);
        }

    }

}
