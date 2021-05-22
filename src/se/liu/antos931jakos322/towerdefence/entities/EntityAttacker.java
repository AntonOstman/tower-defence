package se.liu.antos931jakos322.towerdefence.entities;

import java.awt.*;

public abstract class EntityAttacker extends Entity
{
    protected Entity targetEntity;

    protected EntityAttacker(final Color color, final double size, final double speed, final int attackPower) {
	super(color, size, speed, attackPower);
	this.targetEntity = null;
    }

    protected EntityAttacker(final Color color, final double size, final int attackPower) {
	super(color, size, attackPower);
	this.targetEntity = null;
    }

    public void decideTarget(Entity entity){

	if (targetEntity == null){
	    targetEntity = entity;
	}

    }

    @Override public boolean canBeAttacked() {
	return false;
    }

    public boolean canAttack(Entity entity){

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

    public abstract void draw(final Graphics2D g2d, final int gameScale);
}
