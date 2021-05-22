package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

public abstract class EntityAttacker extends Entity
{
    protected Entity targetEntity;

    /**
     * Constructs an EntityAttacker that also has inital speed on creation
     *
     * @param color the color of the entity
     * @param size the size of the entity
     * @param speed movement speed of the entity
     * @param attackPower the amount of damage the entity deals
     */

    protected EntityAttacker(final Color color, final double size, final double speed, final int attackPower) {
	super(color, size, speed, attackPower);
	this.targetEntity = null;
    }

    /**
     * Constructs an EntityAttacker without inital speed
     *
     * @param size the size of the entity
     * @param speed movement speed of the entity
     * @param attackPower the amount of damage the entity deals
     */
    protected EntityAttacker(final Color color, final double size, final int attackPower) {
	super(color, size, attackPower);
	this.targetEntity = null;
    }

    /**
     * Decides wheter to change the currently targeted enemy
     * Can be used by subclases to implement further logic about how to choose an Entity target
     *
     * @param entity the entity to attack
     */

    protected void decideTarget(Entity entity){

	if (targetEntity == null){
	    targetEntity = entity;
	}

    }

    /**
     * The abstract method entites implement to decide if it can be attacked
     *
     * @return if the entity can be attacked
     */
    @Override public boolean canBeAttacked() {
	return false;
    }


    /**
     * Decides wheter the targeted enemy can be attacked or not.
     * Can be used by subclases to implement further logic if an entity can be attacked or not
     *
     * @param entity the entity to attack
     */

    protected boolean canAttack(Entity entity){

        if (entity == null) {
	    return false;
	}
        if(!entity.canBeAttacked()){
            return false;
	}

	return true;
    }

    public Entity getTargetEntity() {
	return targetEntity;
    }

}
