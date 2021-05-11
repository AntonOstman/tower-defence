package se.liu.antos931jakos322.towerdefence.entities.enemies;

import java.util.ArrayList;
import java.util.List;

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
                // does the createWave method really need to have enemies as an argument?
                return createWave(waveLevel);
            }
        }
        tickCounter ++;
        return enemies;
    }

    // does the createWave method really need to have enemies as an argument?
    private List<Enemy> createWave(int waveLevel){
        // phases:
        //      1. Only generic
        //      2. Generic + speedy
        //      3. Endless (all types)

        // The amount is how many enemies of that type sparn in a wave.
        // The enemy type is determened by the type you send as parameter.
        // Tip! To change spawning rate, change the amount.
        //      To change the spawning change speed, multiply / devide the wavelevel varible.


        if(waveLevel < 5){
            spawnEnemy( 3 + waveLevel, new GenericEnemy());
        } else if(waveLevel < 10){
            spawnEnemy( -1 + waveLevel, new GenericEnemy());
            spawnEnemy( -1 + waveLevel, new SpeedEnemy());
        } else{
            spawnEnemy( -6 + waveLevel, new GenericEnemy());
            spawnEnemy( -6 + waveLevel, new SpeedEnemy());
            spawnEnemy( -9 + waveLevel, new BossEnemy());
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
